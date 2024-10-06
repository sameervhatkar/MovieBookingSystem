package dev.sameer.bookmyshowreviseedition.Controller;

import dev.sameer.bookmyshowreviseedition.DTOs.CityResponseDTO;
import dev.sameer.bookmyshowreviseedition.Service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/addCity/{cityName}")
    public ResponseEntity<Boolean> createCity(@PathVariable String cityName) {
        return ResponseEntity.ok(cityService.createCity(cityName));
    }

    @GetMapping("/getCity/{cityId}")
    public ResponseEntity<CityResponseDTO> getCityById(@PathVariable UUID cityId) {
        return ResponseEntity.ok(cityService.getCity(cityId));
    }

    @GetMapping("/getAllCities")
    public ResponseEntity<List<CityResponseDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @DeleteMapping("/deleteCity/{cityId}")
    public ResponseEntity<Boolean> deleteCityById(@PathVariable UUID cityId) {
        return ResponseEntity.ok(cityService.deleteCity(cityId));
    }

}
