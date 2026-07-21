package io.github.spencerduberry.hotelreservation.room

data class AddRoomContext(
    val roomTypes: List<Room>,
    val previousInput: String

)