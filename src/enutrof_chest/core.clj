(ns enutrof-chest.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroute GET]])

  (:gen-class))

(defn welcome [request]
  (if (= "/" (:uri request))
    {
     :status 200
     :body "<h1>Hello, Clojure World</h1>  <p>Welcome to your first Clojure app.
                                          That message is returned regardless of the request, sorry</p>"
     :headers {}}

    {:status 404
     :body "<h1>This is not the page you are lookings for</h1>
            <p>Sorry, the page you requested was not found!</p>"
     :headers {}}))



(defn dev-main [port-number]
  (jetty/run-jetty (wrap-reload #'welcome) {:port (Integer. port-number)}))
