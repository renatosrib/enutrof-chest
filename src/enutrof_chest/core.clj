(ns enutrof-chest.core
  (:require [ring.adapter.jetty :as jetty]
            [ring.middleware.reload :refer [wrap-reload]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :refer [not-found]])

  (:gen-class))

(def operandos {"+" + "-" - "*" * "/" /})

(defn calculadora-basica
  "Resolve calculos básicos"
  [requisiçao]
  (let [op1 (Integer.(get-in requisiçao [:route-params :op1]))
        op2 (Integer. (get-in requisiçao [:route-params :op2]))
        op (get-in requisiçao [:route-params :op])
        f (get operandos op)]
    {:status 200
     :body (f op1 op2)
     :headers {}}))


(defn welcome
  "Handler criado temporáriamente para teste"
  [req]
  (println req)
  (str (:server-name req) ":" (:server-port req)))

(defn request-info
  "Retorna informações sobre a requisição"
  [request] {:status 200
             :body (pr-str request)
             :headers {}})

(defn bem-vindo
  "Exibe mensagem de bem vindo com o nome do usuário, informado via url"
  [request]
  {:status 200
   :body (let [nome (get-in request [:route-params :nome])]
           (str "Seja bem vindo Sr." nome))

   :headers {}})

(defroutes my-routes
           (GET "/" [] welcome)
           (GET "/request-info" [] request-info)
           (GET "/:nome/bem-vindo" [] bem-vindo)
           (GET "/:op1/:op/:op2/calculadora-basica" [] calculadora-basica)
           (not-found"<h1>A página não existe</h1>"))


;(defn -main [port-number]
;  (jetty/run-jetty (my-routes) {:port (Integer. port-number)}))

(defn dev-main [port-number]
  (jetty/run-jetty (wrap-reload #'my-routes) {:port (Integer. port-number)}))
