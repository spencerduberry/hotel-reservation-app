package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.ai.bundle
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.InputNamePhase
import java.util.Locale
import java.util.ResourceBundle

val bundle = ResourceBundle.getBundle("messages", Locale.getDefault())

fun renderRemoveRoomUi(state: RemoveRoomState, repo : RoomTypeRepository) {
    println("=== Remove room ===")
    when (state) {
        is InputNamePhase -> {
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