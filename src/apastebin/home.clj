(ns apastebin.home
  (:require [ring.util.response :refer [response]]))


(defn home [r]
  (response "<h2>Hello</h2>"))