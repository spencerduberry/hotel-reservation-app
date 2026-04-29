package org;

import java.util.List;

public interface BookingRepository {
    void addBooking(Booking booking);
    void removeBooking(Booking booking);
    List<Booking> getAll();
}
