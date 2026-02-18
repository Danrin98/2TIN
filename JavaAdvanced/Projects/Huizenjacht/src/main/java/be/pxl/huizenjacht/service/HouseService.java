package be.pxl.huizenjacht.service;

import be.pxl.huizenjacht.api.House;
import be.pxl.huizenjacht.api.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseService {

    Map<String, House> houses = new HashMap<>();

    public House createHouse(House house) {
        houses.put(house.getCode(), house);
        return house;
    }

    public List<House> getHouses() {
        return new ArrayList<>(houses.values());
    }

    public House updateHouse(String code, House house) {
        houses.put(code, house);
        return house;
    }

    public House updateHouseSOLD(String code) {
        House house =  houses.get(code);
        house.markAsSold();
        return house;
    }

    public void deleteHouse(String code) {
        houses.remove(code);
    }
}
