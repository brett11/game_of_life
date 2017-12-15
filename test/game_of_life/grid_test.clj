(ns game-of-life.grid-test
  (:require [clojure.test :refer :all])
  (:require [game-of-life.grid :refer :all])
  (:require [game-of-life.cell :refer :all]))

(def example-grid-3x3 ; all cells dead (false)
  [
   [(->Cell false 0 0) (->Cell false 0 1) (->Cell false 0 2)]
   [(->Cell false 1 0) (->Cell false 1 1) (->Cell false 1 2)]
   [(->Cell false 2 0) (->Cell false 2 1) (->Cell false 2 2)]
   ]
  )

(def example-grid-4x5 ;all cells dead (false)
  [
   [(->Cell false 0 0) (->Cell false 0 1) (->Cell false 0 2) (->Cell false 0 3) (->Cell false 0 4) ]
   [(->Cell false 1 0) (->Cell false 1 1) (->Cell false 1 2) (->Cell false 1 3) (->Cell false 1 4) ]
   [(->Cell false 2 0) (->Cell false 2 1) (->Cell false 2 2) (->Cell false 2 3) (->Cell false 2 4) ]
   [(->Cell false 3 0) (->Cell false 3 1) (->Cell false 3 2) (->Cell false 3 3) (->Cell false 3 4) ]
   ]
  )

(def example-grid-5x5 ;cols 2 and 4 are alive (true)
  [
   [(->Cell false 0 0) (->Cell true 0 1) (->Cell false 0 2) (->Cell true 0 3) (->Cell false 0 4) ]
   [(->Cell false 1 0) (->Cell true 1 1) (->Cell false 1 2) (->Cell true 1 3) (->Cell false 1 4) ]
   [(->Cell false 2 0) (->Cell true 2 1) (->Cell false 2 2) (->Cell true 2 3) (->Cell false 2 4) ]
   [(->Cell false 3 0) (->Cell true 3 1) (->Cell false 3 2) (->Cell true 3 3) (->Cell false 3 4) ]
   [(->Cell false 4 0) (->Cell true 4 1) (->Cell false 4 2) (->Cell true 4 3) (->Cell false 4 4) ]
   ]
  )

(deftest make-dead-grid-test
  (testing "grid is vector of vectors (rows)"
    (is (= example-grid-3x3 (make-dead-grid 3 3)))
    (is (= example-grid-4x5 (make-dead-grid 4 5)))))

(deftest get-cell-grid-test
  (let [test-grid (make-dead-grid 5 5)
        test-cell (get-cell test-grid 2 4)]
    (is (= false (:alive test-cell)))
    (is (= 2 (:row test-cell)))
    (is (= 4 (:col test-cell)))))

(deftest get-cell-grid-test-2
  (let [test-cell (get-cell example-grid-3x3 2 1)]
    (is (= (->Cell false 2 1) test-cell))))

(deftest change-cell-in-grid-test
  (let [old-grid (make-dead-grid 5 5)
        test-grid (change-cell-in-grid old-grid 2 4 #(assoc % :alive true))
        test-cell (get-cell test-grid 2 4)
        ]
    (is (= true (:alive test-cell)))
    (is (= 2 (:row test-cell)))
    (is (= 4 (:col test-cell)))))

(deftest alive-neighbor-count-test
  (is (= 6 (alive-neighbor-count example-grid-5x5 2 2))))
