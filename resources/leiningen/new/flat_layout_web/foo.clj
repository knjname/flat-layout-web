(ns {{name}}
  (:require [compojure.core :refer :all]
            [ring.middleware.defaults :refer [site-defaults]]
            [noir.util.middleware :refer [app-handler]]
            [compojure.route :as route]
            [taoensso.timbre :as timbre]
            [environ.core :refer [env]]
            [prone.middleware :refer [wrap-exceptions]]
            [noir-exception.core :refer [wrap-internal-error]]
            )
  (:use [ring.server.standalone]))

;;; Routes

(defroutes my-routes
  (GET "/" [] "Hello!"))


;;; Handlers

(defn init [] nil)
(defn destroy [] nil)

(def development-middleware
  [wrap-exceptions])

(def production-middleware
  [#(wrap-internal-error % :log (fn [e] (timbre/error e)))])

(defn load-middleware []
  (concat (when (env :dev) development-middleware)
          production-middleware))

(def app (app-handler
          [my-routes]
          :middleware (load-middleware)
          :access-rules []
          :formats []))

(defn get-handler []
  (-> #'app))


;;; Server invoked from a REPL

(defonce server (atom nil))

(defn start-server [& [port]]
  (let [port (if port (Integer/parseInt port) 3000)]
    (reset! server
            (serve (get-handler)
                   {:port port
                    :init init
                    :auto-reload true
                    :destroy destroy
                    :join? false}))
    (println (str "You can view the site at http://localhost:" port))))

(defn stop-server []
  (.stop @server)
  (reset! server nil))

