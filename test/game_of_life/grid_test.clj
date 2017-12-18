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

(def blinker-grid-5x5 ;cols 2 and 4 are alive (true)
  [
   [ 0 0 0 0 0]
   [ 0 0 1 0 0]
   [ 0 0 1 0 0]
   [ 0 0 1 0 0]
   [ 0 0 0 0 0]
   ]
  )


(def glider-grid-1-6x6
  [
   [ 0 0 0 0 0 0 ]
   [ 0 0 0 1 0 0 ]
   [ 0 1 0 1 0 0 ]
   [ 0 0 1 1 0 0 ]
   [ 0 0 0 0 0 0 ]
   [ 0 0 0 0 0 0 ]
   ]
  )

(def glider-grid-2-6x6
  [
   [ 0 0 0 0 0 0 ]
   [ 0 0 1 0 0 0 ]
   [ 0 0 0 1 1 0 ]
   [ 0 0 1 1 0 0 ]
   [ 0 0 0 0 0 0 ]
   [ 0 0 0 0 0 0 ]
   ]
  )

(def glider-grid-3-6x6
  [
   [ 0 0 0 0 0 0 ]
   [ 0 0 0 1 0 0 ]
   [ 0 0 0 0 1 0 ]
   [ 0 0 1 1 1 0 ]
   [ 0 0 0 0 0 0 ]
   [ 0 0 0 0 0 0 ]
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

(deftest find_neighbors-new-test
  (testing "#find_neighbors"
    (let [expected_result [
                     [4,4] [4,5] [4,6]
                     [5,4]       [5,6]
                     [6,4] [6,5] [6,6]
                           ]]
      (is (= expected_result (find-neighbor-coords example-grid-7x7 [5 5]))))))

(deftest find_neighbors-new-test-2
  (testing "#find_neighbors"
    (let [expected_result [
                            [2,2] [2,0] [2,1]
                            [0,2]       [0,1]
                            [1,2] [1,0] [1,1]
                           ]]
      (is (= expected_result (find-neighbor-coords example-grid-3x3 [0 0]))))))

(deftest alive-neighbor-count-test
  (testing "#alive-neighbor-count"
    (is (= 6 (alive-neighbor-count example-grid-5x5 [2 2])))))

(deftest alive-neighbor-count-test
  (testing "#alive-neighbor-count"
    (is (= 3 (alive-neighbor-count glider-grid-1-6x6 [3 2])))))

(deftest flip-test
  (is (= false (flip? blinker-grid-5x5 [0 0])))
  (is (= false (flip? blinker-grid-5x5 [0 1])))
  (is (= false (flip? blinker-grid-5x5 [0 2])))
  (is (= false (flip? blinker-grid-5x5 [0 3])))
  (is (= false (flip? blinker-grid-5x5 [0 4])))
  (is (= false (flip? blinker-grid-5x5 [1 0])))
  (is (= false (flip? blinker-grid-5x5 [1 1])))
  (is (= true  (flip? blinker-grid-5x5 [1 2])))
  (is (= false (flip? blinker-grid-5x5 [1 3])))
  (is (= false (flip? blinker-grid-5x5 [1 4])))
  (is (= false (flip? blinker-grid-5x5 [2 0])))
  (is (= true  (flip? blinker-grid-5x5 [2 1])))
  (is (= false (flip? blinker-grid-5x5 [2 2])))
  (is (= true  (flip? blinker-grid-5x5 [2 3])))
  (is (= false (flip? blinker-grid-5x5 [2 4])))
  (is (= false (flip? blinker-grid-5x5 [3 0])))
  (is (= false (flip? blinker-grid-5x5 [3 1])))
  (is (= true  (flip? blinker-grid-5x5 [3 2])))
  (is (= false (flip? blinker-grid-5x5 [3 3])))
  (is (= false (flip? blinker-grid-5x5 [3 4])))
  (is (= false (flip? blinker-grid-5x5 [4 0])))
  (is (= false (flip? blinker-grid-5x5 [4 1])))
  (is (= false (flip? blinker-grid-5x5 [4 2])))
  (is (= false (flip? blinker-grid-5x5 [4 3])))
  (is (= false (flip? blinker-grid-5x5 [4 4]))))

(deftest generate-coord-vectors-test
  (is (= [
          [0,0] [0,1] [0,2]
          [1,0] [1,1] [1,2]
          [2,0] [2,1] [2, 2]
          ] (generate-coord-vectors example-grid-3x3))))

(deftest find-coords-need-to-be-flipped-test
  (is (= [ [1 2]
           [2 1]
           [2 3]
           [3 2]
          ] (find-coords-need-to-be-flipped blinker-grid-5x5))))

(deftest one-generation-blinker-test
  (is (=
          [
           [ 0 0 0 0 0]
           [ 0 0 0 0 0]
           [ 0 1 1 1 0]
           [ 0 0 0 0 0]
           [ 0 0 0 0 0]
           ] (generation blinker-grid-5x5))))

(deftest two-generations-blinker-test
  (is (=
        [
         [ 0 0 0 0 0]
         [ 0 0 1 0 0]
         [ 0 0 1 0 0]
         [ 0 0 1 0 0]
         [ 0 0 0 0 0]
         ] (generation (generation blinker-grid-5x5)))))

(deftest one-generation-glider-test
  (is (= glider-grid-2-6x6 (generation glider-grid-1-6x6))))

(generation glider-grid-1-6x6)

(deftest two-generations-glider-test
  (is (= glider-grid-3-6x6 (generation (generation glider-grid-1-6x6)))))
