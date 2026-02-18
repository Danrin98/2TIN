package be.pxl.delivery.service;

import be.pxl.delivery.Exception.InvalidDeliveryIdException;
import be.pxl.delivery.Exception.InvalidMealCodeException;
import be.pxl.delivery.Exception.MealOrDeliveryNotFound;
import be.pxl.delivery.Repository.IDeliveryRepository;
import be.pxl.delivery.Repository.MealRepository;
import be.pxl.delivery.domain.Delivery;
import be.pxl.delivery.domain.Meal;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    private final IDeliveryRepository deliveryRepository;
    private final MealRepository mealRepository;

    public DeliveryService(IDeliveryRepository deliveryRepository, MealRepository mealRepository) {
        this.deliveryRepository = deliveryRepository;
        this.mealRepository = mealRepository;
    }

    @Transactional
    //Mijn many to many wou niet werken geen idee hoe of wat meer tbh
    // User story 2
    public void addMealToDelivery(Long deliveryId, String mealCode) {
        // TODO: implementeer deze methode
        //  zoek de levering adhv deliveryId, zorg voor foutafhandeling als deze niet bestaat
        //  zoek de maaltijd adhv mealCode, zorg voor fouthafhandeling als deze niet bestaat
        //  de methode addMeal in de klasse Delivery bevat de overige business regels

        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(MealOrDeliveryNotFound::new);
        if (!mealRepository.existsByCode(mealCode)) {
            throw new MealOrDeliveryNotFound();
        };

        delivery.addMeal(mealRepository.findByCode(mealCode));
        mealRepository.save(mealRepository.findByCode(mealCode));
    }
}
