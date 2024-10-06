package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.CityResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.City;

import java.util.List;
import java.util.UUID;

public interface CityService {
    boolean createCity(String cityName);
    CityResponseDTO updateCity(UUID cityId, String newCityName);
    List<CityResponseDTO> getAllCities();
    CityResponseDTO getCity(UUID cityId);
    boolean deleteCity(UUID cityId);
    City getCityByCityId(UUID cityId);

    void saveCity(City city);
}
