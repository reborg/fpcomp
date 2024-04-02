(ns example.example
  (:require [example.config :as config]
            [example.system :as system]
            [example.datastore :as datastore]
            [clojure.pprint :as pprint])
  (:gen-class))

(defn stuff
  []
  (datastore/store "key" "value")
  (println "Datastore value for key" (datastore/fetch "key")))

(defn -main
  [& args]
  (println "Integrant, example, configuration example for ENV" config/env)
  (stuff))
