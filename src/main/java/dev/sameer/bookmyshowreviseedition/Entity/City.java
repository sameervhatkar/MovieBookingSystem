package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class City extends BaseModel {
    private String cityName;
    @OneToMany
    private List<Theatre> theatreList;

    public City(String cityName) {
        this.cityName = cityName;
    }

    public City() {

    }
}
