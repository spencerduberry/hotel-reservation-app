package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
