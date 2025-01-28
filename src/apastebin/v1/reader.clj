(ns apastebin.v1.reader
  (:require [ring.util.response :as response]))


(defn rdr [r]
  (response/response "I'm speaking from reader...."))