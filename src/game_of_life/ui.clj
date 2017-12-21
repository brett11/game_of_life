(ns game-of-life.ui
  (:require [game-of-life.grid :refer :all])
  (:import (javax.swing SwingUtilities JFrame JPanel BorderFactory)
           (java.awt Color Dimension Graphics)
           (java.awt.event ActionListener)))



;(defn draw-boxes [grid rows cols g]
;  (let [all_coordinates (for [r (range 0 7) c (range 0 6)] [r c])]
;    (map )))

(defn draw-grid [grid size-of-desired-grid-in-pixels g]
  (let [number-of-rows (number-of-rows grid)
        number-of-cols (number-of-cols grid)
        row-size (quot size-of-desired-grid-in-pixels number-of-rows)
        col-size (quot size-of-desired-grid-in-pixels number-of-cols)
        all-coordinates (for [r (range 0 number-of-rows) c (range 0 number-of-cols)] [r c])
        draw-box-if-alive (fn[[r c]]
                   (if (alive? (get-cell grid [r c]))
                     (.fillRect g (* c col-size) (* r row-size) row-size col-size)
                   ))
        ]
    (do
      (.setColor g Color/DARK_GRAY)
      ; draw grid
      (doall (map #(.drawLine g 0 (* col-size %1) size-of-desired-grid-in-pixels (* col-size %1)) (range 0 number-of-cols)))
      (doall (map #(.drawLine g (* row-size %1) 0 (* row-size %1) size-of-desired-grid-in-pixels) (range 0 number-of-rows)))
      ; draw boxes
      (doall (map draw-box-if-alive all-coordinates))
      )))

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

(defn gol-panel [pixels]
  (let [panel
        (proxy [JPanel ActionListener] []
          (paintComponent [g]
            (draw-grid (generation (generation glider-grid-1-6x6)) pixels g))

          (actionPerformed [e]
            (.repaint this))

          (getPreferredSize []
            (Dimension. pixels pixels)))]
      panel
      ))

(defn create-and-show-gui []
  (doto (JFrame. "Game of Life")
    (.setDefaultCloseOperation (JFrame/EXIT_ON_CLOSE))
    (.setContentPane (gol-panel 300))
    (.setBackground Color/WHITE)
    .pack
    .show
    ))


(defn -main []
  (create-and-show-gui))
