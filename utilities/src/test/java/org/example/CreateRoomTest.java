//package utilities.src.test.java.org.example;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.FakeScannerCustomInput;
//import org.InMemoryRoomTypeRespository;
//import org.Room;
//import org.RoomService;
//import org.junit.jupiter.api.Test;
//
//public class CreateRoomTest {
//    org.FakeScannerCustomInput fakeInput = new FakeScannerCustomInput();
//    InMemoryRoomTypeRepository fakeRepo = new InMemoryRoomTypeRespository();
//    RoomService service = new RoomService(fakeInput, fakeRepo);
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
