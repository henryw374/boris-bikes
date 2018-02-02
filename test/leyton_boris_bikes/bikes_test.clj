(ns leyton-boris-bikes.bikes-test
  (:require [clojure.test :refer :all]
            [leyton-boris-bikes.bikes :as sut]))


(deftest distance-between-2-points
  (testing "3,4,5 triangle"
    (is
      (= 5E0 (sut/distance {:lat 0 :long 0} {:lat 3 :long 4})))))
