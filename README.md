# Clotronome

## Metronome in Clojure and Arduino


The goal of this project is to build a metronome that has big 
LED display that can be easily seen.



Initial prototype:
![Clotronome metronome](/images/metronome.jpg)


## TODO 
- [ ]  Use LED strips or a LED matrix
Example LED strips:
![LEDs](/images/st1.jpg)
![LEDs](/images/st2.jpg)
![LEDs](/images/st3.jpg)

- [ ]  Use WiFi/Bluetooth shield to control from a PC  or  AlaMode board to control from  Raspberry Pi running Clojure

- [ ] Implement the abilty to program complex beat patterns:
From the Clojure REPL:
For example if the music piece has 2/4 beat for the first 3 measures and 3/4 beat for the next 7 measures we can send the following to Arduino using Clojure via Firmata:
repl=> (beat 2/4  3  3/4  7) 
repl=> (tempo 70)

We could specify different LED colors for different measures
repl=> (beat 2/4  3 red   3/4  7 green) 
