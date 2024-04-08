(ns example.components.system
  (:require [integrant.core :as ig]
            [example.config :as config]
            [example.components.database]
            ))

(defn system-map []
  (-> (config/components)
      ig/init))
