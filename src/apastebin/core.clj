(ns apastebin.core
  (:use ring.adapter.jetty
        apastebin.home)
  (:require [ring.middleware.reload :refer [wrap-reload]]
            [reitit.ring :as ring]
            [apastebin.v1.reader :as rdr]
            [apastebin.v1.writer :as wrt]
            [ring.middleware.params :as params]
            [ring.middleware.json :refer [wrap-json-response]]
            
            [apastebin.home :refer [home]] ; basically testing
            )
  (:gen-class))

(def app
  (ring/ring-handler
   (ring/router
    [["/api" ::api
      ["/v1" ::v1
       ["/paste" {:get rdr/rdr ;;redirect somewhere else
                   :post wrt/wrt
                   :name ::paste}]
       ["/:url" {:get rdr/rdr
                 :name ::all}]]]
     ["/:url" {:get rdr/rdr
               :name ::general}]]
    {:conflicts nil})))

(comment
  (app {:request-method :get :uri "/api/v1/paste"})
  )


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty (wrap-json-response (params/wrap-params (wrap-reload #'app))) {:port 3000
                  :join? false}))
