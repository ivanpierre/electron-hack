(ns hack.core
    (:require [cljs.nodejs :as nodejs]))

(def path (nodejs/require "path"))
(def Electron (nodejs/require "electron"))
(def BrowserWindow (nodejs/require "browser-window"))
(def crash-reporter (nodejs/require "crash-reporter"))
(def Os (nodejs/require "os"))
(def *win* (atom nil))
(def app (.-app Electron))

(defn -main []
  "Main event loop of app"
  ; manage errors
  (.start crash-reporter (clj->js {:companyName "Your Company Name"
                                   :submitURL   "http://example.com/"}))

  ; error listener
  (.on nodejs/process "error"
       (fn [err] (.log js/console err)))

  ; window all closed listener
  (.on app "window-all-closed"
       (fn [] (if (not= (.-platform nodejs/process) "darwin")
                (.quit app))))

  ; ready listener
  (.on app "ready"
       (fn []
         (reset! *win* (BrowserWindow. (clj->js {:width 800 :height 600})))

         ;; Load main HTML file
         (.loadURL @*win* (str "file://" (.resolve path (js* "__dirname") "../../../index.html")))

         ;; manage MainWindows close
         (.on @*win* "closed" (fn [] (reset! *win* nil))))))

; enable printf redirect to the console
(nodejs/enable-util-print!)

; "Linux" or "Darwin" or "Windows_NT"
(.log js/console (str "Start descjop application on " (.type Os) "."))

; set main client function on main loop
(set! *main-cli-fn* -main)
