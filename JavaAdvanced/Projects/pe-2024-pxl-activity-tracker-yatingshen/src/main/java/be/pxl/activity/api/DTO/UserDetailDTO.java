package be.pxl.activity.api.DTO;


import be.pxl.activity.domain.User.UserDetails;

import java.time.LocalDate;

public class UserDetailDTO {
    private double weight_kg;
    private double height_cm;
    private LocalDate date_of_birth;

    public UserDetailDTO(UserDetails userDetails){
        this.weight_kg = userDetails.getWeight_kg();
        this.height_cm = userDetails.getHeight_cm();
        this.date_of_birth = userDetails.getDate_of_birth();
    }
    public double getWeight_kg() {
        return weight_kg;
    }
    public double getHeight_cm() {
        return height_cm;
    }
    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

}
