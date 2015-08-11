(ns sicp-clj.overview)

(+ (* 3 5)
   (* 47
      (- 20 6.8))
   12)

(def a (* 5 5))

(def b (+ a (* 5 a)))

(def square (fn [x] (* x x)))

(defn square [x] (* x x))

(defn abs [x]
  (cond
    (< x 0) (- x)
    (= x 0) 0
    (> x 0) x))

(defn abs [x]
  (if (< x 0)
    (- x)
    x))

;; To find sqrt(x) approximately:
;; - Make a guess G
;; - Improve guess by averaging G and X
;; - Keep improving G until it is good enough
;; - Use 1 as an initial guess

(defn improve [guess x]
  (/ (+ guess (/ x guess))
     2))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [x]
  (sqrt-iter 1 x))

(sqrt 2.0)
;; => 1.4142156862745097

(defn sqrt [x]
  (let [good-enough? (fn [guess x] (< (abs (- (square guess) x)) 0.001))
        improve (fn [guess x] (/ (+ guess (/ x guess)) 2))
        sqrt-iter (fn [guess x]
                    (if (good-enough? guess x)
                      guess
                      (sqrt-iter (improve guess x) x)))]
    (sqrt-iter 1.0 x)))

(sqrt 2.0)
;; => 1.4142156862745097

