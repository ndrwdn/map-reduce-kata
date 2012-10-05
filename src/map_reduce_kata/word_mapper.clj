(ns map-reduce-kata.word-mapper
  (:require
    [clojure.string :as cstr]))

(defn- word-mapper
  "Maps collection of strings to collection of keywords"
  [col]
  (let [splitter #(cstr/split % #" ")
        flattened (flatten (map splitter col))]
    (map keyword flattened)))

(defn- word-reducer
  "Counts number of instances of words in collection."
  [col]
  (let [reducer #(assoc %1 %2 (inc (%1 %2 0)))]
    (reduce reducer {} col)))

(defn word-count
  "Count words"
  [col]
  (word-reducer (word-mapper col)))
