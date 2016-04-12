(defproject gobang "0.1.0-alpha1"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :source-paths ["src/cljs"]

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.8.40" :exclusions [org.apache.ant/ant]]
                 [cljsjs/react "15.0.0-0"]
                 [cljsjs/react-dom "15.0.0-0"]
                 [cljsjs/react-dom-server "15.0.0-0"]
                 [cljsjs/nodejs-externs "1.0.4-1"]
                 [reagent "0.6.0-alpha"]]

  :plugins [[lein-cljsbuild "1.1.3"]]

  :min-lein-version "2.5.3"

  :cljsbuild {:builds {:app {:source-paths ["src/main/cljs" "src/cljs"]
                             :compiler {:output-to     "app/js/p/app.js"
                                        :output-dir    "app/js/p/out"
                                        :asset-path    "js/p/out"
                                        :optimizations :none
                                        :pretty-print  true
                                        :cache-analysis true}}}}

  :clean-targets ^{:protect false} [:target-path "out" "app/js/p"]

  :figwheel {:css-dirs ["app/css"]}

  :profiles {:dev {:cljsbuild {:builds {:app {:source-paths ["src/render/cljs" "src/cljs"]
                                              :compiler {:source-map true
                                                         :main "gobang.render"
                                                         :verbose true}
                                              :figwheel {:on-jsload "gobang.render/mount-root"}}}}
                   :source-paths ["env/dev/cljs"]

                   :dependencies [[figwheel-sidecar "0.5.2"]]

                   :plugins [[lein-ancient "0.6.8"]
                             [lein-kibit "0.1.2"]
                             [lein-cljfmt "0.4.1"]
                             [lein-shell "0.4.1"]
                             [lein-figwheel "0.5.2"]]}

             :production {:cljsbuild {:builds {:app {:source-paths ["src/render/cljs" "src/cljs"]
                                                     :compiler {:optimizations :advanced
                                                                :main          "gobang.render"
                                                                :parallel-build true
                                                                :cache-analysis false
                                                                :closure-defines {"goog.DEBUG" false}
                                                                :externs ["externs/misc.js"]
                                                                :pretty-print false}}}}}})
