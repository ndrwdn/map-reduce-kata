(ns map-reduce-kata.movie-ratings-by-year
  (:require
    [map-reduce-kata.movie-data-source :as s]))

(defn- convert-data-to-year-rating-map
  "Converts a movie data hashmap to a year to rating hashmap."
  [movie-data]
  (let [year (keyword (movie-data :year))
        rating (Double/parseDouble (movie-data :score))]
    (hash-map year rating)))

(defn- movie-ratings-per-year
  "Gets a collection of hashmaps of year to rating for that year."
  [data]
  (map convert-data-to-year-rating-map data))

(defn- get-max-rating
  "Creates a hashmap based on year-maximums with the correct
   maximum rating for the year in the year-rating hashmap."
  [year-maximums year-rating]
  (let [year (first (keys year-rating))
        rating (year-rating year)
        current-max (year-maximums year 0.0)
        new-max (max current-max rating)]
    (assoc year-maximums year new-max)))

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
