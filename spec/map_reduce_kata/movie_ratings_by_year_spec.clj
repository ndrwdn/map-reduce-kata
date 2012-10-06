(ns map-reduce-kata.movie-ratings-by-year-spec
    (:use
        [speclj.core]
        [map-reduce-kata.movie-ratings-by-year]))

(describe "Finds highest rated movie for each year"
  (it "Highest rated movie for 1994"
    (should= 9.2 ((movie-ratings "movies.txt") :1994))))
