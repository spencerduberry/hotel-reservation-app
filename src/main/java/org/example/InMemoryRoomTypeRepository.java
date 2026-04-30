package org.example;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomTypeRepository implements RoomTypeRepository{
    
    List<Room> roomTypes = new ArrayList<>();
    public InMemoryRoomTypeRepository() {
        seedData();
    }

    private void seedData() {
        roomTypes.add(Room.builder()
                .type("Standard Double")
                .description("Comfortable and budget friendly")
                .minRoomNumber(1)
                .maxRoomNumber(250)
                .bedType("Double")
                .roomTypeTotal(250)
                .rate(1000)
                .build());

        roomTypes.add(Room.builder()
                .type("Deluxe")
                .description("Enhanced comfort and additional space")
                .minRoomNumber(251)
                .maxRoomNumber(500)
                .bedType("Queen")
                .roomTypeTotal(250)
                .rate(1200)
                .build());

        roomTypes.add(Room.builder()
                .type("Superior")
                .description("Luxury and premium comfort")
                .minRoomNumber(501)
                .maxRoomNumber(530)
                .bedType("King")
                .roomTypeTotal(30)
                .rate(1800)
                .build());
    }

    public void addRoom(Room newRoom)
	{
		roomTypes.add(newRoom);
	}
    
    public void removeRoom(CustomInput input)
    {
    System.out.println(roomTypes);
    System.out.println("Please enter room to remove:");
    String roomToRemove = input.inputString();

    for (int i = roomTypes.size() - 1; i >= 0; i--) {
        Room room = roomTypes.get(i);
        
        if (room.type().equalsIgnoreCase(roomToRemove)) {
            roomTypes.remove(i); 
        }
    }
    System.out.println(roomTypes);
}

    public List<Room> getAll()
    {
        return roomTypes;
    }

}
