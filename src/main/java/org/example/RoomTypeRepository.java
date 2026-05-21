package org.example;
import java.util.List;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(CustomInput input);
    List<Room> getAll();

} 
    
