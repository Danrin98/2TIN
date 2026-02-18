package be.pxl.activity.domain.User;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

@Entity
@Table(name = "usersDetails")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userProfileId;
    private double weight_kg;
    private double height_cm;
    private LocalDate date_of_birth;
    public UserDetails(){

    }

    public int getUserProfileId() {
        return userProfileId;
    }

    public double getWeight_kg() {
        return weight_kg;
    }

    public void setWeight_kg(double weight_kg) {
        this.weight_kg = weight_kg;
    }

    public double getHeight_cm() {
        return height_cm;
    }

    public void setHeight_cm(double height_cm) {
        this.height_cm = height_cm;
    }

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
    public int getAge(){
        if (date_of_birth == null) {
            throw new IllegalStateException("Date of birth is not set.");
        }
        if (date_of_birth.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future.");
        }
        return Period.between(date_of_birth, LocalDate.now()).getYears();
    }
}
