(ns quant.ize-test
  (:require [clojure.test :refer :all]
            [quant]
            [quant.ize :as quantize]
            [clojure.test]
            [clojure.java.io :as io]))

(deftest test-k-colors
  (let [image-1 (javax.imageio.ImageIO/read (io/resource "image-1.gif"))
        image-2 (javax.imageio.ImageIO/read (io/resource "image-2.jpg"))
        image-3 (javax.imageio.ImageIO/read (io/resource "image-3.jpg"))
        image-4 (javax.imageio.ImageIO/read (io/resource "image-4.jpg"))]
    (testing "k-colors"
      (testing "should return nil values"
        (is (nil? (quantize/k-colors 0 nil))))

      (testing "should return a proper sized list for k-colors"
        (is (= 1 (count (quantize/k-colors 1 image-2))))
        (is (= 2 (count (quantize/k-colors 2 image-2))))
        (is (= 200 (count (quantize/k-colors 200 image-2))))
        )


        )))
