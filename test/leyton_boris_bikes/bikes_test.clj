(ns leyton-boris-bikes.bikes-test
  (:require [clojure.test :refer :all]
            [orchestra.spec.test :as stest]
      
            [leyton-boris-bikes.bikes :as sut]))

(stest/instrument)

(deftest distance-between-2-points
  (testing "3,4,5 triangle"
    (is
      (= 5E0 (sut/distance {:lat 0 :lon 0} {:lat 3 :lon 4})))))

(deftest no-auth-happy 
  (testing "will test result conforms to spec. todo - avoid making acutal http request"
     (sut/bike-points-with-availability)))