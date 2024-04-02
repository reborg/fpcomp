(ns example.datastore-api
  (:require [example.config :as config]))

(defn- init-datastore
  [env]
  )

(defn fetch [k]
  (let [ds (init-datastore config/env)]))

(defn store [k v])
