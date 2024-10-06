package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumRequestDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.AuditoriumResponseDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Auditorium;
import dev.sameer.bookmyshowreviseedition.Entity.Seat;
import dev.sameer.bookmyshowreviseedition.Entity.Theatre;
import dev.sameer.bookmyshowreviseedition.Enum.SeatStatus;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.AuditoriumRepo;
import dev.sameer.bookmyshowreviseedition.Repo.TheatreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        auditorium.setAudiName(auditoriumRequestDTO.getAudiName());
        auditorium.setCapacity(auditoriumRequestDTO.getCapacity());
        //Here the seat forming strategy will be simple, lets say we want to make 50 seats
        // then 10 seats per row, therefore we will have 5 rows
        // max we will have 100 seats and min 10 seats
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
        return EntityToDTOMapper.convertAuditoriumEntitytoDTO(auditorium, theatre.getTheatreName());
    }
}
