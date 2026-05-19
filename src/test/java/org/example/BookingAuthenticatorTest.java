package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        seedRoom = Room.builder()
                .type("deluxe")
                .description("juicy")
                .minRoomNumber(1)
                .maxRoomNumber(60)
                .bedType("raggedy")
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
    public void whenRoomNumberIsEqualToRoomNumberOfExistingBooking_thenAvailabilityIsFalse() {
        assertFalse(authenticator.isUnoccupied(invalidBookingBase, bookingRepo));
    }

    @Test
    public void whenRoomTypeUnavailable_thenAvailabilityIsFalse() {

    }

    @Test
    public void whenRoomNumberIsLessThanMinimumRoomNumber_thenInvalidRoomNumber() {
        Booking invalidBooking = invalidBookingBase.toBuilder().roomNumber(0).build();

        assertFalse(authenticator.isValidRoomNumber(invalidBooking));
    }

    @Test
    public void whenRoomNumberIsGreaterThanMaximumRoomNumber_thenInvalidRoomNumber() {
        Booking invalidBooking = invalidBookingBase.toBuilder().roomNumber(61).build();

        assertFalse(authenticator.isValidRoomNumber(invalidBooking));
    }

    @Test
    public void whenRoomNumberIsEqualToMinimumRoomNumber_thenValidRoomNumber() {
        Booking validBooking = invalidBookingBase.toBuilder().roomNumber(1).build();

        assertTrue(authenticator.isValidRoomNumber(validBooking));
    }

    @Test
    public void whenRoomNumberIsEqualToMaximumRoomNumber_thenValidRoomNumber() {
        Booking validBooking = invalidBookingBase.toBuilder().roomNumber(60).build();

        assertTrue(authenticator.isValidRoomNumber(validBooking));
    }

    @Test
    public void givenRoomNumberIsGreaterThanMinimumRoomNumber_whenRoomNumberIsGreaterThanMaximumRoomNumber_thenValidRoomNumber() {
        Booking validBooking = invalidBookingBase.toBuilder().roomNumber(40).build();

        assertTrue(authenticator.isValidRoomNumber(validBooking));
    }

    @Test
    public void whenValidRoomTypeEntered_thenValidRoomType() {
        assertTrue(authenticator.isValidRoomType(seedBooking.room().type(), roomTypeRepo));
    }

    @Test
    public void whenInvalidRoomTypeEntered_thenInvalid() {
        Booking invalidBooking = seedBooking.toBuilder().room(seedRoom.toBuilder().type("delucks").build()).build();

        assertFalse(authenticator.isValidRoomType(invalidBooking.room().type(), roomTypeRepo));
    }
}
