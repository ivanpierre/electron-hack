;;; hack.render
;;;
;;; Render frame and main figwheel entry point
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.render
  (:require [reagent.core :as reagent]
            [cljsjs.react :as r]
            [hack.electron.electron-front :as e]))

(defn main-page
  []
  [:div "Hello World!"])

(defn mount-root
  []
  (reagent/render [main-page] js/document))
  ; (reagent/render [main-page] (.getElementById js/document "app")))

(defn init!
  []
  (mount-root))

(init!)
