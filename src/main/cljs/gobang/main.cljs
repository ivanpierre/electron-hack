(ns gobang.main
  (:require [gobang.render :as render]
            [hack.hack :refer [objkeys o getobj]]
            [cljs.pprint :refer [pprint]]))
(def electron (js/require "electron"))

(render/init!)
