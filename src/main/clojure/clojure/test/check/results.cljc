;   Copyright (c) Rich Hickey, Reid Draper, and contributors.
;   All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns ^{:author "Gary Fredericks"
      :doc "A protocol and helper functions for trial results."}
    clojure.test.check.results)

(defprotocol Result
  (passing? [result])
  (result-data [result] "A map of data about the trial."))

(extend-protocol Result
  #?(:clj Object :cljs object)
  (passing? [this] (boolean this))
  (result-data [this] {})

  #?@(:cljs
      (string
       (passing? [this] true)
       (result-data [this] {})

       function
       (passing? [this] true)
       (result-data [this] {})

       boolean
       (passing? [this] this)
       (result-data [this] {})

       number
       (passing? [this] true)
       (result-data [this] {})

       array
       (passing? [this] true)
       (result-data [this] {})))

  nil
  (passing? [this] false)
  (result-data [this] {}))
