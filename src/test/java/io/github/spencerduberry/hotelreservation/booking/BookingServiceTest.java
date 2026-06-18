package io.github.spencerduberry.hotelreservation.booking;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository;

public class BookingServiceTest {
    @Mock
    BookingRepository bookingRepo;
    @Mock
    RoomTypeRepository roomRepo;
    @Mock
    BookingAuthenticator authenticator;

    @Test
    public void whenValidDataEntered_thenAddBookingToRepository() {

    }

    @Test
    public void whenInvalidDataEntered_thenRepositoryEmpty() {

    }
}
