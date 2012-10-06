(ns map-reduce-kata.movie-ratings-by-year
  (:require
    [map-reduce-kata.movie-data-source :as s]))

(defn- map-data-to-year
  "Converts a movie data hashmap to a year to rating hashmap."
  [movie-data]
  (hash-map (movie-data :year) movie-data))

(defn- movie-ratings-per-year
  "Gets a collection of hashmaps of year to rating for that year."
  [data]
  (map map-data-to-year data))

(defn- get-max-rating
  "Creates a hashmap based on year-maximums with the correct
   maximum rating for the year in the year-rating hashmap."
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

(defn- max-rating-per-year
  "Gets the maximum rating for a year."
  [ratings]
  (reduce get-max-rating {} ratings))

(defn movie-ratings
  "Gets a hashmap of year to highest rated movie for that year."
  [fname]
  (let [data (s/read-and-parse-data fname)
        ratings (movie-ratings-per-year data)]
    (max-rating-per-year ratings)))
