package org;
import java.util.List;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(CustomInput input);
    Room getRoom(CustomInput input);
    List<Room> getAll();
} 
    
