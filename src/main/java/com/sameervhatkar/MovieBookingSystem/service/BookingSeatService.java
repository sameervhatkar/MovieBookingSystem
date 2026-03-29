package com.sameervhatkar.MovieBookingSystem.service;

import com.sameervhatkar.MovieBookingSystem.entity.Booking;
import com.sameervhatkar.MovieBookingSystem.entity.BookingSeat;
import com.sameervhatkar.MovieBookingSystem.entity.ShowSeat;
import com.sameervhatkar.MovieBookingSystem.repository.BookingSeatRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingSeatService {

    private final BookingSeatRepository bookingSeatRepository;

    public BookingSeatService(BookingSeatRepository bookingSeatRepository) {
        this.bookingSeatRepository = bookingSeatRepository;
    }

    public BookingSeat createBookingSeat(Booking booking, ShowSeat showSeat, Double priceAtBooking) {
        BookingSeat bookingSeat = new BookingSeat();
        bookingSeat.setBooking(booking);
        bookingSeat.setShowSeat(showSeat);
        bookingSeat.setPriceAtBooking(priceAtBooking);

        return bookingSeatRepository.save(bookingSeat);
    }
}
