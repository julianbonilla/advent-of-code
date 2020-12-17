(ns advent-2020.day04
  (:require [clojure.string :as str]
            [clojure.walk :as walk]
            [clojure.java.io :as io]))

(def demo-input "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
byr:1937 iyr:2017 cid:147 hgt:183cm

iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
hcl:#cfa07d byr:1929

hcl:#ae17e1 iyr:2013
eyr:2024
ecl:brn pid:760753108 byr:1931
hgt:179cm

hcl:#cfa07d eyr:2025 pid:166559648
iyr:2011 ecl:brn hgt:59in")

(def demo-passport "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm")

;; Swap args to str/split for thread last macro
(defn split [re s]
  (str/split s re))

(defn parse-passport [passport]
  (->> passport
       (split #"\s")
       (map #(split #":" %))
       (into {})
       walk/keywordize-keys))

(parse-passport demo-passport)
;; => {:ecl "gry", :pid "860033327", :eyr "2020", :hcl "#fffffd", :byr "1937", :iyr "2017", :cid "147", :hgt "183cm"}
;; => {"ecl" "gry", "pid" "860033327", "eyr" "2020", "hcl" "#fffffd", "byr" "1937", "iyr" "2017", "cid" "147", "hgt" "183cm"}
;; => (["ecl" "gry"] ["pid" "860033327"] ["eyr" "2020"] ["hcl" "#fffffd"] ["byr" "1937"] ["iyr" "2017"] ["cid" "147"] ["hgt" "183cm"])
;; => ["ecl:gry" "pid:860033327" "eyr:2020" "hcl:#fffffd" "byr:1937" "iyr:2017" "cid:147" "hgt:183cm"]

(defn valid-passport? [{:keys [pid ecl eyr hcl byr iyr hgt]}]
  (and pid ecl eyr hcl byr iyr hgt))

(valid-passport? (parse-passport demo-passport))
;; => "183cm"

;; Part 1 - count the number of valid passports
(->> (io/resource "advent_2020/day04/input.txt")
     slurp
     (split #"\n{2}")
     (map parse-passport)
     (filter valid-passport?)
     count)
;; => 237
