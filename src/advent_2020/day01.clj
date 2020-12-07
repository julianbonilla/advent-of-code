(ns advent-2020.day01
  (:require [clojure.java.io :as io]))

;; find the two entries that sum to 2020 and then multiply those two numbers together
;; 1721 * 299 = 514579
(def demo-input [1721
                 979
                 366
                 299
                 675
                 1456])

(def input
  (map #(Long/parseLong %)
   (line-seq (io/reader (io/resource "advent_2020/day01/input")))))

;; Part 1
(set
 (for [x input
       y input
       :when (= 2020 (+ x y))]
   (* x y)))
;; => #{514579}
;; => #{786811}

;; Part 2
(set
 (for [x input
       y input
       z input
       :when (= 2020 (+ x y z))]
   (* x y z)))
;; => #{199068980}
