# Order Manager

Order manager is a simple microservices application for product orders management.

This simple application consent to:
* Add a product 
* Edit qty of existing products
* Add an order
* Validate an order


## Architecture 
Order manager is a microservices application written in **Java** using **Spring**. It consist of four microservices that communicates via REST-API. 

The entire application relies on several technologies to ensure an high software quality.
* Virtualization > **Docker**
* Message broker > **Kafka**
* Service discovery > **Consul**
* Relational database > **PostgreSQL**
---
* **product-service** >  It handles the products. 
  * `POST /products` > create a new product
  * `GET /products/{name}` > find a product
  * `GET /products` > find all products 
  * `POST /findproducts/bynames` > find all products based on a given list 
  * `PATCH /products` > update product qty. 
---
* **order-service** >  It handles the orders. 
  * `POST /orders` crea un nuovo ordine (dati cliente, indirizzo, articoli ordinati e totale, passati nel corpo della richiesta)
  * `GET /orders/{id}` trova un ordine (dato l'id) 
  * `GET /orders` trova tutti gli ordini  
  * `GET /findorders/customer/{customer}` trova tutti gli ordini di un cliente (dato il cliente)  
  * `GET /findorders/product/{product}` trova tutti gli ordini contenenti un certo prodotto (dato il prodotto)  
---
* **order-validation-service** >  It validates the orders. 
  * `GET /ordervalidations/{id}` > validate an order 
---
* **api-gateway** >  exposed on 8080 port, it's the API getaway of the application
	* expose **product-service** on path `/productservice`   
	* expose **order-service** on path `/orderservice`  
	* expose **order-validation-service** on path `/ordervalidationservice`


## Costruzione 

Per costruire questa applicazione: 

* eseguire lo script `build-order-manager.sh`


## Esecuzione 

Per avviare questa applicazione: 
* eseguire lo script `run-order-manager.sh`

> Per verificare che l'applicazione sia stata avviata correttamente (ci vuole circa un minuto) usare il comando `docker ps` per verificare
> che lo *status* dei diversi container sia *healthy* e non *starting*.
 
Per arrestare l'applicazione: 
* eseguire lo script `stop-order-manager.sh`

### Esecuzione con profilo debug
In alternativa è possibile eseguire l'applicazione con un profilo di debug che fornisce due UI, nello specifico per Kafka e Postgres, che aiutano a visualizzare i dati contenuti nei db ed i messaggi scambiati tra i servizi.

Per avviare con profilo di debug: 
* eseguire il comando `docker compose --profile debug up -d`

Per arrestare l'applicazione (inclusi gli strumenti di debug): 
* eseguire il comando `docker compose --profile debug down -d`

## Script
Sono anche forniti alcuni script di esempio per utilizzare l'applicazione: 


* per inizializzare le basi di dati con alcuni dati di esempio, eseguire gli script `do-init-products.sh` e `do-init-orders.sh` <br>
> L'operazione di inizializzazione è necessaria soltanto al primo avvio dell'applicazione. Una volta inizializzati, tutti i dati
> verranno salvati in modo persistente all'interno di volumi Docker.
  
* lo script `remove-order-manager-data.sh` elimina tutti i dati posti all'interno dei volumi docker: 

* lo script `do-get-products.sh` trova tutti i prodotti 

* lo script `do-get-product.sh` trova un prodotto 

* lo script `do-get-orders.sh` trova tutti gli ordini 

* lo script `do-get-order.sh` trova un ordine 

* lo script `do-validate-order.sh` convalida un ordine 

Ed inoltre: 

* lo script `do-validate-orders-123.sh` convalida gli ordini 1, 2 e 3

* lo script `do-update-products.sh` aggiorna le quantità disponibili di alcuni ordini 

Ed inoltre ancora: 

* lo script `do-test-1.sh` esegue alcuni test (crea prodotti e ordini e poi esegue le validazioni degli ordini 1, 2 e 3, che sono tutti validi)

* lo script `do-test-2.sh` esegue degli ulteriori test (modifica alcuni prodotti e poi esegue di nuovo le validazioni degli ordini 1, 2 e 3: ora gli ordini 1 e 2 non sono più validi, mentre l'ordine 3 è ancora valido)

* lo script `do-test-3.sh` esegue alcuni ulteriori test (crea un nuovo ordine e poi esegue delle validazioni, che sono tutte non valide)

* nota: questi test possono essere utilmente eseguiti in sequenza, senza eseguire prima nessuno degli altri script  

## Descrizione delle attività da svolgere 
Si veda la descrizione del progetto sul sito web del corso di [Architettura dei sistemi software](http://cabibbo.inf.uniroma3.it/asw/).

## Attività svolte

* Introduzione di due basi di dati **PostgreSQL** rispettivamente per i servizi order-service e product-service;
* Utilizzo di container Docker per l'esecuzione di tutti i servizi e delle basi di dati;
* I servizi **order-service**, **product-service** ed **order-validation-service** vengono eseguiti in container Docker, utilizzando due repliche per ogni servizio;
* Introduzione di **Kafka** come message broker per lo scambio di messaggi tra i servizi, anch'esso eseguito in un container Docker;
* Introduzione di una terza base di dati dedicata ad **order-validation-service**, contenente solo i dati necessari alla validazione degli ordini (anch'essa eseguita in un container Docker);
* Utilizzo di **Docker compose** per mandare in esecuzione tutto il sistema;
* [EXTRA] Introduzione di **volumi Docker** per le basi di dati, per mantenere i dati anche dopo l'arresto dell'applicazione;
* [EXTRA] Introduzione di un profilo *'debug'* nel Docker compose, il quale permette l'esecuzione di due UI utili per debuggare la gestione dei dati e lo scambio di messaggi.


Contributors:
- Matteo Cardilli - <https://github.com/TheBlind11>
- Gianluca Farinaccio - <https://github.com/isRoutine>
- Daniele Ferneti - <https://github.com/DanieleFerneti>
- Alexis Martinez - <https://github.com/osvaldomar144>