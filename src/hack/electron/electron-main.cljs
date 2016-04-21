;;; hack.electron.electron-main
;;;
;;; electron library interface for main process
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.electron.electron-main
  (:require [cljs.nodejs :as js]))

(def path (js/require "path"))
(def root "/../../../../")
(defn file-url [filename]
  "return normalized   "
  (str "file://"
     (.resolve path (str (js* "__dirname")
                         root filename))))

(def electron (js/require "electron"))
(def crash-reporter (js/require "crash-reporter"))
(def os (js/require "os"))
(def fs (js/require "fs-extra"))
(def shell (js/require "shell"))
; (def json (js/require (file-url "package.json")))
(def platform (.-platform js/process))
(def app (.-app electron))
(def ipcMain (.-ipcMain app))
(def dialog (.-dialog app))
(def BrowserWindow (.-BrowserWindow electron))
(def Menu (.-Menu electron))
(def MenuItem (.-MenuItem electron))

(def *win* (atom nil))
