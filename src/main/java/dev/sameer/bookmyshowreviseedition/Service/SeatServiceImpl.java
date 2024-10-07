package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Repo.SeatRepo;
import dev.sameer.bookmyshowreviseedition.Exceptions.SeatNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Override
    public Seat getSeat(UUID seatId) {
        return seatRepo.findById(seatId).orElseThrow(
                () -> new SeatNotFoundException("Seat Not found")
        );
    }

    public void deleteSeat(UUID seatId) {
        Seat seat = seatRepo.findById(seatId).orElseThrow(
                () -> new SeatNotFoundException("Seat Not found")
        );
        seatRepo.delete(seat);
    }
}
