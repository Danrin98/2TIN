package be.pxl.activity.api.request;

import java.time.LocalDate;

public class UserDetailRequest {
    private double weight_kg;
    private double height_cm;
    private LocalDate date_of_birth;
    private int age;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
