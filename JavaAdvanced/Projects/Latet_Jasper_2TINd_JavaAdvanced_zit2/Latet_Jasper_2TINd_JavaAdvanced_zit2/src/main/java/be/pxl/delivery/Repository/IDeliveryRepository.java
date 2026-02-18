package be.pxl.delivery.Repository;

import be.pxl.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryRepository extends JpaRepository<Delivery,Long> {
}
