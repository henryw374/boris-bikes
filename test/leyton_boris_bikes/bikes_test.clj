(ns leyton-boris-bikes.bikes-test
  (:require [clojure.test :refer :all]
            [clojure.spec.alpha :as s]
            [leyton-boris-bikes.bikes :as sut]))


(deftest distance-between-2-points
  (testing "3,4,5 triangle"
    (is
      (= 5E0 (sut/distance {:lat 0 :lon 0} {:lat 3 :lon 4})))))

(deftest no-auth-happy 
  (testing ""
    (let [result (sut/bike-points-with-availability)]
      (is (not (empty? result)))
      (is (s/valid? (s/coll-of ::sut/bike-point) result)))))