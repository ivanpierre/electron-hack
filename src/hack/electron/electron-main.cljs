;;; hack.electron.electron-main
;;;
;;; electron library helper for main process
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.electron.electron-main
  (:require [cljs.nodejs :as js]))

; enable printf redirect to the console
(enable-console-print!)

; Main electron access
(def electron (js/require "electron"))

; for both processes
(def clipboard (.-clipboard electron))
(def crashReporter (.-crashReporter electron))
(def deprecations (.-deprecations electron))
(def nativeImage (.-nativeImage electron))
(def shell (.-shell electron))

; for main process
(def app (.-app electron))
(def autoUpdater (.-autoUpdater electron))
(def BrowserWindow (.-BrowserWindow electron))
(def contentTracing (.-contentTracing electron))
(def dialog (.-dialog electron))
(def ipcMain (.-ipcMain electron))
(def globalShortcut (.-globalShortcut electron))
(def Menu (.-Menu electron))
(def MenuItem (.-MenuItem electron))
(def powerSaveBlocker (.-powerSaveBlocker electron))
(def session (.-session electron))
(def Tray (.-Tray electron))

; needs app to be ready
(def powerMonitor)
(def protocol)
(def screen)

; Hidden internal modules
(def NavigationController (.-NavigationController electron))
(def webContents (.-webContents electron))

; Complementary modules
(def os (js/require "os"))
(def fs (js/require "fs-extra"))
(def path (js/require "path"))

(defn file-url [filename]
  "return normalized url from /app"
  (let [root "/../../../../"]
    (str "file://"
       (.resolve path (str (js* "__dirname")
                           root filename)))))

(def platform (.-platform js/process))
; (def package (js/require (file-url "package.json"))) ; Package content
(def *win* (atom nil))
(def *options* (atom nil))
(def *url* (atom "index.html"))

(defn start-crash-reporter [errorCfg]
  "Start crashReporter"
  (.. crashReporter
    (start (clj->js errorCfg)))
  (.. js/process
    (on "error"
       (fn [err] (println "crashReporter : " err)))))

(defn open-window
 ([]
  (open-window @*options*))

 ([options]
  (enable-console-print!)
  (println (str "Opening window " @*url* "."))
  (if (not= options @*options*)
    (do
      (reset! *win* (BrowserWindow. (clj->js options)))
      (reset! *options* options)))
  (if @*url* ; only load HTML file if URL is specified
    (.. @*win*
      (loadURL (file-url @*url*))))
  (.. @*win*
    (on "closed"
      (fn [] (reset! *win* nil))))
  (println (str "Window " @*url* " opened."))))

(defn change-window [url]
  (reset! *url* url)
  (*main-cli-fn*))

(defn- start-app [error browser]
  "Main event loop of app"
  (enable-console-print!)
  (println (str "Init application on " (.type os) "."))
  (start-crash-reporter error)
  (.. app
    (on "window-all-closed"
      (fn []
;       (if (not= (.platform js/process) "darwin")
        (.quit app))))
  (.. app
    (on "ready"
      (fn []
        (open-window browser)
        (set! powerMonitor (.-powerMonitor electron))
        (set! protocol (.-protocol electron))
        (set! screen (.-screen electron))
        (println (str "Start application on " (.type os) "."))))))
