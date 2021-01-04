(defproject enutrof-chest "0.1.0-SNAPSHOT"
  :description "Projeto criado com o intuito de melhorar conhecimentos em clojure e cálculos usados para investimentos"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[cheshire "5.9.0"]
                 [compojure "1.6.1"]
                 [environ "1.2.0"]
                 [org.clojure/data.json "1.0.0"]
                 [org.clojure/clojure "1.8.0"]
                 [ring "1.8.1"]
                 [camel-snake-kebab "0.4.2"]
                 [ring/ring-json "0.5.0"]
                 ;[ring-cors "0.1.13"]
                 [ring/ring-defaults "0.3.2"]]
  :plugins [[lein-ring "0.12.5"]
            [lein-environ "1.2.0"]]
  :main enutrof-chest.core
  :profiles {:dev [:project/dev :profiles/dev]
             :profiles/dev  {}}
  :ring  {:handler enutrof-chest.web/app
          :port 5000})



