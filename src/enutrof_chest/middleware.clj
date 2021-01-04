(ns enutrof-chest.middleware
  (:require [camel-snake-kebab.core :as csk]
            [camel-snake-kebab.extras :as cske]
            [clojure.data.json :as json]
            [clojure.stacktrace :as st]
            [environ.core :refer [env]]
            [ring.util.request :refer [body-string]]))

(defn- get-allow-origin
  "Verifica se origem está na lista de domínios permitidos na configuração da aplicação."
  [request allowed-origins]
  (let [origin (get-in request [:headers "origin"])
        content (->> allowed-origins
                     (filter (fn [allowed-origin]
                               (= allowed-origin origin)))
                     (first))]
    (when (empty? content) ;;Seria má prática bloquear a requisição antes do processamento?
      (println "Requisição da origem: " origin " não está listada entre os domínios permitidos!"))
    content))


(defn wrap-cors
  "Wrap the server response in a Control-Allow-Origin Header to
  allow connections from the web app."
  [handler]
  (println "Domains allowed: " (env :allowed-origins))
  (fn [request]
    (let [response (handler request)]
      (-> response
          (assoc-in [:headers "Access-Control-Allow-Origin"] (get-allow-origin request (clojure.edn/read-string (env :allowed-origins))))
          (assoc-in [:headers "Access-Control-Allow-Headers"] "*")
          (assoc-in [:headers "Access-Control-Allow-Methods"] "POST, GET, OPTIONS")))))



(defn wrap-exceptions
  "Avoid unhandled exceptions on API"
  [handler]
  (fn [request]
    (try
      (let [response (handler request)]
        response)
      (catch Exception
             e
             (st/print-stack-trace e)
             {:headers {}
              :status  400
              :body    {:status 400
                        :data   (.getMessage e)}}))))


(defn wrap-keys-conversion [handler]
  (fn [request]
    (let [response (handler (-> request
                                (update :body #(cske/transform-keys csk/->kebab-case  %))))]
      (update response :body #(cske/transform-keys csk/->snake_case  %)))))

(defn wrap-response [handler]
  (fn [request]
    (let [response (handler request)]
      (if (map? (:body response))
        (update response :body json/write-json)
        response))))