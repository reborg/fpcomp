(ns example.datastore-api
  (:require [example.config :as config]
            [example.protocols.datastore :as datastore]))

(defn for-key
  "Override ENV added here for testing purposes."
  [k & [override-env]]
  (let [init-fn (->> (config/load-config override-env)
                     :datastore
                     (ns-resolve (the-ns 'example.protocols.datastore)))]
    (init-fn k)))

(defn fetch
  "Override ENV added here for testing purposes."
  [k & [override-env]]
  (-> k
      (for-key override-env)
      datastore/fetch))

(defn store
  "Override ENV added here for testing purposes."
  [k v & [override-env]]
  (-> k
      (for-key override-env)
      (datastore/store v)))
