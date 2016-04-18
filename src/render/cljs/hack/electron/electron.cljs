(ns hack.electron.electron
  (:require [cljs.nodejs :as js]))

(def Electron (js/require "electron"))
(def clipboard (.-clipboard Electron))
(def remote (.-remote Electron))
(def screen (.-screen Electron))
(def nativeImage (.-nativeImage Electron))
(def webFrame (.-webFrame Electron))
(def ipcRenderer (.-ipcRenderer Electron))
(def shell (.-shell Electron))
(def crashReporter (.-crashReporter Electron))
(def desktopCapturer (.-desktopCapturer Electron))
(def deprecations (.-deprecations Electron))
