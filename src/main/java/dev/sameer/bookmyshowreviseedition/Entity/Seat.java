package dev.sameer.bookmyshowreviseedition.Entity;

import dev.sameer.bookmyshowreviseedition.Enum.SeatStatus;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat extends BaseModel {
    private String seatName;
    @Enumerated(EnumType.STRING)
    private SeatStatus seatStatus;
}
