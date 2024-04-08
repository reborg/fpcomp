(ns example.components.database
  (:require [integrant.core :as ig]))

(defmethod ig/init-key ::db [_ _opts]
  (println "### Initializing database")
  {:connection :open-connection
   :stuff :other})

(defmethod ig/halt-key! ::db [_ db]
  (println "### Closing database connection")
  (assoc db :connection :closed-connection))
