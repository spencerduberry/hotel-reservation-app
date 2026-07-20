package io.github.spencerduberry.hotelreservation.room;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.spencerduberry.hotelreservation.bed.Bed;
import io.github.spencerduberry.hotelreservation.utils.CustomInput;

public class InMemoryRoomTypeRepository implements RoomTypeRepository {

    List<Room> roomTypes = new ArrayList<>();

    public InMemoryRoomTypeRepository() {
        seedData();
    }

    private void seedData() {
        Bed doubleBed = new Bed("double");
        Bed queenBed = new Bed("queen");
        Bed kingBed = new Bed("king");

        roomTypes.add(Room.builder()
                .type("Standard Double")
                .description("Comfortable and budget friendly")
                .minRoomNumber(1)
                .maxRoomNumber(250)
                .bedType(doubleBed)
                .roomTypeTotal(250)
                .rate(1000)
                .build());

        roomTypes.add(Room.builder()
                .type("Deluxe")
                .description("Enhanced comfort and additional space")
                .minRoomNumber(251)
                .maxRoomNumber(500)
                .bedType(queenBed)
                .roomTypeTotal(250)
                .rate(1200)
                .build());

        roomTypes.add(Room.builder()
                .type("Superior")
                .description("Luxury and premium comfort")
                .minRoomNumber(501)
                .maxRoomNumber(530)
                .bedType(kingBed)
                .roomTypeTotal(30)
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
