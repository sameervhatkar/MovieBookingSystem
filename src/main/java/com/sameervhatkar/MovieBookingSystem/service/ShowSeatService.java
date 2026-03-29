package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.SeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.ShowSeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Seat;
import com.sameervhatkar.MovieBookingSystem.entity.Show;
import com.sameervhatkar.MovieBookingSystem.entity.ShowSeat;
import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import com.sameervhatkar.MovieBookingSystem.exceptions.ShowSeatNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.ShowSeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShowSeatService {

    private final ShowSeatRepository showSeatRepository;

    public ShowSeatService(ShowSeatRepository showSeatRepository) {
        this.showSeatRepository = showSeatRepository;
    }

    public ShowSeat generateShowSeat(Show show, Seat seat) {

        ShowSeat showSeat = new ShowSeat();
        showSeat.setShow(show);
        showSeat.setSeat(seat);
        showSeat.setStatus(ShowSeatStatus.AVAILABLE);
        showSeat.setLockedBy(null);
        showSeat.setLockExpiryTime(null);

        return showSeatRepository.save(showSeat);
    }

    public ShowSeatResponseDTO getShowSeatById(Long showId) {

        ShowSeat showSeat = showSeatRepository.findById(showId)
                .orElseThrow(() -> new ShowSeatNotFoundException("ShowSeat not found"));

        return EntityToDTOMapper.convertShowSeatEntityToDTO(showSeat);
    }

    public List<ShowSeat> generateShowSeatsForShow(Show show, List<Seat> seats) {

        List<ShowSeat> showSeats = seats.stream().map(seat -> {
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            showSeat.setLockedBy(null);
            showSeat.setLockExpiryTime(null);
            return showSeat;
        }).toList();

        return showSeatRepository.saveAll(showSeats);
    }

    public List<SeatResponseDTO> getSeatByShow(Long showId) {
        List<ShowSeat> showSeats = showSeatRepository.findByShowId(showId);

        return showSeats.stream()
                .map(showSeat -> EntityToDTOMapper.convertSeatEntityToDTO(showSeat.getSeat()))
                .toList();

    }

    @Scheduled(fixedRate = 60000)
    public void releaseExpiryLocks() {
        List<ShowSeat> expiredLock = showSeatRepository.findByStatusAndLockExpiryTimeBefore(ShowSeatStatus.LOCKED, LocalDateTime.now());

        for(ShowSeat showSeat : expiredLock) {
            showSeat.setStatus(ShowSeatStatus.AVAILABLE);
            showSeat.setLockedBy(null);
            showSeat.setLockExpiryTime(null);
        }

        showSeatRepository.saveAll(expiredLock);
    }


}