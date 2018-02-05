 (ns user
   (:require [leyton-boris-bikes.system :as sys]
             [clojure.tools.namespace.repl :as tools.repl]
             [integrant.core :as integrant]))
 
(defonce system nil)

(defn start []
  (alter-var-root #'system
    (constantly (sys/start nil))))

(defn stop []
  (integrant/halt! system)
  (alter-var-root #'system
    (constantly nil)))

(defn reset []
  (when system (stop))
  (tools.repl/refresh :after 'user/start))