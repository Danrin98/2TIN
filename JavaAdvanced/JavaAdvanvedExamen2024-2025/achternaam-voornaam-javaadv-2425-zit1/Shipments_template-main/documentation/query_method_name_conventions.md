## Spring Data JPA Query Method Name Conventions

Spring Data JPA biedt de mogelijkheid om querymethoden te definiëren door gebruik te maken van methodenamen die automatisch worden vertaald naar databasequeries. Hier is een overzicht van de conventies die worden gebruikt om querymethoden te definiëren.

### Basisstructuur

De naam van een querymethode bestaat uit drie delen:

- **Prefix**: De prefix definieert het type query.
```
findEntityBy
findAllEntitiesBy
```
- **Eigenschapsnaam**: Dit is de naam van het veld in de entiteit waarop je wilt filteren.
- **Keywords**: Deze geven aan hoe de query moet worden opgebouwd, zoals AND, OR, GreaterThan, LessThan, etc.

### Voorbeelden

```
List<Employee> findEmployeeByLastname(String lastname);
List<Employee> findAllEmployeesByAge(int age);
List<Employee> or Optional<Employee> findEmployeeByLastnameAndFirstname(String lastname, String firstname);
List<Employee> findAllEmployeesByAgeGreaterThanAndDepartment(int age, String department);
List<Employee> findEmployeeByDepartment_Name(String departmentName);
List<Employee> findAllEmployeesByManager_Lastname(String managerLastname);
Optional<Employee> findEmployeeByEmail(String email);
```
### Overzicht van veelgebruikte keywords
- **Vergelijkingen**: Is, Equals, Between, LessThan, LessThanEqual, GreaterThan, GreaterThanEqual
- **Logische operatoren**: And, Or
- **Aard van waarde**: True, False, Null, NotNull, Like, NotLike, Containing, StartingWith, EndingWith, In, NotIn
