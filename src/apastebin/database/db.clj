(ns apastebin.database.db
  (:require [honey.sql :as sql]
            [honey.sql.helpers :as h]
            [next.jdbc :as jdbc]))

(def dbname "pastebin.db")

(def db {:dbtype "sqlite" :dbname dbname})
(def ds (jdbc/get-datasource db))

(defn create-db! []
  (when-not (.exists (java.io.File. dbname))
    (jdbc/execute! ds
                   (-> (h/create-table :bin)
                       (h/with-columns [[:url :text]
                                        [:content :text]
                                        [:created-at :datetime [:default :CURRENT_TIMESTAMP]]
                                        [[:primary-key :url]]])
                       (sql/format)))))

(defn insert [url content]
  (create-db!)
  (jdbc/execute! ds
                 (-> (h/insert-into :bin)
                     (h/columns :url :content)
                     (h/values [[url content]])
                     (sql/format))))

(defn get-url [url]
  (create-db!)
  (first (jdbc/execute! ds
                        (-> (h/select [:*])
                            (h/from :bin)
                            (h/where [:= :url url])
                            (sql/format)))))

(defn content [url]
  (:bin/content (get-url url)))

(comment
  (insert "alpha" "hi alpha"))