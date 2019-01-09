(ns advent-2018.day01
  (:require
   [clojure.java.io :as io]
   [clojure.string :as str]))

(def input (slurp (io/resource "advent_2018/day01/input")))

(def nums
  (-> input
      (str/split #"\n")
      (->> (map #(Integer/parseInt %)))))

(def numz
  (map #(Integer/parseInt %) (str/split input #"\n")))

(defn part-1 []
  (reduce + 0 nums))

#_(part-1)

;; +1, -2, +3, +1
;; keep frequency count
;; if frequency count seen twice return the frequency
;; else recur

(def data [+1, -2, +3, +1])

(defn part-two [data]
  (loop [freqs #{}
         acc 0
         coll (cycle data)
         next-sum (+ acc (first coll))]
    (if (contains? freqs next-sum)
      next-sum
      (recur (conj freqs next-sum)
             next-sum
             (rest coll)
             (+ next-sum (first (rest coll)))))))

(part-two data)
(part-two nums)


