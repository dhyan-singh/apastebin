(ns apastebin.pages
  (:require [ring.util.response :refer [response content-type]]))

(def home-page "<h1>welcome</h1>")

(defn home [r]
  (-> home-page response (content-type "text/html")))