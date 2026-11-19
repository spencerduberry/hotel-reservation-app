package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.utils.Context

data class RemoveRoomContext(
    val roomTypes: List<Room>,
    override val previousInput: String,
    override val type: String = "Room",
    val removalKey: Int? = null
): Context