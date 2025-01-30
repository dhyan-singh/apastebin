(ns apastebin.v1.reader
  (:require [ring.util.response :as response]
            [apastebin.utils.db :as db]))

(defn get-paste [r]
  (let [q (get-in r [:path-params :url])
        v (db/content q)]
    (response/content-type (response/response v) "text/plain")))