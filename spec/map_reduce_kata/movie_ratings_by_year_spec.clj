(ns map-reduce-kata.movie-ratings-by-year-spec
    (:use
        [speclj.core]
        [map-reduce-kata.movie-ratings-by-year]))

(describe "Finds highest rated movie for each year"
  (it "Highest rated movie for 1994"
    (should= "The Shawshank Redemption" (((movie-ratings "movies.txt") :1994) :title))))
