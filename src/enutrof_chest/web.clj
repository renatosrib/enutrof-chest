(ns enutrof-chest.web
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]
            [enutrof-chest.handler :refer :all]))


(defroutes my-routes
           (POST "/calcula-valor-futuro" [] calcula-valor-futuro)
           (GET "/conversao-rendimento/:juros-anuais/mensal" [] converte-rendimento-anual-mensal)
           (GET "/request-info" [] request-info)
           (GET "/:op1/:op/:op2/calculadora-basica" [] calculadora-basica)
           (not-found "<h1>A página não existe</h1>"))

(def app (-> my-routes
             wrap-reload
             ;;request
             wrap-params
             (wrap-json-body {:keywords? true :bigdecimals? true})
             ;;response
             wrap-json-response))
