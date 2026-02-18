# Meals

## 1. H2 database

http://localhost:8080/h2-console

De URL vind je terug in de console bij het opstarten van de Spring Boot applicatie.  
Default username is `sa` en het password is blanco (leeg).

## 2. API documentation

### Meal aanmaken

#### Request

`POST http://localhost:8080/meals`

```json
{
    "code": "MEAL_01",
    "name": "Spaghetti Carbonara",
    "origin": "CENTER",
    "destination": "EAST"
}
```

#### Response
    Bij succes: 201 CREATED
    Bij fout: 400 BAD REQUEST of 409 CONFLICT

### Meal toevoegen aan een levering (delivery)
#### Request

`PUT http://localhost:8080/deliveries/<delivery_id>/meals/<meal_code>`

waarbij `<delivery_id>` de primaire sleutel van een levering en `<meal_code>` de unieke code van een meal.

#### Response
    Bij succes: 200 OK
    Bij fout: 400 BAD REQUEST of 404 NOT FOUND

### Meal afleveren
#### Request

`PUT http://localhost:8080/meals/<meal_code>/deliver`

waarbij `<meal_code>` de unieke code van een meal.

#### Response
    Bij succes: 202 ACCEPTED
    Bij fout: 400 BAD REQUEST of 404 NOT FOUND

### Statistieken over maaltijden en leveringen opvragen
#### Request

`GET http://localhost:8080/meals/statistics`

#### Response
```json
{
    "statusCounts": {
        "ORDERED": 3,
        "IN_DELIVERY": 4,
        "DELIVERED": 5
    },
    "mostDeliveredLocation": "EAST",
    "mostPopularDestination": "CENTER",
    "averageMealsPerDelivery": 3.25,
    "completedDeliveries": 2
}
```

## 3. Valideer endpoints script
In de map `src/main/resources` vind je het script `valideer_endpoints.sh`.
Dit script test alle belangrijke endpoints en toont de HTTP statuscodes.
De verwachte output staat in `valideer_endpoint_output.txt`.
Je kan hiermee je resultaten vergelijken om te zien of je implementatie correct werkt.