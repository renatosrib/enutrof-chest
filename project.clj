(defproject enutrof-chest "0.1.0-SNAPSHOT"
  :description "Projeto criado com o intuito de melhorar conhecimentos em clojure e cálculos usados para investimentos"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.9.0"]
                 [compojure "1.6.1"]
                 [http-kit "2.1.16"]
                 [org.clojure/clojure "1.8.0"]
                 [ring "1.7.1"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]]
  :main enutrof-chest.core
  :profiles {
             :dev {:main enutrof-chest.core/dev-main}}
  :ring  {:handler enutrof-chest.web/app
          :port 5000})



