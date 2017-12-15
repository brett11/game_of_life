(ns game-of-life.grid
  (:require [game-of-life.cell :refer :all]))

(defn make-row-of-dead-cells [number-of-cols row]
  (letfn [(helper[row n accum]
            (if (= n number-of-cols)
              (into [] accum)
              (helper row (+ n 1) (conj accum (->Cell false row n) ))))]
    (helper row 0 [])))

(defn make-dead-grid [number-of-rows number-of-cols]
  (let [row_numbers (range number-of-rows)]
    (into [] (map (partial make-row-of-dead-cells number-of-cols) row_numbers))))

(defn get-cell [grid row col]
  (get-in grid [row col]))

(defn change-cell-in-grid [grid row col f]
  (let [target-cell (get-cell grid row col)
        updated-cell (f target-cell)]
    (assoc-in grid [row col] updated-cell)))

(defn cell-neighbors [grid r c]
  (let [target-cell (get-cell grid r c)]
    ))

(defn alive-neighbor-count [grid r c]
  )

;(defn debugit []
;  (let [test-grid (make-dead-grid 5 5)
;        test-cell (get-cell test-grid 2 4)
;        row-test (:row test-cell)
;        col-test (:col test-cell)]
;    nil
;     ))
;
;(debugit)
