(ns apastebin.core
  (:use ring.adapter.jetty)
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [reitit.ring :as ring]
            [apastebin.v1.reader :refer [get-paste]]
            [apastebin.v1.writer :refer [create-paste]]
            [ring.middleware.params :as params]
            [ring.middleware.json :refer [wrap-json-response]]
            [apastebin.pages :as pages])
  (:gen-class))

(def app
  (ring/ring-handler
   (ring/router
    [["/" {:get pages/home
           :name ::home-page}]
     ["/api" ::api
      ["/v1" ::v1
       ["/paste" {:post create-paste
                  :name ::paste}]
       ["/:url" {:get get-paste
                 :name ::all}]]]
     ["/:url" {:get get-paste
               :name ::general}]]
    {:conflicts nil})))

(comment
  (app {:request-method :get :uri "/api/v1/paste"}))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty (wrap-json-response (params/wrap-params (wrap-reload #'app))) {:port 3000
                                                                            :join? false}))
