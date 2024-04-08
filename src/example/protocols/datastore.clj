(ns example.protocols.datastore)

(defprotocol Datastore
  (fetch [this k])
  (store [this k v]))

(defrecord DatastoreLocalFs [path]
  Datastore
  (fetch [this k] (slurp (str path "/" k)))
  (store [this k v] (spit (str path "/" k) v)))

(defrecord DatastoreS3 [bucket]
  Datastore
  (fetch [this k] (slurp (str "s3://" bucket "/" k)))
  (store [this k v] (spit (str "s3://" bucket "/" k) v)))

(defrecord DatastoreDB [conn]
  Datastore
  (fetch [this k] (str conn "SELECT value FROM datastore WHERE key = ?" k))
  (store [this k v] (str conn "INSERT INTO datastore (key, value) VALUES (?, ?)" k v)))
