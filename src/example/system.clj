(ns example.system
  (:require [integrant.core :as ig]
            [example.config :as config]
            [example.components.datastore-with-connection]
            ))

(def system (ig/init config/config))
