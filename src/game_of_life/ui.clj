(ns game-of-life.ui
  (:require [game-of-life.grid :refer :all])
  (:import (javax.swing SwingUtilities JFrame JPanel BorderFactory)
           (java.awt Color Dimension Graphics)))

(defn draw-grid [grid size-of-desired-grid-in-pixels g]
  (let [number-of-rows (number-of-rows grid)
        number-of-cols (number-of-cols grid)
        number-of-rc (max number-of-rows number-of-cols)
        cell-size (quot size-of-desired-grid-in-pixels number-of-rc)]
    (do
      (.setColor g Color/DARK_GRAY)
      (doall (map #(.drawLine g 0 (* cell-size %1) size-of-desired-grid-in-pixels (* cell-size %1)) (range 0 number-of-rc)))
      (doall (map #(.drawLine g (* cell-size %1) 0 (* cell-size %1) size-of-desired-grid-in-pixels) (range 0 number-of-rc)))
      )))

(def gol-panel
  (let [panel
        (proxy [JPanel] []
          (paintComponent [g]
            (.setColor g Color/RED)
            (.drawLine g 40 40 240 40)
            (.fillRect g 150 150 20 20))

          (getPreferredSize []
            (Dimension. 300 300)))]
    (doto panel
      (.setPreferredSize (Dimension. 300 300)))))



(defn create-and-show-gui []
  (doto (JFrame. "Game of Life")
    (.setDefaultCloseOperation (JFrame/EXIT_ON_CLOSE))
    (.setContentPane gol-panel)
    (.setBackground Color/WHITE)
    .pack
    .show
    ))


(defn -main []
  (create-and-show-gui))
