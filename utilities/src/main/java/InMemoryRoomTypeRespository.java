import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InMemoryRoomTypeRespository implements RoomTypeRepository{
    
    List<Room> roomTypes = new ArrayList<Room>();

    public void addRoom(Room newRoom)
	{
		roomTypes.add(newRoom);
	}
    
    public void removeRoom(int roomId)
    {

    }

    public Room getRoom(int roomId)
    {

    }

    public List<Room> getAll()
    {

    }

}
