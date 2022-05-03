(ns br.com.souenzzo.pathom-23-compat.pathom3
  (:require [com.wsscode.pathom3.connect.operation :as pco]))


;; TODO:
#_[{:com.wsscode.pathom.connect/indexes [:com.wsscode.pathom.connect/idents
                                         :com.wsscode.pathom.connect/index-io
                                         :com.wsscode.pathom.connect/autocomplete-ignore]}]

(pco/defresolver index-explorer-index [{:com.wsscode.pathom3.connect.indexes/keys [index-resolvers index-mutations index-attributes]} {}]
  {::pco/input  [:com.wsscode.pathom.viz.index-explorer/id]
   ::pco/output [:com.wsscode.pathom.viz.index-explorer/index]}
  {:com.wsscode.pathom.viz.index-explorer/index
   {:com.wsscode.pathom.connect/index-attributes (into {}
                                                   (map (fn [[attr-id {:com.wsscode.pathom3.connect.indexes/keys [attr-provides attr-input-in attr-reach-via attr-output-in]}]]
                                                          [attr-id {:com.wsscode.pathom.connect/attribute-id   attr-id
                                                                    :com.wsscode.pathom.connect/attr-provides  attr-provides
                                                                    :com.wsscode.pathom.connect/attr-input-in  attr-input-in
                                                                    :com.wsscode.pathom.connect/attr-reach-via attr-reach-via
                                                                    :com.wsscode.pathom.connect/attr-output-in attr-output-in}]))
                                                   index-attributes)
    :com.wsscode.pathom.connect/index-mutations  (into {}
                                                   (map (fn [[sym {::pco/keys [input output provides params]}]]
                                                          [sym {:com.wsscode.pathom.connect/sym      sym
                                                                :com.wsscode.pathom.connect/input    (set (map (fn [v]
                                                                                                                 (if (map? v)
                                                                                                                   (ffirst v)
                                                                                                                   v))
                                                                                                            input))
                                                                :com.wsscode.pathom.connect/provides provides
                                                                :com.wsscode.pathom.connect/params   params
                                                                :com.wsscode.pathom.connect/output   output}]))
                                                   index-mutations)
    :com.wsscode.pathom.connect/index-resolvers  (into {}
                                                   (map (fn [[sym {::pco/keys [input output provides params]}]]
                                                          [sym {:com.wsscode.pathom.connect/sym      sym
                                                                :com.wsscode.pathom.connect/input    (set (map (fn [v]
                                                                                                                 (if (map? v)
                                                                                                                   (ffirst v)
                                                                                                                   v))
                                                                                                            input))
                                                                :com.wsscode.pathom.connect/provides provides
                                                                :com.wsscode.pathom.connect/params   params
                                                                :com.wsscode.pathom.connect/output   output}]))
                                                   index-resolvers)}

   #_(pc/register {} pc/index-explorer-resolver)
   #_(select-keys env
       [:com.wsscode.pathom3.connect.indexes/index-resolvers
        :com.wsscode.pathom3.connect.indexes/index-attributes
        :com.wsscode.pathom3.connect.indexes/index-io
        :com.wsscode.pathom3.connect.indexes/index-oir
        :com.wsscode.pathom3.connect.indexes/index-mutations])})
