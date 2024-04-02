(ns example.components.datastore-with-connection
  (:require [integrant.core :as ig]))

(defmethod ig/init-key :a/datastore-with-open-connection [_ _opts]
  {:connection :open-connection
   :stuff :other})

(defmethod ig/halt-key! :a/datastore-with-open-connection [_ datastore]
  (assoc datastore :connection :closed-connection))
