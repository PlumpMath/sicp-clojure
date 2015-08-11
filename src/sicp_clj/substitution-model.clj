(ns sicp-clj.substitution-model)

(defn square [x]
  (* x x))

(defn sos [x y]
  (+ (square x) (square y)))

(sos 3 4)
;; => 25

;; Kinds of expressions
;;  - Numbers
;;  - Symbols
;;  - Lambda expressions
;;  - Definitions
;;  - Conditionals
;;  - Combinations

;; How to evaluate a program:
;; 1. Evaluate some procedure
;; 2. ..by evaluating its operands first then plugging them in
;; 3. ..for every procedure in program

(defn sum [x y]
  (if (= 0 x)
    y
    (sum (dec x) (inc y))))

(sum 3 4)
(sum 2 5)
(sum 1 6)
(sum 0 7)
;; => 7

(defn ssum [x y]
  (if (= 0 x)
    y
    (inc (ssum (dec x) y))))

(ssum 3 4)
(inc (ssum 2 4))
(inc (inc (ssum 1 4)))
(inc (inc (inc (ssum 0 4))))
(inc (inc (inc 4)))
(inc (inc 5))
(inc 6)
;; => 7

;; What are the shapes of theses substitutions? Straight down vs. expand and
;; collapse. The number of steps is ~time, the width is ~space.
;; sum: time = O(x), space = O(1). It is an Iteration.
;; ssum: time = O(x), space = O(x). It is a Linear Recursion, proportional to
;; the input argument in both time and space.

;; An iteration has all of its state in explicit variables. If the program is
;; killed and restarted, it can continue. Recursion keeps some information
;; "under the table", outside of explicit variables given to the program.

(defn fib [n]
  (if (< n 2)
    n
    (+ (fib (- n 1))
       (fib (- n 2)))))
;; time = O(fib(n)), or the mass of the tree
;; space = O(n), or the depth of the tree

;; Tower of Hanoi
(defn move-tower [n from to spare]
  (cond
    (= 0 n) "done"
    :else (do (move-tower (dec n) from spare to)
              (move-tower (dec n) spare to from))))

(move-tower 4 :a :b :c)
(move-tower 3 :a :c :b)
(move-tower 2 :a :b :c)
(move-tower 1 :a :c :b)
