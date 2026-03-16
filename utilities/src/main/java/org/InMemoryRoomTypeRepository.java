package org;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomTypeRepository implements RoomTypeRepository{
    
    List<Room> roomTypes = new ArrayList<>();
    public InMemoryRoomTypeRepository() {
        seedData();
    }

    private void seedData() {
        // You use the builder exactly like the User would!
        roomTypes.add(new Room.RoomBuilder()
                .setType("Standard Double")
                .setDescription("Comfortable and budget friendly")
                .setMinRoomNumber(1)
                .setMaxRoomNumber(250)
                .setBedType("Double")
                .setRoomTypeTotal(250)
                .setRate(1000)
                .build());

        roomTypes.add(new Room.RoomBuilder()
                .setType("Deluxe")
                .setDescription("Enhanced comfort and additional space")
                .setMinRoomNumber(251)
                .setMaxRoomNumber(500)
                .setBedType("Queen")
                .setRoomTypeTotal(250)
                .setRate(1200)
                .build());

        roomTypes.add(new Room.RoomBuilder()
                .setType("Superior")
                .setDescription("Luxury and premium comfort")
                .setMinRoomNumber(501)
                .setMaxRoomNumber(530)
                .setBedType("King")
                .setRoomTypeTotal(30)
                .setRate(1800)
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
        
        if (room.getType().equalsIgnoreCase(roomToRemove)) {
            roomTypes.remove(i); 
        }
    }
    System.out.println(roomTypes);
}

    public List<Room> getAll()
    {
        return new ArrayList<>();
    }

}
