(ns example.components.system
  (:require [integrant.core :as ig]
            [example.config :as config]))

(defn system-map []
  (let [cfg (config/components)]
    (ig/init cfg)
    cfg))

(defn db []
  (-> (system-map)
      :example.components.database/db))
