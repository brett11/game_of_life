(ns game-of-life.grid)

(defn make-dead-grid [number-of-rows number-of-cols]
  (into [] (repeat number-of-rows (into [] (repeat number-of-cols 0)))))

(defn get-cell [grid [row col]]
  (get-in grid [row col]))

(defn number-of-rows [grid]
  (count grid))

(defn number-of-cols [grid]
  (count (grid 0)))

(defn flip [x]
  (if (zero? x) 1 0))

(defn flip-cell [grid [row col]]
  (update-in grid [row col] flip))

(defn flip-cells [grid cells] ;takes in a grid and vector containing a vector for each cell coords
  (reduce flip-cell grid cells))

(defn add-cords-on-infinite-grid [grid coord1 coord2]
  ; figured out formula using this example of a 3x3 grid. 0,0 as target cell
  ;   2, 2   2, 0   2,1
  ;   0, 2   0, 0   0,1
  ;   1, 2   1, 0   1, 1
  (let [n_rows (number-of-rows grid)
        n_cols (number-of-cols grid)
        [x1 y1] coord1
        [x2 y2] coord2
        intermediate-x (+ x1 x2)
        intermediate-y (+ y1 y2)
        new-x (if (>= intermediate-x 0)
                (rem intermediate-x n_rows)
                (+ n_rows intermediate-x)
                )
        new-y (if (>= intermediate-y 0)
                (rem intermediate-y n_cols)
                (+ n_cols intermediate-y)
                )
        ]
    [new-x new-y]
    ))

(def traditional-neighbor-coordinates
  [   ;clockwise
      ; 0, 0   0, 1   0, 2
      ; 1, 0   1, 1   1, 2
      ; 2, 0   2, 1   2, 2
   [-1,-1] [-1, 0] [-1, 1]
   [ 0,-1]         [ 0, 1]
   [ 1,-1] [ 1, 0] [ 1, 1]
   ])

(defn find-neighbor-coords [grid [r c]]
  (map (partial add-cords-on-infinite-grid grid [r c]) traditional-neighbor-coordinates))

(defn alive-neighbor-count [grid [row col]]
  (let [neighbor-coords (find-neighbor-coords grid [row col])]
    (->>
      neighbor-coords
      (map (partial get-cell grid))
      (filter #(= % 1))
      (count))))

(defn alive? [cell_value]
  (= 1 cell_value))

(defn flip? [grid [r c]]
  (let [alive-neighbor-count (alive-neighbor-count grid [r c])
        cell (get-cell grid [r c])]
    (if (alive? cell)
      (cond
        (< alive-neighbor-count 2) true ;live cell with fewer than two neighbors dies (gets flipped)
        (or (= 2 alive-neighbor-count) (= 3 alive-neighbor-count)) false ;live cell with 2 or 3 neighbors continues living
        (> alive-neighbor-count 3) true ;overpopulation
        )
      (if (= 3 alive-neighbor-count)
        true ;dead cell with 3 neighbors becomes alive
        false ;otherwise dead cell stays dead )
        ))))

(defn generate-coord-vectors [grid]
  (let [row-range (range (number-of-rows grid))
        col-range (range (number-of-cols grid))]
    (into [] (for [row-index row-range col-index col-range] [row-index col-index]))))

(defn find-coords-need-to-be-flipped [grid]
  (->>
    (generate-coord-vectors grid)
    (filter (partial flip? grid))
    (into [])))

(defn generation [grid]
  (->>
    grid
    (find-coords-need-to-be-flipped)
    (flip-cells grid)))