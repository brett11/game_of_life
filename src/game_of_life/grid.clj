(ns game-of-life.grid
  )

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

(defn add-coords [coord1 coord2]
  (into [] (map + coord1 coord2)))

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
   [-1, 0], ;top
   [-1, 1], ;top right
   [ 0, 1], ;right
   [ 1, 1], ;bottom right
   [ 1, 0], ;bottom
   [ 1,-1], ;bottom left
   [ 0,-1], ;left
   [-1,-1]  ;top left
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

