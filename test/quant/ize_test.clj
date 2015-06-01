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
        ; (is (= 1 (count (quantize/k-colors 1 image-2))))
        ; (is (= 2 (count (quantize/k-colors 2 image-2))))
        ; (is (= 200 (count (quantize/k-colors 200 image-2))))
        )


        )))

(defn abs [n] (max n (- n)))

(deftest test-group-points
  (testing "group points"
    (testing "should "
      (is (nil? 

(deftest test-k-means
  (let [points-1 [1 2 3 4 5 6 7 8 9]]
    (testing "k-means"
      (testing "should return nil, if nil"
       ;(is (nil? (quantize/k-means 3 nil abs 100)))
       ;(is (nil? (quantize/k-means 3 '() abs 100)))
       ;(is (nil? (quantize/k-means 3 [] abs 100)))
       ;(is (nil? (quantize/k-means 3 nil abs 100))))
       )
      (testing "shoudl return proper number of clusters"
       ;(is (= 1 (count (quantize/k-means 1 points-1 abs 100))))
        
        ))))

(deftest test-argmin
  (testing "argmin"
    (testing "should return nil for empty lists"
      (is (nil? (quantize/argmin nil)))
      (is (nil? (quantize/argmin '())))
      (is (nil? (quantize/argmin [])))
      (is (nil? (quantize/argmin {}))))
    (testing "should return the proper index for lists"
      (is (= 0 (quantize/argmin '(3))))
      (is (= 1 (quantize/argmin '(3 0))))
      (is (= 9 (quantize/argmin [0 1 2 3 4 5 6 7 8 -1])))
      (is (= 5 (quantize/argmin [0 1 2 3 4 -5 6 7 8 -1]))))
    (testing "should return the proper key for a map"
      (is (= :red (quantize/argmin {:red 1 :blue 1000})))
      (is (= :blue (quantize/argmin {:red 100000 :blue 1000}))))))
