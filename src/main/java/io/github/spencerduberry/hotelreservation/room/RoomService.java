package io.github.spencerduberry.hotelreservation.room;


import static io.github.spencerduberry.hotelreservation.utils.Utils.*;

import io.github.spencerduberry.hotelreservation.utils.CustomInput;

public class RoomService {
    private final CustomInput inputSource;
    private final RoomTypeRepository roomRepo;

    public RoomService(CustomInput inputSource, RoomTypeRepository roomRepo) {
        this.inputSource = inputSource;
        this.roomRepo = roomRepo;
    }

    public void addRoomType() {
        int minRoomNumber;
        int maxRoomNumber;

        System.out.println("Please enter the name of the room type.");
        String type = inputSource.inputString();

        System.out.println("Please enter a description of the room type.");
        String description = inputSource.inputString();

        minRoomNumber = getValidInt(inputSource, "Please enter the minimum room number.",
                "The number must be a positive integer. Please try again.",
                val -> val > 0);

        final int minRoomNumberCopy = minRoomNumber;

        maxRoomNumber = getValidInt(inputSource, "Please enter the maximum room number.",
                "The maximum room number must be larger than the minimum room number. Please try again",
                val -> val > minRoomNumberCopy);

        System.out.println("Please enter the bed type.");
        String bedType = inputSource.inputString();

        int roomTypeTotal = getValidInt(inputSource, "How many rooms of this type are available?",
                "The number must be zero or a positive integer. Please try again.",
                val -> val >= 0);

        int rate = getValidInt(inputSource, "Please enter the nightly rate.",
                "The number must be a positive integer. Please try again.",
                val -> val >= 0);

        Bed newBed = new Bed("placeholder");

        Room newRoom = Room.builder().type(type)
                .description(description)
                .minRoomNumber(minRoomNumber)
                .maxRoomNumber(maxRoomNumber)
                .bedType(newBed)
                .roomTypeTotal(roomTypeTotal)
                .rate(rate)
                .build();

        roomRepo.addRoom(newRoom);
    }


}
