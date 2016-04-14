(ns gobang.main
  (:require [gobang.render :as render]))

(enable-console-print!)

(def electron (js/require "electron"))

(render/init!)
