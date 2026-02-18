package be.pxl.huizenjacht.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class House {
    @JsonProperty("code")
    @NotBlank(message="Code must not be blank")
    private String code;
    @NotBlank(message="Code must not be blank")
    private String name;
    private Status status;
    @NotBlank(message="city must not be blank")
    private String city;
    @Min(value = 50, message="Value must be atleast 50 m²")
    private int area;
    @NotNull(message="EPCScore must not be null")
    private EPCScore epcScore;
    public static final double BASEPRICE = 2356.75;

    public House(String code, String name, String city, int area, EPCScore epcScore) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.status = Status.FOR_SALE;
        this.area = area;
        this.epcScore = epcScore;
    }

    public House(){
        this.status = Status.FOR_SALE;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public EPCScore getEpcScore() {
        return epcScore;
    }

    public void setEpcScore(EPCScore epcScore) {
        this.epcScore = epcScore;
    }

    public double getPrice() {
        double price = BASEPRICE * area * epcScore.getPercentage();
        if (city.equals("Hasselt") || city.equals("Genk")){
            price +=  price * 0.25;
        }
        return price;
    }

    public void markAsSold() {
        if (status == Status.SOLD) {
            throw new IllegalStateException("Cannot mark as sold");
        }
        status = Status.SOLD;
    }

    @Override
    public String toString() {
        return "House{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", city='" + city + '\'' +
                '}';
    }
}
