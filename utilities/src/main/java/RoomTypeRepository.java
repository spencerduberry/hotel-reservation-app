import java.util.List;

public interface RoomTypeRepository {
    Room addRoom(Room room);
    void removeRoom(int roomId);
    Room getRoom(int roomId);
    List<Room> getAll();
} 
    
