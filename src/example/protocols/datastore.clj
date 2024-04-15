(ns example.protocols.datastore
  (:require
   [example.components.database :as db]
   [example.config :as config]))

(defprotocol Datastore
  (fetch [this])
  (store [this v]))

(defrecord DatastoreLocalFs [k]
  Datastore
  (fetch [this] (println "fetch local") (slurp (:k this)))
  (store [this v] (println "store local") (spit (:k this) v)))

(defrecord DatastoreS3 [k]
  Datastore
  (fetch [this] (println "fetch s3") (slurp (:k this)))
  (store [this v] (println "store s3") (spit (:k this) v)))

(defrecord DatastoreDB [k]
  Datastore
  (fetch [this]
    (let [q (format "SELECT value FROM datastore WHERE key = '%s'" (:k this))]
      (db/execute q)))
  (store [this v]
    (let [q (format "INSERT INTO datastore (key, value) VALUES ('%s', '%s')" (:k this) v)]
      (db/execute q))))

