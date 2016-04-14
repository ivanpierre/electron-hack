(ns gobang.render
  (:require [reagent.core :as reagent]
            [cljsjs.react :as r]
            [gobang.hack :as h]))

(defn main-page
  []
  [:div "Hello World!"])

(defn mount-root
  []
  (reagent/render [main-page] (.getElementById js/document "app")))

(defn init!
  []
  (mount-root))
