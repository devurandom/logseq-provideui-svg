(ns logseq-provideui-svg.main)

(let [body (. js/document getElementById "app")
      svg  (. js/document createElementNS "http://www.w3.org/2000/svg" "svg")
      defs (. js/document createElementNS "http://www.w3.org/2000/svg" "defs")]
  (set! (. defs -innerHTML) (str "<g id=\"Tree\"><path d=\"m 28,-2 c 0,20 -12,31 -27,31 -15,0 -27,-11 -27,-31 0,-20 12,-43 27,-43 15,0 27,22 27,43 z\" fill=\"white\"/><path d=\"m 6,-43 c 0,0 0,16 -4,19 -3,4 -4,15 -1,18 2,3 8,8 5,11 -3,3 -6,5 -5,9 0,4 8,6 -4,8 -11,2 -11,2 -11,2 0,0 13,9 26,2 14,-6 16,-29 16,-29 0,0 4,-21 -22,-41 z\" fill=\"#39b54a\"/><path d=\"m 28,-2 c 0,20 -12,31 -27,31 -15,0 -27,-11 -27,-31 0,-20 12,-43 27,-43 15,0 27,22 27,43 z\" stroke=\"black\" fill=\"none\" stroke-width=\"6\"/><rect x=\"-4\" y=\"30\" width=\"11\" height=\"20\" fill=\"#39b54a\" stroke=\"black\" stroke-width=\"6\"/><line x1=\"-4\" y1=\"50\" x2=\"29\" y2=\"50\" fill=\"#39b54a\" stroke=\"black\" stroke-width=\"6\"/></g>\n"
                                 "<g id=\"tree\"><use href=\"#Tree\" transform=\"scale(0.6)\"/></g>\n"))
  (. svg append defs)
  (set! (. body -innerHTML) (. svg -outerHTML)))

(defn main []
  (js/logseq.App.onMacroRendererSlotted
    (fn [arg]
      (let [{:keys [slot]} (js->clj arg :keywordize-keys true)
            svg  (. js/document createElementNS "http://www.w3.org/2000/svg" "svg")
            defs (. js/document createElementNS "http://www.w3.org/2000/svg" "defs")]
        (set! (. defs -innerHTML) (str "<g id=\"Tree\"><path d=\"m 28,-2 c 0,20 -12,31 -27,31 -15,0 -27,-11 -27,-31 0,-20 12,-43 27,-43 15,0 27,22 27,43 z\" fill=\"white\"/><path d=\"m 6,-43 c 0,0 0,16 -4,19 -3,4 -4,15 -1,18 2,3 8,8 5,11 -3,3 -6,5 -5,9 0,4 8,6 -4,8 -11,2 -11,2 -11,2 0,0 13,9 26,2 14,-6 16,-29 16,-29 0,0 4,-21 -22,-41 z\" fill=\"#39b54a\"/><path d=\"m 28,-2 c 0,20 -12,31 -27,31 -15,0 -27,-11 -27,-31 0,-20 12,-43 27,-43 15,0 27,22 27,43 z\" stroke=\"black\" fill=\"none\" stroke-width=\"6\"/><rect x=\"-4\" y=\"30\" width=\"11\" height=\"20\" fill=\"#39b54a\" stroke=\"black\" stroke-width=\"6\"/><line x1=\"-4\" y1=\"50\" x2=\"29\" y2=\"50\" fill=\"#39b54a\" stroke=\"black\" stroke-width=\"6\"/></g>\n"
                                       "<g id=\"tree\"><use href=\"#Tree\" transform=\"scale(0.6)\"/></g>\n"))
        (. svg append defs)
        (js/logseq.provideUI #js{:key      "repro"
                                 :slot     slot
                                 :replace  true
                                 :reset    true
                                 :template (. svg -outerHTML)})))))

(defn init []
  (-> (js/logseq.ready main)
      (.catch js/console.error)))
