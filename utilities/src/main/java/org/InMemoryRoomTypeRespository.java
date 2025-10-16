package org;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InMemoryRoomTypeRespository implements RoomTypeRepository{
    
    List<Room> roomTypes = new ArrayList<Room>();

    public void addRoom(Room newRoom)
	{
		roomTypes.add(newRoom);
	}
    
    public void removeRoom(Scanner sc)
    {
    System.out.println(roomTypes);
    System.out.println("Please enter room to remove:");
    String roomToRemove = sc.nextLine();

    for (int i = roomTypes.size() - 1; i >= 0; i--) {
        Room room = roomTypes.get(i);
        
        if (room.getType().equalsIgnoreCase(roomToRemove)) {
            roomTypes.remove(i); 
        }
    }
    System.out.println(roomTypes);
}

    public Room getRoom(Scanner sc)
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
