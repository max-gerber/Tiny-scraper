# Tiny Leaders deck scraper + analyser
Not very user friendly atm, still a work in progress.

To run with the ```decks.json``` already present (scraped 08 Sep 2023) set ```performScrape = false``` in ```Main.java```.
If you want to perform the scrape (takes me a little under half an hour as there are ~3000 api requests being made), keep this variable set to true.

To get stats for a specific commander/partner-pair, add the full name of the commander to  the ```commander``` variable in ```Main.java```, likewise for partners (leave ```partner``` as null or an empty string if there is none)

eg. 
```commander = Rograkh, Son of Rohgahh```
```partner =  Yoshimaru, Ever Faithful```

## ToDos:
* Add UI

* Support other deckbuilding websites
