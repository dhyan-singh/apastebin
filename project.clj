(defproject apastebin "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring/ring-core "1.13.0"]
                 [ring/ring-jetty-adapter "1.13.0"]
                 [metosin/reitit "0.7.2"]
                 [ring/ring-devel "1.13.0"]
                 [com.github.seancorfield/next.jdbc "1.3.981"]
                 [com.github.seancorfield/honeysql "2.6.1196"]
                 [org.xerial/sqlite-jdbc "3.43.0.0"]] 
  :main ^:skip-aot apastebin.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}}
  :ring {:handler apastebin.core/app}
  :plugins [[lein-ring "0.12.6"]])
