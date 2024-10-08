package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumUpdateRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;
import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Entity.Theatre;
import dev.sameer.bookmyshowreviseedition.Enum.SeatStatus;
import dev.sameer.bookmyshowreviseedition.Exceptions.AuditoriumNotFoundException;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.AuditoriumRepo;
import dev.sameer.bookmyshowreviseedition.Repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuditoriumServiceImpl implements AuditoriumService{

    @Autowired
    private AuditoriumRepo auditoriumRepo;

    @Autowired
    private TheatreService theatreService;

    @Autowired
    private SeatService seatService;

    @Autowired
    private TheatreRepo theatreRepo;

    @Override
    public AuditoriumResponseDTO createAuditorium(AuditoriumRequestDTO auditoriumRequestDTO) {
        Theatre theatre = theatreService.getTheatreById(auditoriumRequestDTO.getTheatreId());
        Auditorium auditorium = new Auditorium();
        auditorium.setTheatreName(theatre.getTheatreName());
        auditorium.setAudiName(auditoriumRequestDTO.getAudiName());
        auditorium.setCapacity(auditoriumRequestDTO.getCapacity());
        //Here the seat forming strategy will be simple, lets say we want to make 50 seats
        // then 10 seats per row, therefore we will have 5 rows
        List<Seat> seatList = new ArrayList<>();
        for(int i = 1; i <= auditoriumRequestDTO.getCapacity()/10; i++) {
            for(int j = 1; j <= 10; j++) {
                Seat seat = seatService.createSeat();
                seat.setSeatName((char)('A' + (i-1)) + String.valueOf(j));
                seat.setSeatStatus(SeatStatus.AVAILABLE);
                seatService.saveSeat(seat);
                seatList.add(seat);
            }
        }
        auditorium.setSeatList(seatList);
        auditoriumRepo.save(auditorium);
        List<Auditorium> auditoriumList = theatre.getAuditoriumList();
        auditoriumList.add(auditorium);
        theatre.setAuditoriumList(auditoriumList);
        theatreRepo.save(theatre);
        return EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium);
    }

    @Override
    public AuditoriumResponseDTO getAudi(UUID audiId) {
        Auditorium auditorium = auditoriumRepo.findById(audiId).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with audi Id " + audiId + " not found.")
        );

        return EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium);
    }

    @Override
    public List<AuditoriumResponseDTO> getAudisofTheatre(UUID theatreId) {
        Theatre theatre = theatreService.getTheatreById(theatreId);
        List<Auditorium> auditoriums = theatre.getAuditoriumList();
        List<AuditoriumResponseDTO> auditoriumResponseDTOS = new ArrayList<>();
        for(Auditorium auditorium : auditoriums) {
            auditoriumResponseDTOS.add(EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium));
        }
        return auditoriumResponseDTOS;
    }

    @Override
    public List<AuditoriumResponseDTO> getAllAudis() {
        List<Auditorium> auditoriums = auditoriumRepo.findAll();
        List<AuditoriumResponseDTO> auditoriumResponseDTOS = new ArrayList<>();
        for(Auditorium auditorium : auditoriums) {
            auditoriumResponseDTOS.add(EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium));
        }
        return auditoriumResponseDTOS;
    }

    @Override
    public AuditoriumResponseDTO updateAudi(AuditoriumUpdateRequestDTO auditoriumUpdateRequestDTO) {
        Auditorium auditorium = auditoriumRepo.findById(auditoriumUpdateRequestDTO.getAudiId()).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with audi Id " + auditoriumUpdateRequestDTO.getAudiId() + " not found.")
        );
        int newCapacity = auditoriumUpdateRequestDTO.getCapacity();
        auditorium.setAudiName(auditoriumUpdateRequestDTO.getAudiName());
        if(auditorium.getCapacity() == newCapacity)
            return EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium);

        else if (auditorium.getCapacity() < newCapacity) {
            int diffCap = newCapacity - auditorium.getCapacity();

            // Get the current number of rows (starting row for the new seats)
            int currentRows = auditorium.getCapacity() / 10; // Each row has 10 seats

            List<Seat> seatList = auditorium.getSeatList();  // Existing seat list

            // Add new seats starting from the next row
            for (int i = 1; i <= diffCap / 10; i++) {
                char rowChar = (char) ('A' + currentRows);  // Get the next row character (starting from F if currentRows = 5)

                for (int j = 1; j <= 10; j++) {
                    Seat seat = seatService.createSeat();
                    seat.setSeatName(rowChar + String.valueOf(j));  // Seat name will be like F1, F2, ..., F10, then G1, ...
                    seat.setSeatStatus(SeatStatus.AVAILABLE);
                    seatService.saveSeat(seat);
                    seatList.add(seat);
                }

                currentRows++;  // Move to the next row for the next 10 seats
            }

            auditorium.setSeatList(seatList);  // Update the seat list in the auditorium
        }
        else {          //Here we have to delete the seats to this audi
            List<Seat> seatsToBeRem = new ArrayList<>();
            for(UUID seatId : auditoriumUpdateRequestDTO.getSeatIds()) {
                seatsToBeRem.add(seatService.getSeat(seatId));
            }
            List<Seat> seatsWeHave = auditorium.getSeatList();
            for(Seat seat : seatsToBeRem) {
                if(seatsWeHave.contains(seat)) {
                    seatsWeHave.remove(seat);
                }
            }
            auditorium.setSeatList(seatsWeHave);
            auditoriumRepo.save(auditorium);
            for(UUID seatId : auditoriumUpdateRequestDTO.getSeatIds()) {
                seatService.deleteSeat(seatId);
            }
        }

        return EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium);
    }

    @Override
    public Boolean deleteAudi(UUID audiId) {
        Auditorium auditorium = auditoriumRepo.findById(audiId).orElseThrow(
                () -> new AuditoriumNotFoundException("Auditorium with audi Id " + audiId + " not found.")
        );
        auditoriumRepo.delete(auditorium);
        return true;
    }


}
