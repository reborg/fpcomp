(ns example.datastore-api
  (:require [example.config :as config]
            [example.protocols.datastore :as datastore]))

(defn for-key
  [k]
  (let [init-fn (->> (config/load-config)
                     :datastore
                     (ns-resolve (the-ns 'example.protocols.datastore)))]
    (init-fn k)))

(defn fetch [k]
  (-> k
      for-key
      datastore/fetch))

(defn store [k v]
  (-> k
      for-key
      (datastore/store v)))
