(ns example.components.system
  (:require [integrant.core :as ig]
            [example.config :as config]))

(let [lock (Object.)]
  (defn- ig-load-ns!
    [system-config]
    (locking lock
      (ig/load-namespaces system-config))))

(defn system-map []
  (let [cfg (config/components)]
    (ig-load-ns! cfg)
    cfg))

(defn db []
  (-> (system-map)
      :example.components.database/db))
