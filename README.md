

The project involves the development of a SpringBoot component that exposes a series of APIs for demographic and meteorological functions in the Italian territory. 

The component addresses the following needs:

- Obtain the list of cities with a population greater than a specified value (passed as input)
- Return the list of municipalities in a given region with a population greater than a specified value (passed as input) 
- Return the list of municipalities in a given province with a population greater than a specified value (passed as input) 
- Return the weather forecast for a given city passed as input
- Return the average temperatures for a certain number of days (parameter passed as input) for a given city (parameter passed as input)
- Given a province and a day, return the average temperatures of all municipalities belonging to that province for that day

The data is sourced from the following links:

- Italian municipalities repository updated to 2018: https://github.com/MatteoHenryChinaski/Comuni-Italiani-2018-Sql-Json-excel/tree/master
- Weather Forecast API : https://open-meteo.com/


Developed in 5 days

Improvements to implement:

- improve input validation and exceptions management
- improve the use of data : only pass the necessary data between client and controller, review DTO's and Controller Methods
- learn Spring security and implement authentication/authorization
