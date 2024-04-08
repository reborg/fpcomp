(ns example.datastore-api
  (:require [example.config :as config]))

(defn- active-ds
  [env]
  (println "### config" (resolve (:datastore (config/load))))
  )

(defn fetch [k]
  (let [ds (active-ds config/env)]))

(defn store [k v])
