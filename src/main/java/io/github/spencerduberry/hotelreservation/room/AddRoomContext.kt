package io.github.spencerduberry.hotelreservation.room

data class AddRoomContext(
    val roomTypes: List<Room>,
    val bedTypes: List<String>,
    val previousInput: String
)