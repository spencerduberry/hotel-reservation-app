package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ErrorPhase
import io.github.spencerduberry.hotelreservation.bed.BedValidationError.AlreadyExists
import io.github.spencerduberry.hotelreservation.bed.BedValidationError.Empty
import io.github.spencerduberry.hotelreservation.bed.BedValidationError.TooLong

fun evaluateBedInput(
    currentState: AddBedViewState,
    input: String,
    existingBeds: List<Bed>,
//    isRoomAvailable: (List<Bed>) -> Boolean,
): AddBedViewState {
    //handle white space
    val trimmedInput = input.trim()

    return when {
        //return errors when error
        trimmedInput.isEmpty() ->
            ErrorPhase(Empty)

        trimmedInput.length > 20 ->
            ErrorPhase(TooLong(trimmedInput.length))

        existingBeds.any { it.bedType.equals(trimmedInput, ignoreCase = true) } ->
            ErrorPhase(
                AlreadyExists(trimmedInput)
            )
        //or success
        else -> SuccessPhase(Bed(trimmedInput))
    }
}
