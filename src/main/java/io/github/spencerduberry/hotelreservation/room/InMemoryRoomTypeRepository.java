package io.github.spencerduberry.hotelreservation.room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRoomTypeRepository implements RoomTypeRepository {

    List<Room> roomTypes = new ArrayList<>();

    public InMemoryRoomTypeRepository() {
        seedData();
    }

    private void seedData() {

        roomTypes.add(Room.builder()
                .name("Standard Double")
                .bedType("doubleBed")
                .rate(1000)
                .build());

        roomTypes.add(Room.builder()
                .name("Deluxe")
                .bedType("queenBed")
                .rate(1200)
                .build());

        roomTypes.add(Room.builder()
                .name("Superior")
                .bedType("kingBed")
                .rate(1800)
                .build());
    }

    public void addRoom(Room newRoom) {
        roomTypes.add(newRoom);
    }

    public void removeRoom(Room room) {
        roomTypes.remove(room);
    }

    public Map<String, Room> getAll() {
        Map<String, Room> roomMap = new HashMap<>();

        for (int i=0; i<roomTypes.size(); i++){
            roomMap.put(String.valueOf(i+1), roomTypes.get(i));
        }

        return roomMap;
    }
}
