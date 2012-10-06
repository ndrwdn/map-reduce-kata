(ns map-reduce-kata.movie.year-with-highest-average-score
  (:require
    [map-reduce-kata.movie.data-source :as s]
    [map-reduce-kata.movie.average-scores-by-year :as avg]))

(defn- compare-scores
  "Compares two year's scores."
  [highest-year-score year-score]
  (let [current-score (last year-score)

        highest-score (or (last highest-year-score) 0.0)

        new-highest-score (if (> current-score highest-score)
                            year-score
                            highest-year-score)]
    new-highest-score))

(defn- find-highest-scoring-year
  "Maps score and year to dummy key value."
  [average-scores]
  (reduce compare-scores [] average-scores))

(defn highest-average-movie-score
  "Gets a hashmap of year to average movie score for that year."
  [fname]
  (let [average-scores-by-year (avg/average-movie-scores fname)]
    (find-highest-scoring-year average-scores-by-year)))
