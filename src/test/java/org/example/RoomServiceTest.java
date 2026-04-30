//package org.example;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.example.FakeScannerCustomInput;
//import org.example.InMemoryRoomTypeRepository;
//import org.example.RoomService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class RoomServiceTest {
//    org.example.FakeScannerCustomInput fakeInput = new FakeScannerCustomInput();
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
