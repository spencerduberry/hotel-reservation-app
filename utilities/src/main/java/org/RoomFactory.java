package org;

public class RoomFactory {
    private final CustomInput
            inputSource;
    private final RoomTypeRepository roomRepo;

    public RoomFactory(CustomInput inputSource, RoomTypeRepository roomRepo) {
        this.inputSource = inputSource;
        this.roomRepo = roomRepo;
    }

    public Room addRoomType() {
        String type = inputSource.inputString();
        String description = inputSource.inputString();
        int minRoomNumber = inputSource.inputInt();
        int maxRoomNumber = inputSource.inputInt();
        String bedType = inputSource.inputString();
        int roomTypeTotal = inputSource.inputInt();
        int rate = inputSource.inputInt();

        Room newRoom = new Room(type, description, minRoomNumber, maxRoomNumber, bedType, roomTypeTotal, rate);
        roomRepo.addRoom(newRoom);
        return newRoom;
    }
}
