# Event Platform - Java Advanced Exam

In de code vind je //TODO markers terug waar je aanpassingen moet doen.

## Opgave updates
Aanvullend op de oorspronkelijke opgave:

### Domeinconcepten
Event
- registrations (List): Lijst van registraties voor dit event (relatie).

Attendee
- OPTIONEEL: registrations (List): Lijst van registraties van deze attendee (relatie).


## Resources
Volgende bestanden vind je terug in de resources folder. Normaal gezien hoef je deze niet aan te passen.
Enkel indien nodig kan je de server port 8080 aanpassen in application.properties. Zorg er dan ook voor dat je de requests in de .http bestanden aanpast indien nodig.
- application.properties: Configuratie voor de Spring Boot applicatie.
- data.sql: Initialisatie data voor in de database. Deze wordt automatisch ingeladen bij het opstarten van de applicatie.
- eventplatform-requests.http: Basis set van requests die je kan gebruiken om je applicatie te testen
- eventplatform-requests-full.http: Uitgebreide set van requests die je kan gebruiken om je applicatie te testen