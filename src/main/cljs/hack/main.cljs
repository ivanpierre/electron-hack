(ns hack.main
  (:require [hack.render :as render]))

(enable-console-print!)
(println "passe dans hack.main")
(def electron (js/require "electron"))
(render/init!)
