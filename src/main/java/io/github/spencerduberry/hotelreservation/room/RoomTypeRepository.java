package io.github.spencerduberry.hotelreservation.room;
import java.util.List;

import io.github.spencerduberry.hotelreservation.utils.CustomInput;

public interface RoomTypeRepository {
    void addRoom(Room room);
    void removeRoom(CustomInput input);
    List<Room> getAll();

} 
    
