(ns hack.electron
  (:require [cljs.nodejs :as js]))

(def path (js/require "path"))
(def Electron (js/require "electron"))
(def crash-reporter (js/require "crash-reporter"))
(def Os (js/require "os"))
(def app (.-app Electron))
(def fs (js/require "fs-extra"))
(def shell (js/require "shell"))
(def json (js/require (str (js* "__dirname" "/package.json"))))
(def ipc (.-ipcMain app))
(def dialog (.-dialog app))
(def BrowserWindow (.-BrowserWindow app))
(def Menu (.-Menu app))
(def MenuItem (.-MenuItem app))
(def platform (.-platform js/process))

(def *win* (atom nil))

(defn file-url [filename]
  (str "file://"
     (.resolve path (str (js* "__dirname" filename)))))
