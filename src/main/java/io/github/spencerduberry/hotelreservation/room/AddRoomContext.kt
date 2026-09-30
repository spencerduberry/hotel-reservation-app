package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.utils.Context

data class AddRoomContext(
    val roomTypes: List<Room>,
    val bedTypes: List<String>,
    override val previousInput: String,
    override val name: String = "Room"
): Context