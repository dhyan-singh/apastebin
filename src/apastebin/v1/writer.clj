(ns apastebin.v1.writer
  (:require [ring.util.response :as response]
            [apastebin.utils.generate-url :refer [generate-url]]
            [apastebin.utils.db :as db]
            [clojure.string :as str]
            [apastebin.utils.general :refer [nskw->kw]]))

(defn get-client-ip [req]
  (if-let [ips (get-in req [:headers "x-forwarded-for"])]
    (-> ips (str/split #",") first)
    (:remote-addr req)))

(defn create-paste [r]
  (let [ip (get-client-ip r)
        url (generate-url ip)
        content (slurp (:body r))
        _ (try (db/insert url content)
                   (catch Exception e
                     (println (.getMessage e))))
        m (nskw->kw (db/get-entry url))]
    (response/content-type (response/created url m) "application/json")))