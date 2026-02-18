package be.pxl.delivery.domain;

import be.pxl.delivery.Exception.InvalidDeliveryIdException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Delivery")
public class Delivery {

    @Id
    private Long id;
    @Enumerated(EnumType.STRING)
    private Location startLocation;
    private int maxMealCount;
    // TODO User story 2
    //  List<Meal> mealList
    @OneToMany(mappedBy = "delivery")
    private List<Meal> mealList = new ArrayList<>();


    public Long getId() { return id; }

    public Location getStartLocation() { return startLocation; }

    public void setStartLocation(Location startLocation) { this.startLocation = startLocation; }

    public int getMaxMealCount() { return maxMealCount; }

    public void setMaxMealCount(int maxMealCount) { this.maxMealCount = maxMealCount; }

    public int getMealCount() {
        // TODO implementeer deze method.
        // Deze methode geeft het aantal maaltijden die zijn toegevoegd aan deze levering.
        return mealList.size();
    }

    // User story 2
    public void addMeal(Meal meal) {
        // controleer dat de status van de maaltijd ORDERED is
        // controleer of de levering niet vol zit
        // controller dat de origin van de maaltijd en de startlocation van de levering hetzelfde zijn
        // gooi InvalidDeliveryException (unchecked exception) bij businessfouten

        // als er geen businessfouten zijn voeg je de maaltijd toe en wijzig je de status naar IN_DELIVERY
        if(meal.getStatus() == MealStatus.ORDERED && maxMealCount > mealList.size() ) {
          meal.setDelivery(this);
        } else if (meal.getOrigin() == startLocation) {
            throw new InvalidDeliveryIdException("Meal has invalid origin");
        } else{
            throw new IllegalStateException("Maaltijd is al in een levering, of levering zit vol");
        }

    }

    // User story 3
    // implementeer deze methode en schrijf unit testen
    public boolean isCompleted() {
        return this.mealList.stream().filter(s -> s.getStatus() != MealStatus.DELIVERED).count() <= 0 ;
    }
}
