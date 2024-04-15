# Component Example

    $ clojure -M:run-m

### General Idea

In a functional context, components are long-lived entities referenced globally. They typically represent tangible resources such as connections, servers, threads, IO operations, and other side effectful elements. The discipline of components specifies precise boundaries for managing side effects. Unlike a variable in a namespace, components have a lifecycle that we wish to manage independently of when the namespace is loaded or to enforce a specific initialization order. This is where we turn to component libraries that provide tools for handling component lifecycles. Unlike object-oriented programming, components adhere strictly to the usage pattern outlined above, as introducing stateful objects tends to complicate program reasoning.

### Components and Polymorphism

A component is not required for polymorphism as we can have polymorphic dispatch without components. For example, a "Path" in a file system is a simple type of data to descrie the location of an object. A typical use case would be to fetch the content for that path depending on which environment the application is running in: if it's running locally, we `(fetch path)` to see the content of a local file. If it's running in a AWS environment, we can `(fetch path)` and access a file on S3 (or a database). Similarly, we could `(store path value)` based on the environment.

Let's call this abstraction a "Datastore" from which we can fetch/store given a path. The abstraction is the behavior that we can capture with this simple protocol:

```clojure
(defprotocol Datastore
  (fetch [this])
  (store [this v]))
```

We can extend such behavior to data in different ways. We can extend all strings to have this behavior (assuming a string can always represent a path) or we could create a specific `defrecord` for it:

```clojure
(defrecord DatastoreLocalFs [k]
  Datastore
  (fetch [this] (println "fetch local") (slurp (:k this)))
  (store [this v] (println "store local") (spit (:k this) v)))

(defrecord DatastoreS3 [k]
  Datastore
  (fetch [this] (println "fetch s3") (slurp (:k this)))
  (store [this v] (println "store s3") (spit (:k this) v)))

(defrecord DatastoreDB [k]
  Datastore
  (fetch [this]
    (let [q (format "SELECT value FROM datastore WHERE key = '%s'" (:k this))]
      (db/execute q)))
  (store [this v]
    (let [q (format "INSERT INTO datastore (key, value) VALUES ('%s', '%s')" (:k this) v)]
      (db/execute q))))
```

There is no need to maintain a long lived Datastore component because the datastore interface is not necessarily stateful. The choice for a datastore backend might require or not a stateful component. For example, the local file system or S3 as the datastore backend do not require any state. S3 (and many other AWS components) work with a REST interface which doesn't impose any state on the caller. The database backend, however, requires a connection to be established and maintained. This is where we can introduce a component to manage the lifecycle of the connection which the Datastore uses.

### Resolving defrecords

The specific defrecord to use based on environment can be configured as follows:

```clojure
{:datastore #profile {:dev ->DatastoreLocalFs
                      :test ->DatastoreS3
                      :prod ->DatastoreDB}}
```

Each environment specify the defrecord constructor to use as a symbol. An instance of the correct `defrecord` to create can be resolved at runtime as follows:

```clj
(defn for-key
  "Override ENV added here for testing purposes."
  [k & [override-env]]
  (let [init-fn (->> (config/load-config override-env)
                     :datastore
                     (ns-resolve (the-ns 'example.protocols.datastore)))]
    (init-fn k)))
```

From the perspective of the client using the datastore abstraction, there is nothing to know about how the datastore have been resolved or how the database connection has been acquired:

```clj
(require '[example.datastore-api :as ds])
(ds/fetch "test/key.txt")
(ds/store "test/key.txt" "value")
```
