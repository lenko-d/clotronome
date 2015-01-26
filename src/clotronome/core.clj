(ns clotronome.core)
 (:use :reload-all clodiuno.core)
  (:use :reload-all clodiuno.firmata))

(defn led-pins []
  (range 2 14))

(def tempo-changer-pin 2)

(def init-delay 5000)

(defn blink [board led]
  (digital-write board led HIGH)
  (Thread/sleep (analog-read board tempo-changer-pin))
  (digital-write board led LOW))

(defn -main [& args]
  (let [board (arduino :firmata "/dev/ttyUSB0")] 
    (Thread/sleep init-delay)  ;; arduino needs some time to boot

    (doseq [pin (led-pins)]
      (pin-mode board pin OUTPUT))

    (enable-pin board :analog tempo-changer-pin)
    (Thread/sleep init-delay)

    (while true
      (doseq [led (led-pins)]  (blink board led))
      (doseq [led (reverse (led-pins))]  (blink board led)))))

