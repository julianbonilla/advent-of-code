(ns advent-2018.day01
  (:require [clojure.java.io :as io]))

(def data (mapv
           (comp read-string str)
           (slurp (io/resource "advent_2017/day01/input"))))

(defn count-em [d]
  (->>
   (partition 2 1 (conj (vec d) (first d)))
   (filter (partial apply =))
   (map first)
   (apply +)))

(defn part-1 []
  (count-em data))

(defn part-2 [])

(comment
  (count-em [1 1 2 2])
  (count-em [1 1 1 1])
  (count-em [1 2 3 4])
  (count-em [9 1 2 1 2 1 2 9])
  (part-1))
