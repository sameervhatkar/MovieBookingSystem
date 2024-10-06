package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.CityResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.City;
import dev.sameer.bookmyshowreviseedition.Exceptions.CityNotFoundException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.CityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CityServiceImpl implements CityService {
    @Autowired
    private CityRepo cityRepo;

    @Override
    public boolean createCity(String cityName) {
        City city = cityRepo.findCityByCityName(cityName).orElse(
                new City(cityName)
        );
        cityRepo.save(city);
        return true;
    }

    @Override
    public CityResponseDTO updateCity(UUID cityId, String newCityName) {
        return null;
    }

    @Override
    public List<CityResponseDTO> getAllCities() {
        List<City> cities = cityRepo.findAll();
        List<CityResponseDTO> cityResponseDTOList = new ArrayList<>();
        for(City city : cities)
            cityResponseDTOList.add(EntityToDTOMapper.convertCityEntitytoDTO(city));
        return cityResponseDTOList;
    }

    @Override
    public CityResponseDTO getCity(UUID cityId) {
        City city = cityRepo.findById(cityId).orElseThrow( () ->
                new CityNotFoundException("City with " + cityId + " not found")
        );
        return EntityToDTOMapper.convertCityEntitytoDTO(city);
    }

    @Override
    public boolean deleteCity(UUID cityId) {
        City city = cityRepo.findById(cityId).orElseThrow( () ->
                new CityNotFoundException("City with " + cityId + " not found")
        );
        cityRepo.delete(city);
        return true;
    }

    @Override
    public City getCityByCityId(UUID cityId) {
        return cityRepo.findById(cityId).orElseThrow(
                () -> new CityNotFoundException("City with " + cityId + " not found")
        );
    }

    @Override
    public void saveCity(City city) {
        cityRepo.save(city);
    }
}
