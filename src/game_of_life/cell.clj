(ns game-of-life.cell)

(defrecord Cell [alive row col])

(defn new_cell []
  (->Cell false 0 0))

(defn extract-coords [cell]
  [(:row cell) (:col cell)])

(defn add-coords [coord1 coord2]
  (into [] (map + coord1 coord2)))

(def neighbor-coordinates
  [   ;clockwise
    [ 0, 1],
    [ 1, 1],
    [ 1, 0],
    [ 1,-1],
    [ 0,-1],
    [-1,-1],
    [-1, 0],
    [-1, 1]
    ])

(defn make-alive [cell]
  (assoc cell :alive true))

(defn find-neighbor-coords [cell]
  (let [cell-coords (extract-coords cell)]
    (map (partial add-coords cell-coords) neighbor-coordinates)
    ))