(ns apastebin.v1.reader
  (:require [ring.util.response :as response]
            [apastebin.database.db :as db]))


(defn rdr [r]
  (let [q (get-in r [:path-params :url])
        v (db/content q)]
    (response/content-type (response/response v) "text/plain")))