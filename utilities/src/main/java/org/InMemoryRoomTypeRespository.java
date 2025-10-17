package org;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRoomTypeRespository implements RoomTypeRepository{
    
    List<Room> roomTypes = new ArrayList<Room>();

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

    public Room getRoom(CustomInput input)
    {
        Room goFuckYourself = new Room(null, null, 6, 6, null, 6, 6);

        return goFuckYourself;
    }

    public List<Room> getAll()
    {
        List<Room> placeholderList = new ArrayList<Room>();

        return placeholderList;
    }

}
