package io.github.spencerduberry.hotelreservation.bed

fun renderBedUi(state: AddBedViewState) {
    println("=== Add New Bed Type ===")
    when (state) {
        is AddBedViewState.InputNamePhase -> {
            println("Please enter the name of the new bed type:")
        }
        is AddBedViewState.ValidationErrorPhase -> {
            println("Error: ${state.errorReason}")
            println("You entered: \"${state.invalidName}\"")
            println("Please try again:")
        }
        is AddBedViewState.SuccessPhase -> {
            println("Success: Created bed type: ${state.newBed.bedType}")
        }
    }
}