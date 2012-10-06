(ns map-reduce-kata.movie-data-source
  (:require
    [clojure.string :as cstr]))

(defn- read-data
  "Reads data from specified file."
  [fname]
  (let [data-path (str "data/" fname)]
    (slurp data-path)))

(defn- transform-data
  "Takes a collection of data values and
   transforms them to the correct data-types."
  [data]
  (let [score (Double/parseDouble (first data))
        title (second data)
        year (keyword (last data))]
    (zipmap [:score :title :year] (vector score title year))))

(defn- map-data
  "Takes a collection of vectors of data and
   turns it into a vector hashmaps with field names."
  [data]
  (let [data-without-vote-counts (map rest data)]
    (map transform-data data-without-vote-counts)))

(defn- parse-data
  "Parses data into vector of maps"
  [data]
  (let [records (cstr/split data #"\n")
        fields (map #(cstr/split % #"\t") records)
        trimmer #(map cstr/trim %)
        trimmed (map trimmer fields)]
    (map-data trimmed)))

(defn read-and-parse-data
  "Reads the raw data from specified file and parses it."
  [fname]
  (let [raw-data (read-data fname)]
    (parse-data raw-data)))
