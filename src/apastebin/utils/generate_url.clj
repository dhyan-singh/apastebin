(ns apastebin.utils.generate-url
  (:require [clj-base62.core :as base62]))

(defn generate-url [ip]
  ; ip+timestamp to md5hash to base62 hash
  (let [md5 (java.security.MessageDigest/getInstance "MD5")
        h (->> (str ip (System/currentTimeMillis))
               .getBytes
               (.digest md5)
               base62/encode)]
    h))
