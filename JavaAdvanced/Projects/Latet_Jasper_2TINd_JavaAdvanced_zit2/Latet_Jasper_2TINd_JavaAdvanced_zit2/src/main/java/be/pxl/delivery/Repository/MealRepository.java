package be.pxl.delivery.Repository;

import be.pxl.delivery.domain.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {
    Boolean existsByCode(String code);

    Meal findByCode(String mealCode);
}
