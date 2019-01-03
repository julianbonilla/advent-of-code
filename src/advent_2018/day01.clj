(ns advent-2018.day01
  (:require
   [clojure.string :as str]
   [clojure.java.io :as io]))

(def input (slurp (io/resource "advent_2018/day01/input")))

(def nums
  (-> input
      (str/split #"\n")
      (->> (map #(Integer/parseInt %)))))

(defn part-1 []
  (reduce + 0 nums))

#_(part-1)
