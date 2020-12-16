(ns advent-2020.day02
  (:require [clojure.java.io :as io]))

(def demo-input "1-3 a: abcde
1-3 b: cdefg
2-9 c: ccccccccc")

(defn parse-long [l]
  (Long/parseLong l))

(defn parse-line [s]
  (let [[_ min max char password] (re-find #"(\d+)-(\d+) (.): (.*)" s)]
    [(parse-long min) (parse-long max) (first char) password]))

(def input
  (map parse-line (line-seq (io/reader (io/resource "advent_2020/day02/input.txt")))))

(defn valid-password? [[min max char password]]
  (<= min (get (frequencies password) char 0) max))

;; Part 1
(count (filter valid-password? input))
;; => 465

;; Part 2
(defn valid-password2? [[min max char password]]
  (let [position1 (= (nth password (dec min)) char)
        position2 (= (nth password (dec max)) char)]
    (not= position1 position2)))

(count (filter valid-password2? input))
;; => 294

(comment
  (re-find #"(\d+)-(\d+) (.): (.*)" "1-3 a: abcde")
  ;; => ["1-3 a: abcde" "1" "3" "a" "abcde"]

  (parse-line "1-3 a: abcde")
  ;; => [1 3 \a "abcde"]

  (valid-password? (parse-line "1-3 a: abcde"))
  )
