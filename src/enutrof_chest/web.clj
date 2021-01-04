(ns enutrof-chest.web
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [enutrof-chest.middleware :refer [wrap-cors
                                              wrap-keys-conversion
                                              wrap-exceptions
                                              wrap-response]]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]
            [compojure.core :refer [defroutes GET POST OPTIONS]]
            [compojure.route :refer [not-found]]
            [enutrof-chest.handler :refer :all]))


(defroutes my-routes
           (GET "/" []  (fn [req] (str req)))
           (POST "/calcula-valor-futuro" [] calcula-valor-futuro)
           (POST "/api/conversao-juros/" [] conversao-juros)
           (GET "/request-info" [] request-info)
           (GET "/:op1/:op/:op2/calculadora-basica" [] calculadora-basica)
           (OPTIONS "/*" [] (fn [request]
                              {:headers {}
                               :body    nil
                               :status  200}))

           (not-found "<h1>A página não existe</h1>"))

(def app (-> my-routes
             ;;request
             ;;(wrap-response)
             wrap-keys-conversion
             (wrap-json-body {:keywords? true :bigdecimals? true})
             ;;response
             wrap-cors
             wrap-exceptions
             ;;(wrap-defaults api-defaults)
             wrap-params
             wrap-json-response))



