package be.pxl.delivery.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "meals")
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String name;
    @Enumerated(EnumType.STRING)
    private Location origin;
    @Enumerated(EnumType.STRING)
    private Location destination;
    private MealStatus status;

    @ManyToOne
    private Delivery delivery;

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        this.setStatus(MealStatus.IN_DELIVERY);
    }

    public Meal() {
    }

    public Meal(String code, String name, Location origin, Location destination, Delivery delivery) {
        this.code = code;
        this.name = name;
        this.origin = origin;
        this.destination = destination;
        this.delivery = delivery;
        this.status = MealStatus.ORDERED;
    }

    public Long getId() { return id; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Location getOrigin() { return origin; }

    public void setOrigin(Location origin) { this.origin = origin; }

    public Location getDestination() { return destination; }

    public void setDestination(Location destination) { this.destination = destination; }

    public MealStatus getStatus() { return status; }

    public void setStatus(MealStatus status) { this.status = status; }

}
