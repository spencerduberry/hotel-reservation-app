//package io.github.spencerduberry.hotelreservation.room;
//
//import static org.junit.Assert.assertEquals;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import io.github.spencerduberry.hotelreservation.room.InMemoryRoomTypeRepository;
//import io.github.spencerduberry.hotelreservation.room.RoomService;
//import io.github.spencerduberry.hotelreservation.utils.FakeScannerCustomInput;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RoomServiceTest {
//    FakeScannerCustomInput fakeInput = new FakeScannerCustomInput();
//    @Mock
//    InMemoryRoomTypeRepository mockRepo = new InMemoryRoomTypeRepository();
//    RoomService service = new RoomService(fakeInput, mockRepo);
//
//
//    @Test
//    public void whenNegativeIntegerEnteredAsMinRoomNumber_then() {
//        fakeInput.enqueueInput("string");
//        fakeInput.enqueueInput("string");
//        fakeInput.enqueueInput("42");
//        fakeInput.enqueueInput("42");
//        fakeInput.enqueueInput("string");
//        fakeInput.enqueueInput("42");
//        fakeInput.enqueueInput("42");
//
//        service.addRoomType();
//        assertEquals("string", fakeRoom.getType(),
//        "The Room name should match the first enqueued input");
//    }
//}
