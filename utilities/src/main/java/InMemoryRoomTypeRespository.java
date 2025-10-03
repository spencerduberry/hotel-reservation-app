package utilities.src.main.java;

import java.util.List;

public class InMemoryRoomTypeRespository implements RoomTypeRepository{
    
    List<Room> roomTypes;

    public Room addRoom(Room room)
    {
        roomTypes.add(room);

        return room;
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
