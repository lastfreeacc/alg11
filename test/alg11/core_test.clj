(ns alg11.core-test
  (:require [clojure.test :refer :all]
            [alg11.core :refer :all]))

(deftest get-half-vectors-test
  (testing
    (is (= (get-half-vectors [1 2 3 4 5])
           {:first [1 2 3] :second [4 5]}))))

(deftest sort-and-count-split-invertions-test
  (testing
    (is (= (sort-and-count-split-invertions [4 5 6] [1 2 3] 0)
           {:inv 9 :sorted-vec [1 2 3 4 5 6]}))))

(deftest sort-and-count-invertions-test
  (testing
    (is (= (sort-and-count-invertions [3 1 5 2 4 6] 0)
           {:inv 4 :sorted-vec [1 2 3 4 5 6]}))
    (is (= (sort-and-count-invertions array-for-test 0)
           {:inv 3, :sorted-vec [1 2 3 4 5 6]}))))
