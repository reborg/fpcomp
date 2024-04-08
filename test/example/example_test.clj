(ns example.example-test
  (:require [clojure.test :refer :all]
            [example.datastore-api :as ds]
            [example.example :refer :all]))

; (deftest a-test
;   (testing "FIXME, I fail."
;     (is (= 0 (stuff)))))

(deftest another-test
  (testing "FIXME, I fail."
    (is (= nil (ds/fetch "target/key.txt")))))
