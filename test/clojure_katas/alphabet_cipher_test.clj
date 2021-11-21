(ns clojure-katas.alphabet-cipher-test
  (:require [clojure.test :refer :all]
            [clojure-katas.alphabet-cipher :refer :all]
            [matcher-combinators.test :refer [match?]]))

(deftest row-test
  (is (match? {\a "a" \b "b" \c "c"}
              (row \a "abc")))

  (is (match? {\a "b" \b "c" \c "a"}
              (row \b "abc")))

  (is (match? {\a "c" \b "a" \c "b"}
              (row \c "abc"))))

(deftest substitution-table-test
  (is (match? {\a {\a "a" \b "b" \c "c"}
          \b {\a "b" \b "c" \c "a"}
          \c {\a "c" \b "a" \c "b"}}
         (substitution-table "abc"))))

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

(deftest encode-test
  (is (= {:keyword "sconessconessco"
          :text    "meetmebythetree"
          :encoded "egsgqwtahuiljgs"}

         (encode "scones"
                 "meetmebythetree"
                 (substitution-table "abcdefghijklmnopqrstuvwxyz")))))
