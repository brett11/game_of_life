(defproject game-of-life "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main game-of-life.ui
  :aot [game-of-life.ui]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
