;;; hack.electron.electron-front
;;;
;;; electron library helper for main process
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.electron.electron-front
  (:require [cljs.nodejs :as js]))

; Main electron access
(def electron (js/require "electron"))

; for both processes
(def clipboard (.-clipboard electron))
(def crashReporter (.-crashReporter electron))
(def deprecations (.-deprecations electron))
(def nativeImage (.-nativeImage electron))
(def shell (.-shell electron))

; for Renderer process
(def desktopCapturer (.-desktopCapturer electron))
(def ipcRenderer (.-ipcRenderer electron))
(def remote (.-remote electron))
(def screen (.-screen electron)) ; deprecated
(def webFrame (.-webFrame electron))

; Hidden internal modules
(def deprecate (.-deprecate electron))
(def CallbackRegistery (.-CallbackRegistery electron))
