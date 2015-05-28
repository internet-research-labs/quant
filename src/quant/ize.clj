(ns quant.ize
  (:require [quant]))

(defn k-colors
  [k image]
  (when (and (not (nil? image))
             (<= 0 k))
    nil))

(defmacro sqr [n] `(let [v# ~n] (* v# v#)))

(defn color-distance
  "Returns the l2 distance of two colors."
  [col1 col2]
  (+ (sqr (- (:red col1)   (:red col2)))
     (sqr (- (:green col1) (:green col2)))
     (sqr (- (:blue col1)  (:blue col2)))))

(defn random-color
  "Return a random color"
  []
  {:red   (/ (rand-int 1000) 1000)
   :green (/ (rand-int 1000) 1000)
   :blue  (/ (rand-int 1000) 1000)
   :alpha (/ (rand-int 1000) 1000)})


(defn- k-means
  "Return clusters."
  [k points]
  (loop [clusters (repeat k random-color)]
    (for [point points]
      1
    )))
