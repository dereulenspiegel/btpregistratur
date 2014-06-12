BTP Registratur [![Build Status](https://travis-ci.org/dereulenspiegel/btpregistratur.svg?branch=master)](https://travis-ci.org/dereulenspiegel/btpregistratur)
===============

Einleitung
----------
Aktuell werden in vielen Einsatzeinheiten die Registraturen in den Betreungsstellen noch mit Stift und
Papier geführt. Stift und Papier sind zwar ein sehr bewährtes und robustes Einsatzmittel, aber in der
Praxis ergeben sich viele Probleme. Bei großem Patientenandrang ist es einer einzelnen Person an der
Registratur kaum möglich zeitnah alle Personen zu registrieren. Die Zusammenarbeit ist jedoch schwierig,
da die Registratur oft eine durchgehende Nummerierung aufweisen muss. 
Auch während des laufenden Einsatzes ergeben sich Schwierigkeiten. Die Registratur wird in der Regel
vor allem dazu erstellt auskunftsfähig zu sein. Aber aus Papierlisten lässt sich sehr schwer ablesen
wieviele Personen sich gerade in der Betreungsstelle aufhalten. Es lässt sich auch schwer nach Personen
suchen. Zusätzlich sind die Listen in der Regel Unikate, daher es kann immer nur eine Person suchen
durchführen und die Listen unersetzbar.

Aktueller Lösungsansatz
-----------------------
Die oben genannten Probleme lassen sich mit Mitteln der modernen Informationstechnik lösen. Android
bietet sich hier als kostengünstige und offene Basis an. Android-basierte Tablets können in Verbindung
mit Tastaturen als Arbeitsstattion für die Kräfte in der Registratur dienen. 
Dadurch liegen alle Informationen direkt in digitaler Form vor und können einfach gesichert, dupliziert
und ausgewertet werden.

In diesem Projekt soll zunächst eine Android-Applikation entwickelt werden mit der es möglich ist
alle relevanten Informationen für die Registratur zu erfassen. Die Appliaktion soll auch die 
Möglichkeit haben sich ohne Server mit anderen zu synchronisieren. Das dafür nötige Protokoll soll
im Rahmen dieses Projektes ebenfalls entstehen.

gewünschte Features
-------------------
* Erfassung aller Personendaten
* Liste der Personen soll durchsuchbar sein
* Synchronisation zwischen mehreren Geräten ohne Server
* Export als XML oder JSON um später daraus HTML oder PDF zu generieren (basierend auf Templates)
* einfache Stastiken generieren
