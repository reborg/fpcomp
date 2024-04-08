(ns example.components.system
  (:require [integrant.core :as ig]
            [example.config :as config]))

(def system-map
  (-> (config/components)
      ig/init))

(def db
  (-> system-map
      :example.components.database/db))
