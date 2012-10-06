(ns map-reduce-kata.movie.year-with-highest-average-score-spec
    (:use
        [speclj.core]
        [map-reduce-kata.movie.year-with-highest-average-score]))

(describe "Finds year with highest movie score."
  (it "Highest movie score in small data set."
      (let [highest-scoring-year (highest-average-movie-score "movies.txt")]
        (should= :1972 (first highest-scoring-year))
        (should= 9.2 (last highest-scoring-year))))

  (it "Highest movie score in large data set."
      (let [highest-scoring-year (highest-average-movie-score "ratings.txt")]
        (should= :1889 (first highest-scoring-year))
        (should= 8.0 (last highest-scoring-year)))))
