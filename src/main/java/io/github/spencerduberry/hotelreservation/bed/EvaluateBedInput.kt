package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ValidationErrorPhase

fun evaluateBedInput(
    currentState: AddBedViewState,
    input: String,
    existingBeds: List<Bed>
): AddBedViewState {
    //handle white space
    val trimmedInput = input.trim()

    return when {
        //return errors when error
        trimmedInput.isEmpty() ->
            ValidationErrorPhase(input, "Bed name cannot be empty.")

        trimmedInput.length > 20 ->
            ValidationErrorPhase(input, "Bed name cannot exceed 20 characters.")

        existingBeds.any { it.bedType.equals(trimmedInput, ignoreCase = true) } ->
            ValidationErrorPhase(
                input,
                "A bed type with that name already exists."
            )
        //or success
        else -> SuccessPhase(Bed(trimmedInput))
    }
}