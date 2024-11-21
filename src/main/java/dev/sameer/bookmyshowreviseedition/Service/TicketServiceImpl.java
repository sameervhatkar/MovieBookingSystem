package dev.sameer.bookmyshowreviseedition.Service;

import dev.sameer.bookmyshowreviseedition.DTOs.TicketResponseDTO;
import dev.sameer.bookmyshowreviseedition.DTOs.TicketRequestDTO;
import dev.sameer.bookmyshowreviseedition.Entity.Show;
import dev.sameer.bookmyshowreviseedition.Entity.ShowSeat;
import dev.sameer.bookmyshowreviseedition.Entity.Ticket;
import dev.sameer.bookmyshowreviseedition.Enum.TicketStatus;
import dev.sameer.bookmyshowreviseedition.Mapper.EntityToDTOMapper;
import dev.sameer.bookmyshowreviseedition.Repo.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ShowSeatService showSeatService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public TicketResponseDTO bookTicket(TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = new Ticket();
        List<ShowSeat> showSeats = new ArrayList<>();
        for(UUID showSeatId : ticketRequestDTO.getShowSeatIds()) {
            showSeats.add(showSeatService.getShowSeat(showSeatId));
        }
        double ticketPrice = 0.0;
        Show show = null;

        for(ShowSeat showSeat : showSeats) {
            show = showSeat.getShow();
            ticketPrice += showSeat.getPrice();
        }
        ticket.setTicketPrice(ticketPrice);
        ticket.setBookingTime(LocalDateTime.now());
        ticket.setShow(show);
        ticket.setShowSeats(showSeats);
        ticket.setTicketStatus(TicketStatus.IN_PROGRESS);
        ticket.setTicketStatus(TicketStatus.BOOKED);
        ticketRepo.save(ticket);
        return EntityToDTOMapper.convertTicketEntitytoDTO(ticket, movieService.getMovie(show.getMovieId()).getMovieName());
    }


}
