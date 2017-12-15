(ns game-of-life.cell-test
  (:require [clojure.test :refer :all])
  (:require [game-of-life.cell :refer :all]))

(deftest cell-instantiate-test
  (testing "cell instantiated"
    (let [test-cell (->Cell false 2 1)]
      (is (= false (:alive test-cell)))
      (is (= 2 (:row test-cell)))
      (is (= 1 (:col test-cell))))))

(deftest new-cell-test
  (testing "#new_cell"
    (let [test-cell (new_cell)]
      (is (= false (:alive test-cell)))
      (is (= 0 (:row test-cell)))
      (is (= 0 (:col test-cell))))))

(deftest add-coords-test
  (testing "#add_coords"
    (is (= [3 9] (add-coords [1 3] [2 6])))))

(deftest find_neighbors-test
  (testing "#find_neighbors"
    (let [test-cell (->Cell false 5 5)
          expected_result [
                           [5,6],
                           [6,6],
                           [6,5],
                           [6,4],
                           [5,4],
                           [4,4],
                           [4,5],
                           [4,6],
                           ]]
      (is (= expected_result (find-neighbor-coords test-cell))))))

(deftest make-alive-test
  (testing "takes dead cell and returns same cell but alive"
    (let [test-cell (->Cell false 2 1)
          test-cell-alive (make-alive test-cell)]
      (is (= false (:alive test-cell)))
      (is (= 2 (:row test-cell)))
      (is (= 1 (:col test-cell)))
      (is (= true (:alive test-cell-alive)))
      (is (= 2 (:row test-cell-alive)))
      (is (= 1 (:col test-cell-alive))))))

