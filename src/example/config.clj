(ns example.config
  (:require
   [aero.core :as aero]))

(def env (keyword (or (System/getenv "ENV") "dev")))

(defn aero-config [& [override-env]]
  (aero/read-config
   "example-config.edn"
   {:profile (or override-env env)}))

(defn load
  "Return plain configuration without integrant system"
  [& [override-env]]
  (dissoc (aero-config override-env) :ig/system))

(defn components []
  (:ig/system (aero-config)))
