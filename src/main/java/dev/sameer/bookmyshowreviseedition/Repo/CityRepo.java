package dev.sameer.bookmyshowreviseedition.Repo;

import dev.sameer.bookmyshowreviseedition.Entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepo extends JpaRepository<City, UUID> {
    Optional<City> findCityByCityName(String cityName);
    Optional<City> findById(UUID cityId);

}
