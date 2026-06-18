package io.github.spencerduberry.hotelreservation.booking;

import static io.github.spencerduberry.hotelreservation.booking.BookingService.*;
import static io.github.spencerduberry.hotelreservation.booking.BookingService.RoomNumberResult.ErrorType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.spencerduberry.hotelreservation.room.Bed;
import io.github.spencerduberry.hotelreservation.room.InMemoryRoomTypeRepository;
import io.github.spencerduberry.hotelreservation.room.Room;
import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository;

public class BookingAuthenticatorTest {
    BookingRepository bookingRepo;
    RoomTypeRepository roomTypeRepo;
    Room seedRoom;
    Booking seedBooking;
    BookingAuthenticator authenticator = new BookingAuthenticator();
    Booking invalidBookingBase;

    @BeforeEach
    void setup() {
        bookingRepo = new InMemoryBookingRepository();

        roomTypeRepo = new InMemoryRoomTypeRepository();

        Bed testBed = new Bed("raggedy");

        seedRoom = Room.builder()
                .type("deluxe")
                .description("juicy")
                .minRoomNumber(1)
                .maxRoomNumber(60)
                .bedType(testBed)
                .roomTypeTotal(50)
                .rate(60)
                .build();

        roomTypeRepo.addRoom(seedRoom);

        seedBooking = Booking.builder()
                .firstName("Terry")
                .lastName("Rampling")
                .lengthOfStay(10)
                .roomNumber(55)
                .room(seedRoom)
                .build();

        bookingRepo.addBooking(seedBooking);

        invalidBookingBase = Booking.builder()
                .firstName("Ed")
                .lastName("Balls")
                .lengthOfStay(10)
                .roomNumber(55)
                .room(seedRoom)
                .build();
    }


    @Test
    public void whenRoomNumberIsEqualToRoomNumberOfExistingBooking_thenDoubleBookingError() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(55, seedRoom, bookingRepo);

        assertEquals(DOUBLEBOOKING, result.error());
        assertNull(result.value());
    }

    @Test
    public void whenRoomTypeAtFullCapacity_thenAvailabilityIsFalse() {

    }

    @Test
    public void whenRoomNumberIsLessThanMinimumRoomNumber_thenOutOfRangeError() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(0, seedRoom, bookingRepo);

        assertEquals(OUTOFRANGE, result.error());
        assertNull(result.value());
    }

    @Test
    public void whenRoomNumberIsGreaterThanMaximumRoomNumber_thenInvalidRoomNumber() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(61, seedRoom, bookingRepo);

        assertEquals(OUTOFRANGE, result.error());
        assertNull(result.value());
    }

    @Test
    public void whenRoomNumberIsEqualToMinimumRoomNumber_thenValidRoomNumber() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(1, seedRoom, bookingRepo);

        assertTrue(result.isSuccess());
        assertNotNull(result.value());
        assertNull(result.error());
    }

    @Test
    public void whenRoomNumberIsEqualToMaximumRoomNumber_thenValidRoomNumber() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(60, seedRoom, bookingRepo);

        assertTrue(result.isSuccess());
        assertNotNull(result.value());
        assertNull(result.error());
    }

    @Test
    public void givenRoomNumberIsGreaterThanMinimumRoomNumber_whenRoomNumberIsGreaterThanMaximumRoomNumber_thenValidRoomNumber() {
        RoomNumberResult result = authenticator.roomNumberValidityChecker(45, seedRoom, bookingRepo);

        assertTrue(result.isSuccess());
        assertNotNull(result.value());
    }

    @Test
    public void whenValidRoomTypeEntered_thenReturnValidRoom() {
        assertTrue(authenticator.isValidRoom("Deluxe", roomTypeRepo));
    }

    @Test
    public void whenInvalidRoomTypeEntered_thenInvalid() {
        assertFalse(authenticator.isValidRoom("Delucks", roomTypeRepo));
    }
}
