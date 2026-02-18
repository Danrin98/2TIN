package be.pxl.cargo.domain;

import jakarta.persistence.*;

@Entity
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Location startLocation;
    @Enumerated(EnumType.STRING)
    private Location endLocation;
    private double maxCapacity;
    private boolean arrived;

    public Shipment() {
    }

    public Shipment(Location startLocation, Location endLocation, double maxCapacity) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.maxCapacity = maxCapacity;
    }

    public double totalWeight() {
        // TODO gebruik de Stream API om het totale gewicht van alle cargos te berekenen.
        return 0;
    }

//    public void addCargo(Cargo cargo) {
//        if (arrived) {
//            throw new ShipmentException("Shipment already arrived.");
//        }
//        if (cargo.getCurrentLocation() != startLocation) {
//            throw new ShipmentException("Cargo at wrong location.");
//        }
//        if (cargo.getCargoStatus() == CargoStatus.DELIVERED) {
//            throw new ShipmentException("Cargo already delivered.");
//        }
//        if (totalWeight() - cargo.getWeight() > maxCapacity) {
//            throw new ShipmentException("Shipment capacity exceeded.");
//        }
//        cargoList.add(cargo);
//        cargo.setCargoStatus(CargoStatus.MOVING);
//    }

     public void setArrived() {
//        if (arrived) {
//            throw new ShipmentException("Shipment already arrived.");
//        }
//        // TODO: zorg dat ieder cargo in cargoList de endLocation als huidige locatie krijgt toegekend.
//        //   In de klasse Cargo is een methode voorzien die je kan gebruiken.
        arrived = true;
     }

    public boolean isArrived() {
        return arrived;
    }

    public Location getEndLocation() {
        return endLocation;
    }
}
