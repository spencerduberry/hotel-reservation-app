package io.github.spencerduberry.hotelreservation.room

sealed interface RemoveRoomState {

    object InputNamePhase : RemoveRoomState

    data class ErrorPhase(val error: RoomRemovalError) : RemoveRoomState

    data class SuccessPhase(val removedRoom : Room) : RemoveRoomState
}

sealed interface RoomRemovalError {

    object InvalidRoomNumber : RoomRemovalError
}