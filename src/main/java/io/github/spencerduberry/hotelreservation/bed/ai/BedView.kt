package io.github.spencerduberry.hotelreservation.bed.ai

import java.util.Locale
import java.util.ResourceBundle

val bundle = ResourceBundle.getBundle("messages", Locale.getDefault())

fun renderBedUi(state: AddBedViewState) {
    println("=== Add New Bed Type ===")
    when (state) {
        is AddBedViewState.InputNamePhase -> {
            println("Please enter the name of the new bed type:")
        }
        is AddBedViewState.ErrorPhase -> {
            printError(state.error)
        }
        is AddBedViewState.SuccessPhase -> {
            println("Success: Created bed type: ${state.newBed}")
        }
    }
}

fun printError(error: BedValidationError) {
    val key = when (error) {
        is BedValidationError.Empty -> "error.bed.empty"
        is BedValidationError.TooLong -> "error.bed.toolong"
        is BedValidationError.AlreadyExists -> "error.bed.exists"
    }
    System.err.println(bundle.getString((key)))
}
