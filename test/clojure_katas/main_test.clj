(ns clojure-katas.main-test
  (:require [clojure.test :refer :all]
            [matcher-combinators.test :refer [match?]]))

(defn repeat-keyword [keyword char-count]
  (->> (cycle keyword)
       (take char-count)
       (apply str)))

(deftest keyword-repeated-test
  (testing "repeat the keyword until a count is reached"
    (is (= ""
           (repeat-keyword "abc" 0)))
    (is (= "a"
           (repeat-keyword "abc" 1)))
    (is (= "ab"
           (repeat-keyword "abc" 2)))
    (is (= "abc"
           (repeat-keyword "abc" 3)))
    (is (= "abcab"
           (repeat-keyword "abc" 5)))))

(def substitution-table
  {\s {\b "t"
       \e "w"
       \m "e"
       \r "j"
       \t "l"}
   \c {\e "g"
       \y "a"}
   \o {\e "s"
       \t "h"}
   \n {\h "u"
       \t "g"}
   \e {\e "i"
       \m "q"}})

(defn encode [keyword text substitution-table]
  (let [keyword-repeated (repeat-keyword keyword (count text))]
    {:keyword keyword-repeated
     :text    text
     :encoded (->> text
                   (map (fn [a b] (get-in substitution-table [a b]))
                        keyword-repeated)
                   (reduce str ""))}))

(deftest encode-test
  (is (= {:keyword "sconessconessco"
          :text    "meetmebythetree"
          :encoded "egsgqwtahuiljgs"}

         (encode "scones"
                 "meetmebythetree"
                 substitution-table))))
