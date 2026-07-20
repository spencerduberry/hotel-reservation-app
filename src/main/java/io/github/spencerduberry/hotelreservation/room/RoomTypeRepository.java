package io.github.spencerduberry.hotelreservation.room;
import java.util.List;
import java.util.Map;

import io.github.spencerduberry.hotelreservation.utils.CustomInput;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(Room room);
    Map<String, Room> getAll();

} 
    
