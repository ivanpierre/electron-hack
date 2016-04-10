(ns gobang.core
  (:require [reagent.core :as reagent]
            [cljsjs.react]))

(defn main-page
  []
  [:div "Hello world!"])

(defn mount-root
  []
  (reagent/render [main-page] (.getElementById js/document "app")))

(defn init!
  []
  (mount-root))
