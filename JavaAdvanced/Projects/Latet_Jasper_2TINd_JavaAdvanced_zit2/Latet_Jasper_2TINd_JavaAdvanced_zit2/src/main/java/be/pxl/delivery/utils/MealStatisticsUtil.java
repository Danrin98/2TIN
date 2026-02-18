package be.pxl.delivery.utils;


import be.pxl.delivery.domain.Delivery;
import be.pxl.delivery.domain.Meal;
import be.pxl.delivery.domain.MealStatus;

import java.util.List;
import java.util.Map;

// User story 4: statistieken
public class MealStatisticsUtil {

    // tel het aantal maatlijden per MealStatus
    public static Map<MealStatus, Long> calculateStatusCounts(List<Meal> meals) {
        throw new UnsupportedOperationException();
    }

    // De locatie waar de meeste maaltijden effectief geleverd zijn
    // Als er nog geen maaltijden zijn afgeleverd, waarde UNKNOWN
    // Bij gelijkspel (bv. CENTER en EAST allebei 4) → "CENTER, EAST" (komma gescheiden).
    public static String calculateMostDeliveredLocation(List<Meal> meals) {
        throw new UnsupportedOperationException();
    }

    // De bestemming die het meest gekozen wordt door klanten, ongeacht de status van de maaltijd.
    // Als er geen maaltijden zijn → "UNKNOWN".
    // Bij gelijkspel (bv. CENTER en EAST allebei 4) → "CENTER, EAST" (komma gescheiden).
    public static String calculateMostPopularDestination(List<Meal> meals) {
        throw new UnsupportedOperationException();
    }

    // Het gemiddelde aantal maaltijden dat aan een levering gekoppeld is.
    // (niet maxCount, maar het effectief aantal toegevoegde maaltijden bij een levering)
    public static double calculateAverageMealsPerDelivery(List<Delivery> deliveries) {
        throw new UnsupportedOperationException();
    }

    // Het aantal leveringen waarvan alle maaltijden afgeleverd zijn.
    public static long calculateCompletedDeliveries(List<Delivery> deliveries) {
        throw new UnsupportedOperationException();
    }
}
