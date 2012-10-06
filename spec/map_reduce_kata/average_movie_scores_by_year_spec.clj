(ns map-reduce-kata.average-movie-scores-by-year-spec
    (:use
        [speclj.core]
        [map-reduce-kata.average-movie-scores-by-year]))

(defn- round
  [n]
  (/ (Math/round (* n 1000)) 1000.0))

(describe "Finds average movie scores for each year"
  (it "Average movie score for 1994"
    (should= 7.656 (round ((average-movie-scores "movies.txt") :1994)))))
