(ns advent-2020.day03
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(def demo-input "..##.......
#...#...#..
.#....#..#.
..#.#...#.#
.#...##..#.
..#.##.....
.#.#.#....#
.#........#
#.##...#...
#...##....#
.#..#...#.#")

(def demo-terrain (str/split-lines demo-input))

(def terrain (->> "advent_2020/day03/input.txt"
                  io/resource
                  io/reader
                  line-seq
                  (map vec)))

(defn navigate [terrain slope]
  (let [width (count (first terrain))
        columns (iterate #(mod (+ slope %1) width) 0)
        spots (map nth terrain columns)
        trees (filter #(= \# %1) spots)]
    (count trees)))

;; Part 1 - traverse map using a slope of 3
(navigate terrain 3)
;; => 234

;; Part 2 - multiply series of traversal with different slopes
;; Right 1, down 1.
;; Right 3, down 1. (This is the slope you already checked.)
;; Right 5, down 1.
;; Right 7, down 1.
;; Right 1, down 2.
;; [[1 1] [3 1] [5 1] [7 1] [1 2]]

(*
 (navigate terrain 1)
 (navigate terrain 3)
 (navigate terrain 5)
 (navigate terrain 7)
 (navigate (take-nth 2 terrain) 1))
;; => 5813773056

(comment
  (count (first terrain))

  (map nth terrain (iterate #(mod (+ 3 %) (count (first terrain))) 0))
  )
