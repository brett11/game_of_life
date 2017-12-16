(ns game-of-life.grid-test
  (:require [clojure.test :refer :all])
  (:require [game-of-life.grid :refer :all])
    )

(def example-grid-3x3 ; all cells dead (false)
  [
   [ 0 0 0 ]
   [ 0 0 0 ]
   [ 0 0 0 ]
   ]
  )

(def example-grid-3x3-middle-flipped ; all cells dead (false)
  [
   [ 0 0 0 ]
   [ 0 1 0 ]
   [ 0 0 0 ]
   ]
  )


(def example-grid-3x3-diag-flipped ; all cells dead (false)
  [
   [ 1 0 0 ]
   [ 0 1 0 ]
   [ 0 0 1 ]
   ]
  )

(def example-grid-4x5 ;all cells dead (false)
  [
   [ 0 0 0 0 0]
   [ 0 0 0 0 0]
   [ 0 0 0 0 0]
   [ 0 0 0 0 0]
   ]
  )

(def example-grid-5x5 ;cols 2 and 4 are alive (true)
     [
      [ 0 1 0 1 0]
      [ 0 1 0 1 0]
      [ 0 1 0 1 0]
      [ 0 1 0 1 0]
      [ 0 1 0 1 0]
      ]
     )

(def example-grid-7x7 ;cols 2 and 4 are alive (true)
  [
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0]
   ]
  )

(deftest make-dead-grid-test
  (testing "grid is vector of vectors (rows)"
    (is (= example-grid-3x3 (make-dead-grid 3 3)))
    (is (= example-grid-4x5 (make-dead-grid 4 5)))
    ))

(deftest get-cell-grid-test
  (let [test-grid (make-dead-grid 5 5)
        test-cell (get-cell test-grid [2 4])]
    (is (= 0 test-cell))))

(deftest number-of-rows-test
  (is (= 4 (number-of-rows example-grid-4x5))))

(deftest number-of-cols-test
  (is (= 5 (number-of-cols example-grid-4x5))))

(deftest get-cell-grid-test-2
  (let [test-cell (get-cell example-grid-3x3 [2 1])]
    (is (= 0 test-cell))))

(deftest flip-cell-in-grid-test
  (let [test-grid (flip-cell example-grid-3x3 [1 1])]
    (is (= example-grid-3x3-middle-flipped test-grid))))

(deftest flip-cells-test
  (let [test-grid (flip-cells example-grid-3x3 [[0 0] [1 1] [2 2]])]
    (is (= example-grid-3x3-diag-flipped test-grid))))
;(deftest alive-neighbor-count-test
;  (is (= 6 (alive-neighbor-count example-grid-5x5 2 2))))

(deftest add-coords-test
  (testing "#add_coords"
    (is (= [3 9] (add-coords [1 3] [2 6])))))

(deftest find_neighbors-old-test
  (testing "#find_neighbors"
    (let [expected_result [
                           [4,5],
                           [4,6],
                           [5,6],
                           [6,6],
                           [6,5],
                           [6,4],
                           [5,4],
                           [4,4],

                           ]]
      (is (= expected_result (find-neighbor-coords-old [5 5]))))))

(deftest find_neighbors-new-test
  (testing "#find_neighbors"
    (let [expected_result [
                           [4,5],
                           [4,6],
                           [5,6],
                           [6,6],
                           [6,5],
                           [6,4],
                           [5,4],
                           [4,4],

                           ]]
      (is (= expected_result (find-neighbor-coords-new example-grid-7x7 [5 5]))))))
