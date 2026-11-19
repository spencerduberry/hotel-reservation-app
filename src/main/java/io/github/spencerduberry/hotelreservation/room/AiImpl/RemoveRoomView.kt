package io.github.spencerduberry.hotelreservation.room.AiImpl

import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository
import java.util.Locale
import java.util.ResourceBundle

val bundle = ResourceBundle.getBundle("messages", Locale.getDefault())

fun renderRemoveRoomUi(state: RemoveRoomState, repo : RoomTypeRepository) {
    println("=== Remove room ===")
    when (state) {
        is RemoveRoomState.InputNamePhase -> {
            println("Please select a room to remove")
            println(repo.all)

        }
        is RemoveRoomState.ErrorPhase -> {
            printError(state.error)
        }
        is RemoveRoomState.SuccessPhase -> {
            println("Success: Deleted room: ${state.removedRoom}")
        }
    }
}

fun printError(error: RoomRemovalError) {
    val key = when (error) {
        is RoomRemovalError.InvalidRoomNumber -> "error.room-removal.invalid"
    }
    System.err.println(bundle.getString((key)))
}