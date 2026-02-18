package be.pxl.activity.api.DTO;

public class NutritionixDTO {
    private String query;
    private double weight_kg;
    private double height_cm;
    private int age;

    public NutritionixDTO(String query, double weight_kg, double height_cm, int age) {
        this.query = query;
        this.weight_kg = weight_kg;
        this.height_cm = height_cm;
        this.age = age;
    }

    public String getQuery() {
        return query;
    }

    public double getWeight_kg() {
        return weight_kg;
    }

    public double getHeight_cm() {
        return height_cm;
    }

    public int getAge() {
        return age;
    }
}
