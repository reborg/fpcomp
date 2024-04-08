(ns example.example
  (:require [example.config :as config]
            [example.components.system :as system]
            [example.datastore-api :as ds])
  (:gen-class))

(defn stuff
  []
  (println "### config" (config/aero-config))
  (ds/store "key" "value")
  (println "datastore value for key" (ds/fetch "key"))
  (system/system-map))

(defn -main
  [& args]
  (stuff))
