package io.github.spencerduberry.hotelreservation.booking;

import static io.github.spencerduberry.hotelreservation.booking.BookingService.*;
import static io.github.spencerduberry.hotelreservation.booking.BookingService.RoomNumberResult.*;
import static io.github.spencerduberry.hotelreservation.booking.BookingService.RoomNumberResult.ErrorType.*;

import io.github.spencerduberry.hotelreservation.room.Room;
import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository;

public class BookingAuthenticator {
    public boolean isValidRoom(String roomName, RoomTypeRepository roomRepo) {

        for (Room room : roomRepo.getAll()) {
            if (room.type().equalsIgnoreCase(roomName)) {
                return true;
            }
        }
        System.out.println("Please enter a valid room type");

        return false;
    }

    public RoomNumberResult roomNumberValidityChecker(int roomNumber, Room roomType, BookingRepository bookingRepo) {

        if (roomNumber < roomType.minRoomNumber() || roomNumber > roomType.maxRoomNumber()) {
            System.out.printf("Please choose a room number between %d and %d.%n",
                    roomType.minRoomNumber(), roomType.maxRoomNumber());
            return error(OUTOFRANGE);
        }

        for (Booking booking : bookingRepo.getAll()) {
            if (booking.roomNumber() == roomNumber) {
                System.out.println("This room is already booked, please choose another.");
                return error(DOUBLEBOOKING);
            }
        }

        return RoomNumberResult.ok(roomNumber);
    }
}
