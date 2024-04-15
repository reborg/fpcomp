(ns example.components.database
  (:require [integrant.core :as ig]
            [example.components.system :refer [db]]))

(defmethod ig/init-key ::db [_k _url]
  (println "### Open database connection"))

(defmethod ig/halt-key! ::db [_ db]
  (println "### Closing database connection")
  :closed-connection)

(defn execute [q]
  (if (db)
    (do (println "### Query executed:" q) "works")
    (throw (Exception. "Connection is closed"))))
