package io.github.spencerduberry.hotelreservation.bed

//all possible states of add bed operation
sealed interface AddBedViewState {
    //display UI prompt
    object InputNamePhase : AddBedViewState

    //just defines a template for an error
    data class ErrorPhase(
        val error: BedValidationError
    ) : AddBedViewState

    data class SuccessPhase(val newBed: Bed) : AddBedViewState
}

sealed interface BedValidationError {

    object Empty : BedValidationError

    data class TooLong(val length: Int) : BedValidationError

    data class AlreadyExists(val name: String) : BedValidationError
}