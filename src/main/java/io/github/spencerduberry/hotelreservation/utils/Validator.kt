package io.github.spencerduberry.hotelreservation.utils

import io.github.spencerduberry.hotelreservation.bed.AddBedContext
import io.github.spencerduberry.hotelreservation.room.AddRoomContext
import io.github.spencerduberry.hotelreservation.room.RemoveRoomContext
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.ALREADY_EXISTS
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.EMPTY_INPUT
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.INVALID_CHOICE
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.NEGATIVE_INTEGER
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.TOO_LONG
import io.github.spencerduberry.hotelreservation.utils.Validator.EmptyInput
import io.github.spencerduberry.hotelreservation.utils.Validator.InputAlreadyExists
import io.github.spencerduberry.hotelreservation.utils.Validator.InputNegativeInteger
import io.github.spencerduberry.hotelreservation.utils.Validator.InputTooLong

//import java.util.function.Predicate

sealed interface Validator : State {

    class EmptyInput(
        val previous: State
    ) : State {
        override fun process(input: String): State = previous

        override fun ui(): String = """
                Input cannot be empty. 
                Press any key to try again.
            """.trimIndent()
    }

    class InputTooLong(
        val previous: State, val context: Context
    ) : State {
        override fun process(input: String): State = previous

        override fun ui(): String = """
                Input '${context.previousInput}' is longer than 20 characters.
                Press any key to try again.
            """.trimIndent()
    }

    class InputAlreadyExists(
        val previous: State, val context: Context
    ) : State {
        override fun process(input: String): State = previous

        override fun ui(): String = """
                Input A ${context.type} with this name already exists.
                Press any key to try again.
            """.trimIndent()
    }

    class InputNegativeInteger(
        val previous: State
    ) : State {
        override fun process(input: String): State = previous

        override fun ui(): String = """
                Input must be a positive integer.
                Press any key to try again.
            """.trimIndent()
    }

    class InvalidMenuChoice(
        val previous : State, val context: Context
    ) : State {
        override fun process(input: String): State = previous

        override fun ui(): String = """
                Please choose a valid option.
            """.trimIndent()
    }
}

enum class ValidationKey {
    EMPTY_INPUT,
    TOO_LONG,
    ALREADY_EXISTS,
    NEGATIVE_INTEGER,
    INVALID_CHOICE;
}

fun getValidationRule(key: ValidationKey, context: Context): (String) -> Boolean {
    return when (key) {
        EMPTY_INPUT -> { input -> input.isBlank() }
        TOO_LONG -> { input -> input.length > 20 }
        ALREADY_EXISTS -> { input -> inputAlreadyExists(input, context) }
        NEGATIVE_INTEGER -> { input -> input.toInt() < 1 }
        INVALID_CHOICE -> { input -> invalidChoice(input, context) }
    }

}

fun inputAlreadyExists(input: String, context: Context): Boolean {
    return when (context) {
        is AddBedContext -> context.bedTypes.contains(input)
        is AddRoomContext -> context.roomTypes.any { it.name() == input }
        else -> false
    }
}

fun invalidChoice(input: String, context: Context): Boolean {
    return when (context) {
        is AddRoomContext -> input.toInt() !in 1..context.bedTypes.size
        is RemoveRoomContext -> input.toInt() !in 1..context.roomTypes.size
        else -> false
    }
}

fun getErrorState(key: ValidationKey, previousState: State, context: Context): State {
    return when (key) {
        EMPTY_INPUT -> EmptyInput(previousState)
        TOO_LONG -> InputTooLong(previousState, context)
        ALREADY_EXISTS -> InputAlreadyExists(previousState, context)
        NEGATIVE_INTEGER -> InputNegativeInteger(previousState)
        INVALID_CHOICE -> Validator.InvalidMenuChoice(previousState, context)
    }
}

fun validate(
    keys: List<ValidationKey>,
    context: Context,
    input: String,
    previousState: State,
    nextState: State
): State {
    // loop through the keys in the list
    for (key in keys) {
        //get the rule that corresponds to the key
        val rule = getValidationRule(key, context)
        //if the input satisfies the predicate
        if (rule(input)) {
            //return the appropriate error state
            return getErrorState(key, previousState, context)
        }
    }
    return nextState
}
