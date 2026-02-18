# Opdracht: Roll The Die

### Interface Rollable
Deze interface verwacht 1 methode: void roll()

### Class Die

Deze klasse representeert een dobbelsteen, met een (onveranderbaar) aantal zijden (minstens 4). 
Gooi een IllegalArgumentException als het aantal zijden niet correct is (dus minder dan 4).
De dobbelsteen geeft een waarde terug tussen 1 en het aantal zijden. De klasse implementeert de interface Rollable. Wanneer de methode roll() wordt aangeroepen wordt de huidige waarde van de dobbelsteen aangepast met een nieuwe willekeurige waarde tussen 1 en het aantal zijden. Voorzie de volgende methoden:
-	SIX_SIDED_DIE_EMOJI 🎲  Unicode: \uD83C\uDFB2
-	Die(int sides): constructor
-	void roll(): verandert de huidige waarde van de dobbelsteen
-	int getSides(): geeft het aantal zijden
-	int getValue(): geeft de huidige waarde
-   void setValue(value): pas de huidige waarde van de dobbelsteen aan, gooi een InvalidDieValue (unchecked exception) als de waarde
ongeldig is. Deze methode zal je moeten gebruiken om unit testen te schrijven.
-	String toString(): formaat [<huidige_waarde>], bijv. [5]

### Klasse DiceSet

Een verzameling van minstens 2 Die-objecten.
Gooi een IllegalArgumentException als het aantal dobbelstenen niet correct is.
Iedere dobbelsteen in de verzameling heeft hetzelfde aantal zijden. 
De klasse implementeert de interface Rollable.

Voorzie de volgende methoden:
- DiceSet(int sidesOnEachDie, int numberOfDice): constructor
- String getDescriptor(): formaat <numberOfDice>d<sidesOnEachDie> bijv. 6d5 (6 dobbelstenen met elke waarden van 1 tot 5 (incl.).
- int sum(): geef de som van de huidige waarde van elke dobbelsteen
- void roll(): verander de waarde van elke dobbelsteen 
- void rollIndividual(int i): verander de waarde van de i-de dobbelsteen, waarbij i de waarde 0 tot aantal dobbelstenen – 1 is.
- int getIndividual(int i): geeft de huidige waarde van de i-de dobbelsteen, waarbij i de waarde 0 tot aantal dobbelstenen – 1 is.
- void setIndividual(int i, int value): pas de huidige waarde van de i-de dobbelsteen aan.
De methode ga je gebruiken om unit testen te schrijven.
- List<Integer> values(): geeft alle waarden van de dobbelstenen.
- String toString(): de waarde van elke dobbelsteen in string formaat bijv. [4][6][2]

## Opdracht 1: schrijf unit testen voor de klasse Die en DiceSet

## Opdracht 2: implementeer de API zoals beschreven in de README.md.

Implementeer een service-klasse waarin je de huidige DiceSet bijhoudt en manipuleert.



