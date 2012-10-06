(ns map-reduce-kata.movie-scores-by-year
  (:require
    [map-reduce-kata.movie-data-source :as s]))

(defn- map-data-to-year
  "Converts a movie data hashmap to a year to score hashmap."
  [movie-data]
  (hash-map (movie-data :year) movie-data))

(defn- movie-scores-per-year
  "Gets a collection of hashmaps of year to score for that year."
  [data]
  (map map-data-to-year data))

(defn- get-max-score
  "Creates a hashmap based on year-maximums with the correct
   maximum score for the year in the year-score hashmap."
  [year-maximums movie-to-year-map]
  (let [movie-data (first (vals movie-to-year-map))
        year (movie-data :year)
        score (movie-data :score)

        current-movie-data (year-maximums year {:score 0.0})
        current-max (current-movie-data :score)

        max-movie-data (if (> score current-max)
                         movie-data
                         current-movie-data)]
    (assoc year-maximums year max-movie-data)))

(defn- max-score-per-year
  "Gets the maximum score for a year."
  [scores]
  (reduce get-max-score {} scores))

(defn movie-scores
  "Gets a hashmap of year to highest scored movie for that year."
  [fname]
  (let [data (s/read-and-parse-data fname)
        scores (movie-scores-per-year data)]
    (max-score-per-year scores)))
