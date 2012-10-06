(ns map-reduce-kata.word-count.mapper-spec
    (:use
        [speclj.core]
        [map-reduce-kata.word-count.mapper]))

(describe "Word counter"
  (it "Find number of instances of words in collection of strings."
      (let [data ["tom kim ian" "nancy bob tom kim" "ian"]
            result {:bob 1 :nancy 1 :ian 2 :kim 2 :tom 2}]
        (should= result (word-count data)))))

