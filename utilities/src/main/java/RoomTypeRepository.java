import java.util.List;
import java.util.Scanner;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(Scanner sc);
    Room getRoom(int roomId);
    List<Room> getAll();
} 
    
