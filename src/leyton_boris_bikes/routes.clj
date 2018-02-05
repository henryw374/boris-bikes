(ns leyton-boris-bikes.routes
  (:require [yada.yada :refer [handler resource] :as yada]
            [clojure.tools.logging :as log]
            [leyton-boris-bikes.bikes :as bikes]))


(defn routes []
  ["/"
   [
    ["bikes" (resource
               {:description "get bike data, not requiring authentication"
                :methods {:get {:response (fn [ctx]
                                            (log/info "user making no-auth request")
                                            (bikes/bike-points-with-availability ctx))
                                :produces "application/json"}}})]
    ["bikes-with-auth" (resource
                         {:description "get bike data, requiring authentication"
                          :methods {:get {:response (fn [ctx]
                                                      (log/info "user making auth request")
                                                      (bikes/bike-points-with-availability ctx))
                                          :produces "application/json"}}
                          :access-control
                          {:scheme "Basic"
                           :verify (fn [[user password]]
                                     (log/infof "got creds :user %s" user )
                                     (if (and
                                           (= "user1" user)
                                           (= "password" password))
                                       {:username user
                                        :roles #{:bike/user}}
                                       {}))
                           :authorization {:methods
                                           {:get :bike/user}}}})]
    [true (yada/as-resource nil)]]])