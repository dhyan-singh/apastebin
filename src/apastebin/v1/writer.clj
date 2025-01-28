(ns apastebin.v1.writer
  (:require [ring.util.response :as response]))


(defn wrt [r]
  (response/response "I'm speaking from writer!!!!"))