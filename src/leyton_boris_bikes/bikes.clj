(ns leyton-boris-bikes.bikes
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire]
            [clojure.tools.logging :as log]))

(defn distance [a b]
  (Math/sqrt
    (+
      (Math/pow (- (:lat a) (:lat b)) 2)
      (Math/pow (- (:long a) (:long b)) 2))))

(def centre-location {:lat 51.561948 :long -0.013139})
(def max-bike-points 5)
(def tfl-bike-resource "https://api.tfl.gov.uk/bikepoint")

(defn bike-data []
  (try
    (let [{:keys [status body] :as response} (client/get tfl-bike-resource {:timeout 2})]
      (if (= 200 status)
        {:data body}
        (throw (ex-info "failed to get bike data" response))))
    (catch Exception e
      (log/info e)
      {:data "resources/sample-bike-data.json"})))


(defn bike-points-with-availability []
  )
