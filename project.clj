(defproject hack "0.1.0-alpha"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license
    {:name "Eclipse Public License"
     :url "http://www.eclipse.org/legal/epl-v10.html"}

  :min-lein-version "2.5.3"

  :dependencies
    [[org.clojure/clojure "1.8.0"]
     [org.clojure/clojurescript "1.8.40" :exclusions [org.apache.ant/ant]]
     [cljsjs/react "15.0.1-0"]
     [cljsjs/react-dom "15.0.1-0"]
     [cljsjs/react-dom-server "15.0.1-0"]
     [cljsjs/nodejs-externs "1.0.4-1"]
     [reagent "0.6.0-alpha"]
     [figwheel-sidecar "0.5.2"]]

  :plugins
    [[lein-cljsbuild "1.1.3"]
     [lein-ancient "0.6.8"] ; to ensure packages are up-to-date
     [lein-externs "0.1.6"] ; to generate extern files
     [lein-kibit "0.1.2"]
     [lein-cljfmt "0.4.1"]  ; to ensure formatting matches the syntax
     [lein-shell "0.4.1"]
     [lein-figwheel "0.5.2"]]

  :figwheel
    {:css-dirs          ["resources/public/css"]
     :server-logfile    "figwheel_server.log"
     :reload-clj-files  {:clj true
                         :cljc true}}

  ; Generated file for lein clean
  :clean-targets ^{:protect false} [:target-path  "app/js"]

  ; Compile the whole
  :cljsbuild
    {:builds
      [{:id main
        :source-paths ["src/main/cljs"]
        :incremental true
        :jar true
        :assert true
        :compiler
          {:output-to "app/js/main/hackmain.js"
           :asset-path "js/main"
           :externs ["externs/misc.js"
                     "node_modules/closurecompiler-externs/path.js"
                     "node_modules/closurecompiler-externs/process.js"]
           :main "hack.core"
           :warnings true
           :elide-asserts true
           :target :nodejs
           :optimizations :none
           :output-dir "app/js/main"
           ;;:source-map "app/js/test.js.map"
           :pretty-print true
           :output-wrapper true}}
       {:id front
         :source-paths ["src/render/cljs"]
         :incremental true
         :jar true
         :assert true
         :compiler
           {:output-to "app/js/front/frontmain.js"
            :asset-path "js/front"
            :main "hack.render"
            :externs ["externs/misc.js"]
            :warnings true
            :elide-asserts true
            :optimizations :none
            :output-dir "app/js/front"
            ;;:source-map "app/js/test.js.map"
            :pretty-print true
            :output-wrapper true}}]})
