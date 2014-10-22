(ns alg11.core)

(defn read-file-in-vec [f]
  (with-open [rdr (clojure.java.io/reader f)]
         (vec (line-seq rdr))))

(defn read-vec-from-file [f]
  (read-string (str "[" (slurp f) "]")))

(def array-for-test (read-vec-from-file "resources/ArrayForTest.txt"))
(def integer-array (read-vec-from-file "resources/IntegerArray.txt"))

(defn get-half-vectors [full-vector]
  (let [half-size (Math/ceil (/ (count full-vector) 2.0))]
    {:first (subvec full-vector 0 half-size)
     :second (subvec full-vector half-size)}))

;; testing get-half-vectors
;; (get-half-vectors [1 2 3 4 5])

(defn sort-and-count-split-invertions [first-vec second-vec inv]
  (let [first-vec-size (count first-vec)
        second-vec-size (count second-vec)]
    (loop [i 0 j 0 acc inv sorted-vec []]
      (if (= i first-vec-size)
        {:inv acc :sorted-vec (into sorted-vec (subvec second-vec j))}
        (if (= j second-vec-size)
          {:inv acc :sorted-vec (into sorted-vec (subvec first-vec i))}
          (let [i-el (nth first-vec i)
                j-el (nth second-vec j)]
            (if (> i-el j-el)
              (recur i (inc j) (+ acc (- first-vec-size i)) (conj sorted-vec j-el))
              (recur (inc i) j acc (conj sorted-vec i-el)))))))))

;; testing sort-and-count-split-invertions
;; (sort-and-count-split-invertions [4 5 6] [1 2 3] 0)

(defn sort-and-count-invertions [input inv]
  (let [vec-size (count input)]
    (if (= vec-size 1)
      {:inv inv :sorted-vec input}
    (let [vecs       (get-half-vectors input)
          first-vec  (:first vecs)
          second-vec (:second vecs)
          first-prepare  (sort-and-count-invertions first-vec inv)
          second-prepare (sort-and-count-invertions second-vec inv)
          pre-inv        (+ (:inv first-prepare) (:inv second-prepare))
          pre-result     (sort-and-count-split-invertions
                          (:sorted-vec first-prepare)
                          (:sorted-vec second-prepare)
                          pre-inv)]
      {:inv        (:inv pre-result)
       :sorted-vec (:sorted-vec pre-result)}))))

;; testing sort-and-count-invertions
;; (sort-and-count-invertions [3 1 5 2 4 6] 0)

;; (sort-and-count-invertions array-for-test 0)
;; (time (sort-and-count-invertions integer-array 0))
