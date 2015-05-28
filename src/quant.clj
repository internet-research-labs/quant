(ns quant
  (:import  [java.awt.image BufferedImage]
            [org.imgscalr Scalr]
            [javax.imageio ImageIO]
            [org.imgscalr Scalr$Method Scalr$Mode])
  (:require [clojure.java.io :as io]))

(defn- buffered-image [image]
  "Return a buffered image."
  (if (instance? BufferedImage image)
    image
    (ImageIO/read image)))

(defn dimensions
  "Return a map of image dimensions."
  [image]
  (if (nil? image)
    {:width 0 :height 0}
    {:width (.getWidth image) :height (.getHeight image)}))

(defn resize
  "Resizes an image."
  [image width height]
  (if-not (nil? image)
    (Scalr/resize (buffered-image image) Scalr$Mode/FIT_EXACT width height nil)))

(defn- pixel
  "Return rgb-value for a pixel"
  [image x y]
  (when (and (not (nil? image))
             (< -1 x (.getWidth image))
             (< -1 y (.getHeight image)))
    (let [color (.getRGB image x y)]
      {:alpha (bit-shift-right (bit-and color 0xFF000000) 24)
       :red   (bit-shift-right (bit-and color 0x00FF0000) 16)
       :green (bit-shift-right (bit-and color 0x0000FF00) 8)
       :blue  (bit-and color 0x000000FF)})))

(defn sample-colors
  "Return a sample of *n* pixels."
  [n image]
  (when-not (nil? image)
    (let [{height :height width :width} (dimensions image)]
      (repeat n #([(rand-int width) (rand-int height)])))))
