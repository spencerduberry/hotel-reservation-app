package io.github.spencerduberry.hotelreservation.room.AiImpl

import io.github.spencerduberry.hotelreservation.room.Room

sealed interface RemoveRoomState {

    object InputNamePhase : RemoveRoomState

    data class ErrorPhase(val error: RoomRemovalError) : RemoveRoomState

    data class SuccessPhase(val removedRoom : Room) : RemoveRoomState
}

sealed interface RoomRemovalError {

    object InvalidRoomNumber : RoomRemovalError
}