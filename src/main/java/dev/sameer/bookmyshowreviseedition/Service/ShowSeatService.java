package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Entity.Show;
import dev.sameer.bookmyshowreviseedition.Entity.ShowSeat;
import dev.sameer.bookmyshowreviseedition.Exceptions.ShowSeatNotFoundException;
import dev.sameer.bookmyshowreviseedition.Repo.ShowSeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class ShowSeatService {
    @Autowired
    private ShowSeatRepo showSeatRepo;

    public ShowSeat generateShowSeat(Show show, Seat seat) {
        ShowSeat showSeat = new ShowSeat();
        LocalTime showTiming = show.getShowTimings().toLocalTime();

        if(showTiming.isAfter(LocalTime.of(8, 0)) && showTiming.isBefore(LocalTime.of(12, 0)))
            showSeat.setPrice(150);
        else if(showTiming.isAfter(LocalTime.of(12, 0)) && showTiming.isBefore(LocalTime.of(16, 0)))
            showSeat.setPrice(200);
        else if(showTiming.isAfter(LocalTime.of(16, 0)) && showTiming.isBefore(LocalTime.of(20, 0)))
            showSeat.setPrice(220);
        else if(showTiming.isAfter(LocalTime.of(20, 0)) && showTiming.isBefore(LocalTime.MIDNIGHT))
            showSeat.setPrice(220);

        showSeat.setShow(show);
        showSeat.setSeat(seat);
        showSeatRepo.save(showSeat);
        return showSeat;
    }

    public ShowSeat getShowSeat(UUID showSeatId) {
        return showSeatRepo.findById(showSeatId).orElseThrow(
                () -> new ShowSeatNotFoundException("Show seat not found")
        );
    }
}
