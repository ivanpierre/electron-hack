(ns hack.core
  (:require [cljs.nodejs :as js]
            [hack.electron.electron :as e]))

(defn -main []
  "Main event loop of app"
  ; manage errors
  (.. e/crash-reporter
    (start (clj->js {:companyName "Your Company Name"
                     :submitURL   "http://example.com/"})))

  ; error listener
  (.. js/process
    (on "error"
       (fn [err] (.log js/console err))))

  ; window all closed listener
  (.. e/app
    (on "window-all-closed"
       (fn []
         (if (not= e/platform "darwin")
             (.quit e/app)))))

  ; app ready
  (.. e/app
    (on "ready"
      (fn []
        (reset! e/*win* (e/BrowserWindow. #js {:width 800 :height 600})))

      ;; Load main HTML file
      (.. @e/*win*
        (loadURL (e/file-url "../../../index.html")))

      ;; manage MainWindows close
      (.. @e/*win*
        (on "closed"
          (fn [] (reset! e/*win* nil)))))))

; enable printf redirect to the console
(js/enable-util-print!)

; "Linux" or "Darwin" or "Windows_NT"
(.. js/console
  (log (str "Start descjop application on " (.type e/Os) ".")))

; set main client function on main loop
(set! *main-cli-fn* -main)
