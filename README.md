
## Requirements
* All items have a SellIn value which denotes the number of days we have
to sell the item.
* All items have a Quality value which denotes how valuable the item is.
* At the end of each day our system lowers both values for every item.

* Once the sell by date has passed, Quality degrades twice as fast.
* The Quality of an item is never negative.
* ”Aged Brie” actually increases in Quality the older it gets.
* The Quality of an item is never more than 50.
* ”Sulfuras”, being a legendary item, never has to be sold or decreases in
Quality.
* ”Backstage passes”, like aged brie, increases in Quality as its SellIn value approaches: Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but Quality drops to 0 after the concert.
* do not alter the Item class or Items property!


## Steps

1. "Plan" erstellen (README)
2. Analyse und ausarbeiten der Anforderungen
3. VCS, Build-Env, statische Code-Analyse und automatische Tests einrichten
4. Erstellen bzw. reparieren der bestehenden Testbasis
5. Erweitern der Testbasis um alle Anforderungen abzudecken
6. Auflösen und expandieren komplexer Strukturen (bewusste Duplizierung um Komplexität zu reduzieren)
7. Aufteilen in Funktionen nach SRP und DRY (d.h. wieder Kompaktieren)
8. Mögliche Pattern ermitteln und implementieren
9. Struktur überarbeiten und Koppung prüfen (Packages, visibility)
10. Feinheiten verbessern (Naming, Syntax, etc.)
