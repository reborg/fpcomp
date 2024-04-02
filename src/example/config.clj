(ns example.config
 (:require [integrant.core :as ig]))

(def env (or (System/getenv "ENV") "dev"))

(def config
  (ig/read-string (slurp "config.edn")))
