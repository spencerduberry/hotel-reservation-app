package io.github.spencerduberry.hotelreservation.bed.ai

fun evaluateBedInput(
    currentState: AddBedViewState,
    input: String,
    existingBeds: List<String>,
//    isRoomAvailable: (List<Bed>) -> Boolean,
): AddBedViewState {
    //handle white space
    val trimmedInput = input.trim()

    return when {
        //return errors when error
        trimmedInput.isEmpty() ->
            AddBedViewState.ErrorPhase(BedValidationError.Empty)

        trimmedInput.length > 20 ->
            AddBedViewState.ErrorPhase(BedValidationError.TooLong(trimmedInput.length))

        existingBeds.any { it.equals(trimmedInput, ignoreCase = true) } ->
            AddBedViewState.ErrorPhase(
                BedValidationError.AlreadyExists(trimmedInput)
            )
        //or success
        else -> AddBedViewState.SuccessPhase(trimmedInput)
    }
}
