(ns map-reduce-kata.movie.data-source-spec
    (:use
        [speclj.core]
        [map-reduce-kata.movie.data-source]))

(describe "Read and parse movie data"
  (it "Reads the data"
    (should= 999 (count (read-and-parse-data "movies.txt")))))
