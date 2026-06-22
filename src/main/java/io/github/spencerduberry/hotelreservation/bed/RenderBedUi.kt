package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ValidationErrorPhase

fun renderBedUi(state: AddBedViewState) {
    println("=== Add New Bed Type ===")
    when (state) {
        is InputNamePhase -> {
            println("Please enter the name of the new bed type:")
        }
        is ValidationErrorPhase -> {
            println("Error: ${state.errorReason}")
            println("You entered: \"${state.invalidName}\"")
            println("Please try again:")
        }
        is SuccessPhase -> {
            println("Success: Created bed type: ${state.newBed.bedType}")
        }
    }
}