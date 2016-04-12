(ns hack.hack
  (:require [cljs.pprint :refer [pprint]]))

(defn req
  [name]
  (js/require (str name)))

(declare objkeys)

(defn getobj [key obj]
  (let [val (js->clj (get obj key))]
    (println "type is " (type val))
    (if (seq? val)
      [(keyword key) (objkeys val)]
      [(keyword key) val])))

(defn objkeys
  [obj]
  (let [obj (js->clj obj)
        k (sort (keys obj))]
    (reduce conj {}
            (map #(getobj % obj) k))))

(defn my [name]
  (objkeys (req name)))

(def o (objkeys 'electron))
