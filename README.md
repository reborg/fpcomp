# Component Example

    $ clojure -M:run-m

### General Idea

- Components, in a functional context, are long-lived bit of information that are shared across function calls.
- Differently from a var in a namespace, components also have a lifecycle that we want to control independently from when the namespace is loaded, or to impose a specific initialization order.
- This is when we want to look at component libraries and pretty much nothing else.
- If we didn't limit ourselves to a particular component definition, we could essentially have var defined for every piece of data and passing them around similarly to object-oriented programming (OOP).

### Components and Polymorphism

- A component is not required for polymorphism.
- We can have polymorphic dispatch without components.
- For example, a "path" in a file system is a simple type of data which is essentially just a string.
- We can fetch the content at that "path" depending on which environment the application is running in.
- If it's running locally, we `(fetch path)` to see the content of a local file.
- If it's running in "test", we can `(fetch path)` and access a file on S3 or even a database.
- Similarly, we could `(store path value)`.
- We just discovered a protocol which we call a Datastore.
- We can fetch from datastore, we can store into the datastore using the "path" as a key.
