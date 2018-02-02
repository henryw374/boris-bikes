(ns leyton-boris-bikes.routes
  (:require [yada.yada :refer [handler resource] :as yada]
            [clojure.tools.logging :as log]
            [leyton-boris-bikes.bikes :as bikes]))


(defn routes []
  ["/"
   [
    ["bikes" (resource
               {:description "get bike data, not requiring authentication"
                :methods {:get {:response (fn []
                                            (log/info "user making request")
                                            (bikes/bike-points-with-availability))
                                :produces "application/json"}}})]
    ["bikes-with-auth" (resource
                         {:description "get bike data, requiring authentication"
                          :responses {403 {:response "You are not allowed to make this request"}}
                          :methods {:get {:response (fn []
                                                      (log/info "user making request")
                                                      (bikes/bike-points-with-availability))
                                          :produces "application/json"}}})]
    [true (yada/as-resource nil)]]])