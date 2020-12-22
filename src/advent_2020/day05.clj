(ns advent-2020.day05
  (:require [clojure.string :as str]
            [clojure.java.io :as io]))

;; F => front (0)
;; B => back  (1)
;; L => left  (0)
;; R => right (1)
(def seat "FBFBBFFRLR")

(defn parse-seat [seat]
  (Integer/parseInt
   (str/replace seat #"F|B|L|R" {"F" "0" "B" "1" "L" "0" "R" "1"})
   2))

(defn parse-seat2 [seat]
  (-> seat
      (str/replace #"F|L" "0")
      (str/replace #"B|R" "1")
      (Integer/parseInt 2)))

(parse-seat seat)
;; => 357

(defn read-input [resource]
  (->> resource
       slurp
       str/split-lines))

;; Part 1 - what's the highest seat
(->> (read-input (io/resource "advent_2020/day05/input.txt"))
     (map parse-seat2)
     (apply max))
;; => 901

;; Part 2 - what's your seat?
(->> (read-input (io/resource "advent_2020/day05/input.txt"))
     (map parse-seat2)
     sort
     (partition 2 1)
     (filter (fn [[x y]] (= (inc x) (dec y)))) ;; => ((660 662))
     first
     first
     inc)
;; => 661
