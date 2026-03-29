package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.CreateSeatRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.GenerateSeatsRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.SeatResponseDTO;
import com.sameervhatkar.MovieBookingSystem.dto.UpdateSeatRequestDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Auditorium;
import com.sameervhatkar.MovieBookingSystem.entity.Seat;
import com.sameervhatkar.MovieBookingSystem.exceptions.AuditoriumNotFoundException;
import com.sameervhatkar.MovieBookingSystem.exceptions.SeatNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.AuditoriumRepository;
import com.sameervhatkar.MovieBookingSystem.repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final AuditoriumRepository auditoriumRepository;

    public SeatService(SeatRepository seatRepository, AuditoriumRepository auditoriumRepository) {
        this.seatRepository = seatRepository;
        this.auditoriumRepository = auditoriumRepository;
    }

    //CRUD
    public SeatResponseDTO createSeat(CreateSeatRequestDTO requestDTO) {
        Seat seat = new Seat();
        Auditorium auditorium = auditoriumRepository.findById(requestDTO.getAuditoriumId()).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with this id is not found.")
        );
        seat.setAuditorium(auditorium);
        seat.setRow_label(requestDTO.getRow_label());
        seat.setSeat_number(requestDTO.getSeat_number());
        seat.setSeatType(requestDTO.getSeatType());
        Seat savedSeat = seatRepository.save(seat);

        return EntityToDTOMapper.convertSeatEntityToDTO(savedSeat);
    }

    public void generateSeats(GenerateSeatsRequestDTO requestDTO) {


        Auditorium auditorium = auditoriumRepository.findById(requestDTO.getAuditoriumId()).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with this id is not found.")
        );
        List<Seat> seats = new ArrayList<>();

        for (String row : requestDTO.getRows()) {

            for (int i = 1; i <= requestDTO.getSeatsPerRow(); i++) {

                Seat seat = new Seat();
                seat.setRow_label(row);
                seat.setSeat_number(i);
                seat.setSeatType(requestDTO.getSeatType());
                seat.setAuditorium(auditorium);

                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }

    public SeatResponseDTO getSeatById(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new SeatNotFoundException("Seat with this id is not found.")
        );
        return EntityToDTOMapper.convertSeatEntityToDTO(seat);
    }

    public SeatResponseDTO updateSeat(UpdateSeatRequestDTO requestDTO) {
        Seat seat = seatRepository.findById(requestDTO.getId()).orElseThrow(
                () -> new SeatNotFoundException("Seat with this id is not found.")
        );
        Auditorium auditorium = auditoriumRepository.findById(requestDTO.getAuditoriumId()).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with this id is not found.")
        );
        seat.setAuditorium(auditorium);
        seat.setRow_label(requestDTO.getRow_label());
        seat.setSeat_number(requestDTO.getSeat_number());
        seat.setSeatType(requestDTO.getSeatType());
        Seat savedSeat = seatRepository.save(seat);

        return EntityToDTOMapper.convertSeatEntityToDTO(savedSeat);
    }

    public void deleteSeat(Long id) {
        Seat seat = seatRepository.findById(id).orElseThrow(
                () -> new SeatNotFoundException("Seat with this id is not found.")
        );
        seatRepository.delete(seat);
    }
}
