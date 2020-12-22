(ns advent-2020.day05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

;; F => front (0)
;; B => back  (1)
;; L => left  (0)
;; R => right (1)
(def seat "FBFBBFFRLR")

(defn parse-seat [seat]
  (Integer/parseInt (str/replace seat #"F|B|L|R" {"F" "0" "B" "1" "L" "0" "R" "1"}) 2))

(parse-seat seat)
;; => 357

(->> (io/resource "advent_2020/day05/input.txt")
     slurp
     (str/split-lines)
     (map parse-seat)
     (apply max))
;; => 901
