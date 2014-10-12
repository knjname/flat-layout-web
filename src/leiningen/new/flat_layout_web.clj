(ns leiningen.new.flat-layout-web
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "flat-layout-web"))

(defn flat-layout-web
  "Generates flat-layout-web leiningen project."
  [name]
  (let [data {:name name
              :sanitized (name-to-path name)}]
    (main/info "Generating fresh 'lein new' flat-layout-web project.")
    (->files data
             ["{{sanitized}}.clj" (render "foo.clj" data)]
             ["project.clj" (render "project.clj" data)]
             [".gitignore" (render "gitignore" data)]
             ["Procfile" (render "Procfile" data)]
             ["system.properties" (render "system.properties" data)])))
