(ns protocols.protocols
  (:gen-class))

(defn stuff
  [data]
  (println (str "Hello, " (or (:name data) "World") "!")))

(defn -main
  [& args]
  (println "Integrant, protocols, configuration example")
  (stuff {:name (first args)}))
