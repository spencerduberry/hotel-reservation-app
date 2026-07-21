package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ErrorPhase
import java.util.Locale
import java.util.ResourceBundle

val bundle = ResourceBundle.getBundle("messages", Locale.getDefault())

fun renderBedUi(state: AddBedViewState) {
    println("=== Add New Bed Type ===")
    when (state) {
        is InputNamePhase -> {
            println("Please enter the name of the new bed type:")
        }
        is ErrorPhase -> {
            printError(state.error)
        }
        is SuccessPhase -> {
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
