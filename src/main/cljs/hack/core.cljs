(ns hack.core
  (:require [cljs.nodejs :as js]
            [hack.electron.electron :as e]))

(def browserWindowOptions
  #js {:height  850
       :title   "hack"
       :width   1400
       :icon    (e/file-url "img/logo_96x96.png")})


; enable printf redirect to the console
(enable-console-print!)

; "Linux" or "Darwin" or "Windows_NT"
(println (str "Init application on " (.type e/Os) "."))

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
        (let [win (e/Electron.BrowserWindow. browserWindowOptions)]
          (reset! e/*win* win))

        ;; Load main HTML file
        (.. @e/*win*
          (loadURL (e/file-url "index.html")))

        ;; manage MainWindows close
        (.. @e/*win*
          (on "closed"
            (fn [] (reset! e/*win* nil))))

        ; "Linux" or "Darwin" or "Windows_NT"
        (println (str "Start application on " (.type e/Os) "."))))))

; set main client function on -main
(set! *main-cli-fn* -main)
