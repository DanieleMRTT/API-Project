

Il progetto consiste nella realizzazione di un componente SpringBoot che espone una serie di API per funzioni demografiche e meteorologiche sul territorio italiano.
Il componente risponde alle seguenti esigenze:

- ottenere la lista delle citta' con numero di abitanti maggiore di un valore (passato in input) //POST?
- ritornare la lista di comuni in una data regione con numero di abitanti maggiore di un valore (passato in input)//Post
- ritornare la lista di comuni di una data provincia con numero di abitanti maggiore di un valore (passato in input)//((POST))
- ritornare le previsioni meteo di una data citta' passata in input
- ritornare la media delle temperature di un certo numero di giorni (parametro passato in input) di una data citta' (parametro passato in input)
- data una provincia ed un giorno, ritornare la media delle temperature di tutti i comuni appartenenti a quella provincia per quel giorno

I dati provengono dai seguenti link:
- Repository dei comuni italiani aggiornato al 2018: https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel/tree/master
- API meteo gratuite: https://open-meteo.com/


Developed in 5 days

Improvements to implement:
    - improve input validation and exceptions management
    - improve the use of data : only pass the necessary data between client and controller, review DTO's and Controller Methods
    - learn Spring security and implement authentication/authorization
