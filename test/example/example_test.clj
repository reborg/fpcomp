(ns example.example-test
  (:require [clojure.test :refer :all]
            [example.datastore-api :as ds]
            ))

(deftest fetching-from-local-fs
  (testing "fetching from local fs"
    (is (= "works\n" (ds/fetch "test/key.txt")))))
