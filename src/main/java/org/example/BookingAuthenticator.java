package org.example;

import com.google.auto.value.AutoValue;

public class BookingAuthenticator {

    public boolean isUnoccupied(Booking booking, BookingRepository bookingRepo) {
        int roomNumber = booking.roomNumber();

        for (Booking b : bookingRepo.getAll()) {
            if (roomNumber == b.roomNumber()) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidRoomNumber(Booking booking) {
        return ((booking.roomNumber() >= booking.room().minRoomNumber()) &&
                booking.roomNumber() <= booking.room().maxRoomNumber());
    }

    public boolean isValidRoomType(String roomName, RoomTypeRepository roomRepo){
        for (Room room : roomRepo.getAll()) {

            if (roomName.equals(room.type())) {
                return true;
            }
        }
        return false;
    }
}
