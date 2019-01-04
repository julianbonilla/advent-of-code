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

(defn part-2 [])
