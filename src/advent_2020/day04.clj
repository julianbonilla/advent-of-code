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

;; Part 1 - count the number of valid passports
(defn valid-passport? [{:keys [pid ecl eyr hcl byr iyr hgt]}]
  (and pid ecl eyr hcl byr iyr hgt))

(valid-passport? (parse-passport demo-passport))
;; => "183cm"

;; Part 2 - valid passports now have more validation
(defn valid-passport2? [{:keys [pid ecl eyr hcl byr iyr hgt]}]
  (and pid ecl eyr hcl byr iyr hgt
       ;; byr (Birth Year) - four digits; at least 1920 and at most 2002.
       (<= 1920 (Integer/parseInt byr) 2002)
       ;; iyr (Issue Year) - four digits; at least 2010 and at most 2020.
       (<= 2010 (Integer/parseInt iyr) 2020)
       ;; eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
       (<= 2020 (Integer/parseInt eyr) 2030)
       ;; hgt (Height) - a number followed by either cm or in:
       ;; If cm, the number must be at least 150 and at most 193.
       ;; If in, the number must be at least 59 and at most 76.
       (let [[_ number measurement] (re-matches #"(\d+)(cm|in)" hgt)]
         (case measurement
           "cm" (<= 150 (Integer/parseInt number) 193)
           "in" (<= 59 (Integer/parseInt number) 76)
           false))
       ;; hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
       (re-matches #"#[0-9a-f]{6}" hcl)
       ;; ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
       (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} ecl)
       ;; pid (Passport ID) - a nine-digit number, including leading zeroes
       (re-matches #"[0-9]{9}" pid)))

(->> (io/resource "advent_2020/day04/input.txt")
     slurp
     (split #"\n{2}")
     (map parse-passport)
     ;; (filter valid-passport?)
     (filter valid-passport2?)
     count)
;; => Part 2: 172
;; => Part 1: 237
