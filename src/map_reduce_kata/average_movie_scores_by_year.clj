(ns map-reduce-kata.average-movie-scores-by-year
  (:require
    [map-reduce-kata.movie-data-source :as s]))

(defn- get-year-and-score
  "Gets a map of year and score."
  [movie]
  {(movie :year) (movie :score)})

(defn- map-score-to-year
  "Returns a hashmap of score to year."
  [data]
  (map get-year-and-score data))

(defn- make-score-list
  "Creates a list of scores by year."
  [scores-by-year year-and-score]
  (let [year (first (keys year-and-score))
        current-score-list (scores-by-year year [])
        new-score-list (conj current-score-list (year-and-score year))]
    (assoc scores-by-year year new-score-list)))

(defn- reduce-scores-to-list-by-year
  "Takes the individual hashmaps per year/score and
   creates one hashmap with list of scores by year."
  [years-and-scores]
  (reduce make-score-list {} years-and-scores))

(defn- averager
  "Averages list of scores."
  [[year score-list]]
  (let [avg-score (/ (reduce + score-list) (count score-list))]
    {year avg-score}))

(defn- average-scores-list
  "Averages the lists of scores."
  [list-of-scores-by-year]
  (reduce conj {} (map averager list-of-scores-by-year)))

(defn average-movie-scores
  "Gets a hashmap of year to average movie score for that year."
  [fname]
  (let [data (s/read-and-parse-data fname)
        years-and-scores (map-score-to-year data)
        list-of-scores-by-year (reduce-scores-to-list-by-year years-and-scores)]
    (average-scores-list list-of-scores-by-year)))
