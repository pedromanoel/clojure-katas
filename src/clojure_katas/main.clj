(ns clojure-katas.main)

(defn repeat-keyword [keyword char-count]
  (->> (cycle keyword)
       (take char-count)
       (apply str)))

(defn row [char alphabet]
  (let [row-values (drop-while (partial not= char) (cycle alphabet))]
    (->> (map #(vector %1 (str %2)) alphabet row-values)
         (into {}))))

(defn substitution-table [alphabet]
  (into {} (map #(vector %1 (row %2 alphabet)) alphabet alphabet)))

(defn encode [keyword text substitution-table]
  (let [keyword-repeated (repeat-keyword keyword (count text))]
    {:keyword keyword-repeated
     :text    text
     :encoded (->> text
                   (map (fn [a b] (get-in substitution-table [a b]))
                        keyword-repeated)
                   (reduce str ""))}))
