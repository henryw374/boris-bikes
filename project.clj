(defproject leyton-boris-bikes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [integrant "0.4.0"]
                 [yada "1.2.6"]
                 [org.clojure/tools.logging "0.4.0"]
                 [orchestra "2017.11.12-1"]
                 [clj-http "2.0.0"]]
  :profiles {:dev {:source-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]]}})
