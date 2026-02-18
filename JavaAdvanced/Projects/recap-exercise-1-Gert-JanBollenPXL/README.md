# Dice Rolling API Documentation

## Overview

The Dice Rolling API allows users to roll a specified number of dice with a specified maximum number on each die. Users can choose to roll all dice at once or roll each die separately. The API returns the results of the dice rolls, and it can also provide the highest sum of all the dice rolls.

## Endpoints

### 1. Create The Dice Set

- **URL**: `/diceset`
- **Method**: POST
- **Body**:

```
{
  "numberOfDice": 4,
  "maxNumber": 9
 }
```
Waarbij:

- `numberOfDice` (integer, required): The number of dice in the set.
- `maxNumber` (integer, required): The maximum number on each die.

- **Response**:
    - Status: 201 CREATED

### 2. Get The Current DiceSet

- **URL**: `/diceset`
- **Method**: GET
- **Response**:
    - Status: 200 OK
    - Body: A json-array of integers representing the results of all the dice in the set. For example, `[3, 5, 2, 4]`.


### 3. Roll All Dice

- **URL**: `/diceset/roll`
- **Method**: POST

- **Response**:
    - Status: 200 OK
    - Body: An array of integers representing the values on all the dice. For example, `[3, 5, 2]`.

### 4. Roll a Single Die

- **URL**: `/diceset/roll?dice=1`
- **Method**: POST
- **Response**:
    - Status: 200 OK
    - Body: An array of integers representing the values on all the dice. For example, `[3, 5, 2]`.

### 3. Get Highest Sum

- **URL**: `/highest-sum`
- **Method**: GET
- **Response**:
    - Status: 200 OK
    - Body: An integer representing the highest sum of all the dice rolls. For example, `12`.

## Error Handling

The API should return appropriate HTTP status codes and error messages in case of invalid requests or other errors. 
Common status codes to use include 400 (Bad Request) for invalid input parameters and 500 (Internal Server Error) for unexpected server issues.
