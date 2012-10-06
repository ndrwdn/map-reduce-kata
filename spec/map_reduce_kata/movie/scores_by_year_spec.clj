(ns map-reduce-kata.movie.scores-by-year-spec
    (:use
        [speclj.core]
        [map-reduce-kata.movie.scores-by-year]))

(describe "Finds highest scored movie for each year"
  (it "Highest scored movie for 1994"
    (should= "The Shawshank Redemption" (((movie-scores "movies.txt") :1994) :title))))
