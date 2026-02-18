package be.pxl.student.eventplatform.domain;

//TODO vul de klasse aan zoals beschreven in de opdracht

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private LocalDateTime eventDate;
    @Enumerated(EnumType.STRING)
    private VenueArea venueArea;
    private String internalCode;
    private double price;
    private int capacity;
    // Aanvullend op de oorspronkelijke opgave (relatie)
    @OneToMany(cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Event() {}

    public Event(String name, LocalDateTime eventDate, VenueArea venueArea, String internalCode, double price, int capacity) {
        this.name = name;
        this.eventDate = eventDate;
        this.venueArea = venueArea;
        this.internalCode = internalCode;
        this.price = price;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public VenueArea getVenueArea() {
        return venueArea;
    }

    public void setVenueArea(VenueArea venueArea) {
        this.venueArea = venueArea;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    //TODO 1 Berken de beschikbare plaatsen op basis van de registraties
    // Business rule: bezette plaatsen = aantal registraties met status RESERVED of CHECKED_IN
    // CANCELLED registraties tellen niet mee.
    //TODO 2 UNIT TEST deze methode
    public int availableSeats() {
        int availableSeats = this.getCapacity();
        for (Registration registration : registrations) {
            if (registration.getStatus().equals(RegistrationStatus.RESERVED) || registration.getStatus().equals(RegistrationStatus.CHECKED_IN)) {
                availableSeats--;
            }
            if (registration.getStatus().equals((RegistrationStatus.CANCELLED) )) {
                availableSeats++;
            }
        }
        return availableSeats;
    }


}
