package io.github.spencerduberry.hotelreservation.booking;

import java.util.List;

public interface BookingRepository {
    void addBooking(Booking booking);
    void removeBooking(Booking booking);
    List<Booking> getAll();
}
