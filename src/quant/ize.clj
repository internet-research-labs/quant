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


(defn argmin
  "Return key of max value."
  [xs]
  (loop [ys (if (map? xs) xs (zipmap (range) xs))
         smallest-pos   nil
         smallest-value Double/POSITIVE_INFINITY]
    (if-not (empty? ys)
      (let [[k value] (first ys)]
        (if (< value smallest-value)
          (recur (rest ys) k value)
          (recur (rest ys) smallest-pos smallest-value)))
      ;; FINAL RETURN VALUE
      smallest-pos)))

; (loop [i 0
;        ys xs
;        smallest Double/POSITIVE_INFINITY]
;   (if (empty? ys)
;     (let [value (first ys)]
;         (recur (inc i)
;                (rest ys)
;                (if (< smallest (first ys)) smallest value)))
;     smallest)))


(defn group-points
  "Return clusters."
  [k points distf iterations]
  (for [point points]
    (loop [centers (repeat k random-color)
           clusters (zipmap centers (repeat (count centers) []))]
      clusters
    )))
