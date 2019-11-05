(defproject enutrof-chest "0.1.0-SNAPSHOT"
  :description "Projeto criado com o intuito de melhorar conhecimentos em clojure e cálculos usados para investimentos"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] [ring "1.7.1"] [ring/ring-json "0.5.0"]  [ring/ring-defaults "0.3.2"] [compojure "1.6.1"] [http-kit "2.1.16"]]
  :plugins [[lein-ring "0.12.1"]]
  :main enutrof-chest.core
  :profiles {
             :dev {:main enutrof-chest.core/dev-main}})



