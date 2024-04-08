# example

    $ clojure -M:run-m

### Brain dump

- the client calls some data-store component, store/fetch something
- the choice of the data-store implementation is done based on the environment, in this case ENV
- in this case the datastore is a local file system data store implementation
- the data-store namespace hides everything away from the client
- long lived components are just in the app as a startup optimization: we don't want to costantly recycle resources when this is expensive
- we can use the same configuration file for components and normal configuration
- in terms of configuration for the data store, I need to know what to do for DEV compared to PROD.
- whenever the client calls, I want to decorate any data connected to the data-store, for example the "key" with the fetch/store behavior. This is a reify operation for each function call.
    - In practice, I'm calling fetch on a key and store on a key also passing some value.
    - I can't store the key as a long living instance because there is no state, it's just pure behavior around the key, which is the data.
- How to pick the correct instance for reify? Good old reflection I believe.
    - Anything dispatching on something in the configuration.
