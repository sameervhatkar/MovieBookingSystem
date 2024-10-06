package dev.sameer.bookmyshowreviseedition.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "bms_show")
public class Show extends BaseModel {
    private UUID theaterId;
    private UUID audiId;
    private UUID movieId;
    private LocalDateTime showTimings;
    @OneToMany
    private List<ShowSeat> showSeats;
}
