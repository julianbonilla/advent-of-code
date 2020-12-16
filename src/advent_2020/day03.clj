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

;; Traverse map using a slope of 3
(navigate terrain 3)
;; => 234

(comment
  (count (first terrain))

  (map nth terrain (iterate #(mod (+ 3 %) (count (first terrain))) 0))
  )
