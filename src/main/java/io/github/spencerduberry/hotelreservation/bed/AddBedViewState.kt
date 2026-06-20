package io.github.spencerduberry.hotelreservation.bed

//all possible states of add bed operation
sealed interface AddBedViewState {
    //display UI prompt
    object InputNamePhase : AddBedViewState

    //just defines a template for an error
    data class ValidationErrorPhase(
        val invalidName: String,
        val errorReason: String
    ) : AddBedViewState

    data class SuccessPhase(val newBed: Bed) : AddBedViewState
}