(ns leyton-boris-bikes.bikes
  (:require [clj-http.client :as client]
            [cheshire.core :as cheshire]
            [clojure.spec.alpha :as s]
            [clojure.tools.logging :as log]))

(s/def ::lat double?)
(s/def ::lon double?)
(s/def ::commonName string?)
(s/def ::availableBikes integer?)
(s/def ::url string?)
(s/def ::distance double?)

(s/def ::bike-point
  (s/keys
    :req-un [::lat ::lon ::commonName ::availableBikes ::url ::distance]))

(defn distance [a b]
  (Math/sqrt
    (+
      (Math/pow (- (:lat a) (:lat b)) 2)
      (Math/pow (- (:lon a) (:lon b)) 2))))

(def centre-location {:lat 51.561948 :lon -0.013139})
(def max-bike-points 5)
(def tfl-host "https://api.tfl.gov.uk")
(def tfl-bike-resource (str tfl-host "/bikepoint"))

(defn bike-data []
  (->
    (try
      (let [{:keys [status body] :as response} (client/get tfl-bike-resource)]
        (if (= 200 status)
          {:data body
           :status :fresh}
          (throw (ex-info "failed to get bike data" response))))
      (catch Exception e
        (log/info e)
        {:data (slurp "resources/sample-bike-data.json")
         :status :stale}))
    (update :data cheshire/parse-string true)))

(defn number-of-available-bikes [bike-point]
  (->> bike-point
       :additionalProperties
       (filter #(= "NbBikes" (:key %)))
       first
       :value
       (Integer/parseInt)))

(defn shape-for-client [bike-point]
  (-> bike-point
      (select-keys [:distance
                    :lon 
                    :url 
                    :lat 
                    :commonName ])
      (update :url #(str tfl-host %))
      (assoc :availableBikes (number-of-available-bikes bike-point))))

(defn closest-bike-points [{:keys [data]}]
  (->>
    data
    (mapv #(assoc % :distance (distance centre-location %)))
    (sort-by :distance)
    (take max-bike-points)
    (mapv shape-for-client)))


(defn bike-points-with-availability []
  (-> (bike-data)
      (closest-bike-points)))
