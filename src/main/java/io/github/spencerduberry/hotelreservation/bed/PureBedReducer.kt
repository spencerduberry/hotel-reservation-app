package io.github.spencerduberry.hotelreservation.bed

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
            AddBedViewState.ValidationErrorPhase(input, "Bed name cannot be empty.")

        trimmedInput.length > 20 ->
            AddBedViewState.ValidationErrorPhase(input, "Bed name cannot exceed 20 characters.")

        existingBeds.any { it.bedType.equals(trimmedInput, ignoreCase = true) } ->
            AddBedViewState.ValidationErrorPhase(
                input,
                "A bed type named '$trimmedInput' already exists."
            )
        //or success
        else -> AddBedViewState.SuccessPhase(Bed(trimmedInput))
    }
}