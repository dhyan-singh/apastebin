(ns apastebin.utils.general)

(defn nskw->kw
  "{:bin/content 1} to {:content 1}"
  [m]
  (update-keys
   m
   (comp keyword name)))