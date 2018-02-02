(ns leyton-boris-bikes.system
  (:require [leyton-boris-bikes.routes :as routes]
            [integrant.core :as integrant]
            [yada.yada :as yada])
  (:gen-class))

(defn config [{:keys [port]}]
  {:sys/server {:port (Integer/parseInt (or port "3000"))}})

(defmethod integrant/init-key :sys/server [_ opts]
  (yada/listener (routes/routes) opts))

(defmethod integrant/halt-key! :sys/server [_ server]
  ((:close server)))

(defn- wait-for-exit! []
  (.join (Thread/currentThread)))

(defn start [port]
  (integrant/init (config {:port port})))

(defn -main [& [port]]
  (start port)
  (wait-for-exit!))
