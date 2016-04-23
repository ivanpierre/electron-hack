;;; hack.core
;;;
;;; Entry point for the main process, launches the window and render
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.core
  (:require [cljs.nodejs :as js]
            [hack.electron.electron-main :as e]))

; enable printf redirect to the console
(enable-console-print!)

; "Linux" or "Darwin" or "Windows_NT"
(println (str "Init application on " (.type e/os) "."))

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
  (e/start-crash-reporter errorCfg)
  (.. e/app
    (on "window-all-closed"
      (fn []
;       (if (not= (.platform js/process) "darwin")
        (.quit e/app))))
  (.. e/app
    (on "ready"
      (fn []
        (e/open-window browserWindowOptions)
        (println (str "Start application on " (.type e/os) "."))))))

; set main CLI function on start-main
(set! *main-cli-fn* start-main)

(defn -main []
  (e/change-window "index.html"))
