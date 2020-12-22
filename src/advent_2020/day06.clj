(ns advent-2020.day06
  (:require [clojure.java.io :as io]
            [clojure.set :as set]
            [clojure.string :as str]))

(def demo-input "abc

a
b
c

ab
ac

a
a
a
a

b")

;; Swap args to str/split for thread last macro
(defn split [re s]
  (str/split s re))

(def input
  (->> (io/resource "advent_2020/day06/input.txt")
       slurp
       (split #"\n\n")))

(defn count-votes [input]
  (-> input
      set
      (disj \newline)
      count))

;; Part 1 - sum the votes for each group
(->> input
     (map count-votes)
     (reduce +))
;; => 6686

;; Part 2 - sum common votes in groups
(defn count-votes2 [input]
  (->> input
       (split #"\n")
       (map set)
       (apply set/intersection)
       count))

(->> input
     (map count-votes2)
     (reduce +))
;; => 3476

(comment
  (->> demo-input
      (split #"\n\n"))

  (count-votes "psyjxulrdtfe\njeusdrlxyftp")

  (count-votes2 "psyjxulrdtfe\njeusdrlxyftp")
  )
