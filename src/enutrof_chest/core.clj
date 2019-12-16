(ns enutrof-chest.core
  (:require [ring.adapter.jetty :as jetty]

            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes GET POST]]
            [compojure.route :refer [not-found]]))

(defn calcular-valor-futuro [valor-aplicado taxa-juros periodo]
  (println "Juros" taxa-juros "Valor aplicado" valor-aplicado "Período" periodo)
  (* valor-aplicado (Math/pow (+ 1 taxa-juros) periodo)))

(defn
  converter-rendimento-anual-mensal
  "Converte juros anuais para mensais usando a seguinte fórmula: juros-mensais = ((1 + juros-anuais) ^ (1/12)) -1"
  [ juros-anuais]
  (- (Math/pow (+ 1 juros-anuais) (/ 1 12)) 1))


