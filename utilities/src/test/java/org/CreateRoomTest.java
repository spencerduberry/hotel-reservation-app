package org;

public class CreateRoomTest {
    FakeScannerCustomInput fakeInput = new FakeScannerCustomInput();
    InMemoryRoomTypeRespository fakeRepo = new InMemoryRoomTypeRespository();
    RoomFactory factory = new RoomFactory(fakeInput, fakeRepo);

    fakeInput.enqueueInput("string");
    fakeInput.enqueueInput("string");
    fakeInput.enqueueInput("42");
    fakeInput.enqueueInput("42");
    fakeInput.enqueueInput("string");
    fakeInput.enqueueInput("42");
    fakeInput.enqueueInput("string");

    Room fakeRoom = 
}
