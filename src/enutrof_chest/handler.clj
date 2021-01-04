(ns enutrof-chest.handler
  (:require [enutrof-chest.core :refer :all]))

(def operandos {"+" + "-" - "*" * "/" /})

(defn calculadora-basica
  "Resolve calculos básicos"
  [requisicao]
  (println requisicao)
  (let [op1 (Integer. (get-in requisicao [:route-params :op1]))
        op2 (Integer. (get-in requisicao [:route-params :op2]))
        op (get-in requisicao [:route-params :op])
        f (get operandos op)]
    {:status  200
     :body    (str (f op1 op2))
     :headers {}}))

(defn request-info
  "Retorna informações sobre a requisição"
  [request] {:status  200
             :body    (pr-str request)
             :headers {}})

(defn conversao-juros
  "Faz conversão de juros anual para mensal"
  [request]
  (println "**********REQUEST-HANDLER" request)
  {:status  200
   :body    (let [taxa-juros (BigDecimal. (get-in request [:body :taxa-juros]))
                  tipo-taxa (get-in request [:body :tipo-taxa])]
              (println (get-in request [:body]))
              (if (= tipo-taxa "MENSAL")
                (str (juro-mensal->juro-anual taxa-juros))
                (str (juro-anual->juro-mensal taxa-juros))))
   :headers {}})

(defn calcula-valor-futuro [req]
  (println req)
  (let [valor-aplicado (get-in req [:body :valor_presente])
        taxa-juros (/ (get-in req [:body :taxa_juros]) 100)
        periodo (get-in req [:body :periodo_aplicacao])]
    {
     :status 200
     :body {:valor_futuro (calcular-valor-futuro valor-aplicado taxa-juros periodo)}}))
