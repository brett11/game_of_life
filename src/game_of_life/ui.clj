(ns game-of-life.ui
  (:require [game-of-life.core :refer :all]
            [game-of-life.examples :refer :all])
  (:import (javax.swing SwingUtilities JFrame JPanel BorderFactory Timer)
           (java.awt Color Dimension Graphics)
           (java.awt.event ActionListener)))

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


(defn gol-panel [pixels grid]
  (let [current-grid (atom grid)
        panel (proxy [JPanel ActionListener] []
                (paintComponent [g]
                  (draw-grid @current-grid pixels g))

                (actionPerformed [e]
                  (swap! current-grid generation)
                  (.repaint this))

                (getPreferredSize []
                  (Dimension. pixels pixels)))]
    panel
    ))

(defn create-and-show-gui []
  (let [panel (gol-panel 300 glider-grid-1-10x10)
        timer (Timer. 100 panel)]
    (doto (JFrame. "Game of Life")
      (.setDefaultCloseOperation (JFrame/EXIT_ON_CLOSE))
      (.setContentPane panel)
      (.setBackground Color/WHITE)
      .pack
      .show)
    (.start timer)))


(defn -main []
  (create-and-show-gui))
