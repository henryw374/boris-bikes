(defproject leyton-boris-bikes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [integrant "0.4.0"]
                 [yada "1.2.11"]
                 [org.clojure/tools.logging "0.4.0"]
                 [ch.qos.logback/logback-classic "1.2.3" :exclusions [org.slf4j/slf4j-api]]
                 [orchestra "2017.11.12-1"]
                 [clj-http "2.0.0"]]
  :profiles {:uberjar {:main leyton-boris-bikes.system
                       :uberjar-name "bikes-standalone.jar"}
             :dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]]}})
