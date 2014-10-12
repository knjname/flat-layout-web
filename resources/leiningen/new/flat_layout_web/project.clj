(defproject {{name}} "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [lib-noir "0.9.1"]
                 [noir-exception "0.2.2"]
                 [com.taoensso/timbre "3.3.1"]
                 [environ "1.0.0"]
                 [ring-server "0.3.1"]
                 [prone "0.6.0"]
                 ]
  :repl-options {:init-ns {{name}}}
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler {{name}}/app
         :init    {{name}}/init
         :destroy {{name}}/destroy}
  :profiles
  {:uberjar {:aot :all}
   :production {:ring {:open-browser? false
                       :stacktraces?  false
                       :auto-reload?  false}}}
  :source-paths ["."])
