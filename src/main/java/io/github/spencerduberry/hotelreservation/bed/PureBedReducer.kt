package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ValidationErrorPhase

fun pureBedReducer(
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
                "A bed type named '$trimmedInput' already exists."
            )
        //or success
        else -> AddBedViewState.SuccessPhase(Bed(trimmedInput))
    }
}