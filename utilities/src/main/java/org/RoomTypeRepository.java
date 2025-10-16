package org;
import java.util.List;
import java.util.Scanner;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(Scanner sc);
    Room getRoom(Scanner sc);
    List<Room> getAll();
} 
    
