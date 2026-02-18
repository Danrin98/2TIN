package be.pxl.delivery.service;

import be.pxl.delivery.Exception.InvalidMealCodeException;
import be.pxl.delivery.Repository.MealRepository;
import be.pxl.delivery.api.request.CreateMealRequest;
import be.pxl.delivery.api.response.MealStatistics;
import be.pxl.delivery.domain.Delivery;
import be.pxl.delivery.domain.Meal;
import be.pxl.delivery.domain.MealStatus;
import be.pxl.delivery.utils.MealStatisticsUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {
    private final MealRepository mealRepository;

    public MealService(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }


    // User story 1
    public void createMeal(CreateMealRequest createMealRequest) {
        // TODO : controleer of de mealCode reeds bestaat.
        //  Bij dubbele code gooi je een unchecked InvalidMealCodeException
        //  Maak de nieuwe maaltijd aan en sla op in de database.

        Boolean existsByCode = mealRepository.existsByCode(createMealRequest.code());
        if (existsByCode) {
            throw new InvalidMealCodeException("Maaltijd of levering niet gevonden");
        }
        Meal newMeal = new Meal();

        newMeal.setCode(createMealRequest.code());
        newMeal.setName(createMealRequest.name());
        newMeal.setOrigin(createMealRequest.origin());
        newMeal.setDestination(createMealRequest.destination());
        newMeal.setStatus(MealStatus.ORDERED);

        mealRepository.save(newMeal);
    }

    // User Story 3
    public void markAsDelivered(String mealCode) {
        // TODO: zoek de maaltijd op in de database
        //  Zorg voor correcte foutafhandeling als de maaltijd niet bestaat.
        //  Implementeer de gevraagde validaties.
        //  Zorg dat de status correct wordt gewijzigd en alle gegevens correct worden opgeslagen in de databank.

        Meal meal = mealRepository.findByCode(mealCode);
        if (meal == null) {
            throw new InvalidMealCodeException("Maaltijd niet gevonden");
        }
        meal.setStatus(MealStatus.DELIVERED);
        mealRepository.save(meal);
    }

    // User story 4
    public MealStatistics getMealStatistics() {
        List<Meal> meals = null; // TODO: assign meals
        List<Delivery> deliveries = null; // TODO assign deliveries

        MealStatistics stats = new MealStatistics();
        stats.setStatusCounts(MealStatisticsUtil.calculateStatusCounts(meals));
        stats.setMostDeliveredLocation(MealStatisticsUtil.calculateMostDeliveredLocation(meals));
        stats.setMostPopularDestination(MealStatisticsUtil.calculateMostPopularDestination(meals));
        stats.setAverageMealsPerDelivery(MealStatisticsUtil.calculateAverageMealsPerDelivery(deliveries));
        stats.setCompletedDeliveries(MealStatisticsUtil.calculateCompletedDeliveries(deliveries));

        return stats;
    }

}
