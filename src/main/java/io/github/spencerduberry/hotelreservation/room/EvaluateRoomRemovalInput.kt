//package io.github.spencerduberry.hotelreservation.room
//
//import io.github.spencerduberry.hotelreservation.room.AiImpl.RemoveRoomState.ErrorPhase
//import io.github.spencerduberry.hotelreservation.room.AiImpl.RemoveRoomState.SuccessPhase
//
//
//fun evaluateRoomRemovalInput(
//    currentState: RemoveRoomState,
//    input: String,
//    existingRooms: List<Room>,
//): RemoveRoomState {
//    val trimmedInput = input.trim()
//
//    return when {
//        trimmedInput !in existingRooms.keys ->
//            ErrorPhase(RoomRemovalError.InvalidRoomNumber)
//        //could throw assertion error instead of !!
//        else -> SuccessPhase(existingRooms[trimmedInput]!!)
//    }
//}