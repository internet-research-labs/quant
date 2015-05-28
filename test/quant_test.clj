(ns quant-test
  (:require [clojure.test :refer :all]
            [quant :refer :all]
            [quant.core :refer :all]
            [clojure.java.io :as io]))

(deftest dimensions-test
  (let [image-1 (javax.imageio.ImageIO/read (io/resource "image-1.gif"))
        image-2 (javax.imageio.ImageIO/read (io/resource "image-2.jpg"))
        image-3 (javax.imageio.ImageIO/read (io/resource "image-3.jpg"))
        image-4 (javax.imageio.ImageIO/read (io/resource "image-4.jpg"))]

    (testing "dimensions"
      (testing "should return a map"
        (is (map? (dimensions nil))))
      (testing "should return 0x0 for nil values"
        (is (= 0 (:width  (dimensions nil))))
        (is (= 0 (:height (dimensions nil)))))
      (testing "should return valid values for non-null images"
        (is (= 259 (:width  (dimensions image-1))))
        (is (= 212 (:height (dimensions image-1))))
        (is (= 640 (:width  (dimensions image-2))))
        (is (= 615 (:height (dimensions image-2))))))))

(deftest resize-test
  (let [image-1 (javax.imageio.ImageIO/read (io/resource "image-1.gif"))
        image-2 (javax.imageio.ImageIO/read (io/resource "image-2.jpg"))
        image-3 (javax.imageio.ImageIO/read (io/resource "image-3.jpg"))
        image-4 (javax.imageio.ImageIO/read (io/resource "image-4.jpg"))]
    (testing "resize"
      (testing "should resize to the correct sizes"
        (let [resized-nil (resize nil 200 200)
              resized-1 (resize image-1 200 300)
              resized-2 (resize image-2 400 300)
              resized-3 (resize image-3  69  70)
              resized-4 (resize image-4 800 800)]
          (is (= 0 (:width  (dimensions resized-nil))))
          (is (= 0 (:height (dimensions resized-nil))))
          (is (= 200 (:width  (dimensions resized-1))))
          (is (= 300 (:height (dimensions resized-1))))
          (is (= 400 (:width  (dimensions resized-2))))
          (is (= 300 (:height (dimensions resized-2))))
          (is (= 69 (:width  (dimensions resized-3))))
          (is (= 70 (:height (dimensions resized-3))))
          (is (= 800 (:width  (dimensions resized-4))))
          (is (= 800 (:height (dimensions resized-4)))))))))

(deftest sample-colors-tst
  (let [image-1 (javax.imageio.ImageIO/read (io/resource "image-1.gif"))
        image-2 (javax.imageio.ImageIO/read (io/resource "image-2.jpg"))
        image-3 (javax.imageio.ImageIO/read (io/resource "image-3.jpg"))
        image-4 (javax.imageio.ImageIO/read (io/resource "image-4.jpg"))]

    (testing "sample-colors"
      (testing "should return 0 for n=0"
        (is (= 0 (count (sample-colors 0 image-1)))))

      (testing "shuld return 0 for image=nil"
        (is (= 0 (count (sample-colors 0 nil))))
        (is (= 0 (count (sample-colors 10 nil)))))

      (testing "should return the corrent number of elements for a non-nil image"
        (is (= 10 (count (sample-colors 10 image-1))))
        (is (= 100 (count (sample-colors 100 image-2))))
        (is (= 1000 (count (sample-colors 1000 image-3))))))))


(deftest pixel-test
  (let [image-1 (javax.imageio.ImageIO/read (io/resource "image-1.gif"))
        image-2 (javax.imageio.ImageIO/read (io/resource "image-2.jpg"))
        image-3 (javax.imageio.ImageIO/read (io/resource "image-3.jpg"))
        image-4 (javax.imageio.ImageIO/read (io/resource "image-4.jpg"))]

    (testing "pixel"
      (testing "should return nil when image=nil"
        (is (nil? (@#'quant/pixel nil 0 0))))

      (testing "should return nil when xy-coord is bad"
        (is (nil? (@#'quant/pixel image-1 -1 0)))
        (is (nil? (@#'quant/pixel image-1 -1 0)))
        (is (nil? (@#'quant/pixel image-1 0 259)))
        (is (nil? (@#'quant/pixel image-1 0 212))))

      (testing "should return rgb-value for legitimate images"
        (is (not (nil? (@#'quant/pixel image-1 0 0))))
        (is (map? (@#'quant/pixel image-1 0 0)))
        (is (map? (@#'quant/pixel image-2 0 0)))
        (is (map? (@#'quant/pixel image-3 0 0)))
        (is (map? (@#'quant/pixel image-4 0 0)))
        (is (contains? (@#'quant/pixel image-4 10 10) :red))
        (is (contains? (@#'quant/pixel image-4 10 10) :blue))
        (is (contains? (@#'quant/pixel image-4 10 10) :green))
        (is (contains? (@#'quant/pixel image-4 10 10) :alpha))))))
