package org;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CreateRoomTest {
    FakeScannerCustomInput fakeInput = new FakeScannerCustomInput();
    InMemoryRoomTypeRespository fakeRepo = new InMemoryRoomTypeRespository();
    RoomFactory factory = new RoomFactory(fakeInput, fakeRepo);
  

    @Test
    public void testRoomCreationWithFakeInput() {
        fakeInput.enqueueInput("string");
        fakeInput.enqueueInput("string");
        fakeInput.enqueueInput("42");
        fakeInput.enqueueInput("42");
        fakeInput.enqueueInput("string");
        fakeInput.enqueueInput("42");
        fakeInput.enqueueInput("42");

        Room fakeRoom = factory.addRoomType();
        assertEquals("string", fakeRoom.getType(), 
        "The Room name should match the first enqueued input");
    }
}
