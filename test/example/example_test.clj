(ns example.example-test
  (:require [clojure.test :refer :all]
            [example.datastore-api :as ds]
            ))

(deftest fetching-from-local-fs
  (testing ""
    (is (= "" (ds/fetch "test/key.txt")))))
