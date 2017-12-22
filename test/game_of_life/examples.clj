(ns game-of-life.examples)


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
(def glider-grid-1-10x10
  [
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 1 0 0 0 0 0 0]
   [ 0 1 0 1 0 0 0 0 0 0]
   [ 0 0 1 1 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
   [ 0 0 0 0 0 0 0 0 0 0]
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
