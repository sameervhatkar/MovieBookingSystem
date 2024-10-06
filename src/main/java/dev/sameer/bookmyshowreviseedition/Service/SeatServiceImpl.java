package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Repo.SeatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatServiceImpl implements SeatService {

    @Autowired
    private SeatRepo seatRepo;

    @Override
    public Seat createSeat() {
        return new Seat();
    }

    @Override
    public void saveSeat(Seat seat) {
        seatRepo.save(seat);
    }
}
