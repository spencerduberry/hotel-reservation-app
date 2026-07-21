package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.ErrorPhase
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.SuccessPhase


fun evaluateRoomRemovalInput(
    currentState: RemoveRoomState,
    input: String,
    existingRooms: Map<String, Room>,
): RemoveRoomState {
    val trimmedInput = input.trim()

    return when {
        trimmedInput !in existingRooms.keys ->
            ErrorPhase(RoomRemovalError.InvalidRoomNumber)
        //could throw assertion error instead of !!
        else -> SuccessPhase(existingRooms[trimmedInput]!!)
    }
}