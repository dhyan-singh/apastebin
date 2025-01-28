(ns apastebin.core
  (:use ring.adapter.jetty
        apastebin.home)
  (:require [ring.util.response :as response]
            [reitit.ring :as ring]
            [apastebin.v1.reader :as rdr]
            [apastebin.v1.writer :as wrt])
  (:gen-class))

(def app
  (ring/ring-handler
   (ring/router
    [["/api" ::api
      ["/v1" ::v1
       ["/paste" {:get rdr/rdr
                   :post wrt/wrt
                   :name ::paste}]]]])))

(comment
  (app {:request-method :get :uri "/api/v1/paste"})
  )

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (run-jetty app {:port 3000
                  :join? false}))
