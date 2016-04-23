;;; hack.core
;;;
;;; Entry point for the main process, launches the window and render
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.core
  (:require [cljs.nodejs :as js]
            [hack.electron.electron-main :as e]))


(def browserWindowOptions
  {:height  850
   :title   "hack"
   :width   1400
   :icon    (e/file-url "img/logo_96x96.png")})

(def errorCfg
  {:companyName "kilroySoft Inc."
   :submitURL   "http://example.com/"})

(defn- start-main []
  "Main event loop of app"
  (e/start-app errorCfg browserWindowOptions))

; set main CLI function on start-main
; used to loop in case of HTML file change too
(set! *main-cli-fn* start-main)

(defn -main []
  (e/change-window "index.html"))
