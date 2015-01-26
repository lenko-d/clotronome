(ns clotronome.core)
(require '[clodiuno.core :as cld])
(require '[clodiuno.firmata :as cldf])

(defn led-pins []
  (range 2 14))

(def tempo-changer-pin 2)

(def init-delay 5000)

(defn blink [board led]
  (cld/digital-write board led cld/HIGH)
  (Thread/sleep (cld/analog-read board tempo-changer-pin))
  (cld/digital-write board led cld/LOW))


(defn -main [& args]
  (let [board (cld/arduino :firmata "/dev/ttyUSB0")] 
    (Thread/sleep init-delay)  ;; arduino needs some time to boot

    (doseq [pin (led-pins)]
      (cld/pin-mode board pin cld/OUTPUT))

    (cld/enable-pin board :analog tempo-changer-pin)
    (Thread/sleep init-delay)

    (while true
      (doseq [led (led-pins)]  (blink board led))
      (doseq [led (reverse (led-pins))]  (blink board led)))))

