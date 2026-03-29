package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.Mapper.EntityToDTOMapper;
import com.sameervhatkar.MovieBookingSystem.dto.CreatePaymentRequestDTO;
import com.sameervhatkar.MovieBookingSystem.dto.PaymentResponseDTO;
import com.sameervhatkar.MovieBookingSystem.entity.Booking;
import com.sameervhatkar.MovieBookingSystem.entity.BookingSeat;
import com.sameervhatkar.MovieBookingSystem.entity.Payment;
import com.sameervhatkar.MovieBookingSystem.entity.ShowSeat;
import com.sameervhatkar.MovieBookingSystem.enums.BookingStatus;
import com.sameervhatkar.MovieBookingSystem.enums.PaymentStatus;
import com.sameervhatkar.MovieBookingSystem.enums.ShowSeatStatus;
import com.sameervhatkar.MovieBookingSystem.exceptions.BookingNotFoundException;
import com.sameervhatkar.MovieBookingSystem.repository.BookingRepository;
import com.sameervhatkar.MovieBookingSystem.repository.BookingSeatRepository;
import com.sameervhatkar.MovieBookingSystem.repository.PaymentRepository;
import com.sameervhatkar.MovieBookingSystem.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;
    private final BookingSeatRepository bookingSeatRepository;
    private final ShowSeatRepository showSeatRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository, BookingSeatRepository bookingSeatRepository, ShowSeatRepository showSeatRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
        this.bookingSeatRepository = bookingSeatRepository;
        this.showSeatRepository = showSeatRepository;
    }

    public PaymentResponseDTO createPayment(CreatePaymentRequestDTO requestDTO) {
        Booking booking = bookingRepository.findById(requestDTO.getBookingId()).orElseThrow(
                () -> new BookingNotFoundException("Booking not found.")
        );

        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(booking.getTotalAmount());
        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setPaymentGatewayReferenceId(UUID.randomUUID().toString());

        Payment savedPayment = paymentRepository.save(payment);

        booking.setStatus(BookingStatus.CONFIRMED);
        bookingRepository.save(booking);

        List<BookingSeat> bookingSeatList = bookingSeatRepository.findByBookingId(booking.getId());

        for(BookingSeat bookingSeat : bookingSeatList) {
            ShowSeat showSeat = bookingSeat.getShowSeat();
            showSeat.setStatus(ShowSeatStatus.BOOKED);
            showSeatRepository.save(showSeat);
        }

        return EntityToDTOMapper.convertPaymentEntityToDTO(savedPayment);

    }
}
