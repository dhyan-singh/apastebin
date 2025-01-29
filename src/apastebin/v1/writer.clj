(ns apastebin.v1.writer
  (:require [ring.util.response :as response]
            [apastebin.utils.generate-url :refer [generate-url]]
            [apastebin.utils.db :as db]))

(defn get-client-ip [req]
  (if-let [ips (get-in req [:headers "x-forwarded-for"])]
    (-> ips (clojure.string/split #",") first)
    (:remote-addr req)))

(defn wrt [r]
  (let [ip (get-client-ip r)
        url (generate-url ip)
        content (slurp (:body r))
        _ (try (db/insert url content)
                   (catch Exception e
                     (println (.getMessage e))))
        simple-keys (fn [m] (update-keys 
                                       m
                                       (comp keyword name)))
        resp (simple-keys (db/get-entry url))]
    (println (db/get-entry url))
    (response/content-type (response/created url resp) "application/json")))


