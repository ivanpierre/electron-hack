;;; hack.electron.electron-front
;;;
;;; electron library interface for front-end process
;;;
;;; @author Ivan Pierre<ivan@kilroysoft.ch>
;;;

(ns hack.electron.electron-front
  (:require [cljs.nodejs :as js]))

(def electron (js/require "electron"))
(def clipboard (.-clipboard electron))
(def remote (.-remote electron))
(def screen (.-screen electron))
(def nativeImage (.-nativeImage electron))
(def webFrame (.-webFrame electron))
(def ipcRenderer (.-ipcRenderer electron))
(def shell (.-shell electron))
(def crashReporter (.-crashReporter electron))
(def desktopCapturer (.-desktopCapturer electron))
(def deprecations (.-deprecations electron))
