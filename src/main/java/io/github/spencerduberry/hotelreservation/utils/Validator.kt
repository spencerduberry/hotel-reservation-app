package io.github.spencerduberry.hotelreservation.utils

import io.github.spencerduberry.hotelreservation.bed.AddBedContext
import io.github.spencerduberry.hotelreservation.room.AddRoomContext
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.ALREADY_EXISTS
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.EMPTY_INPUT
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.TOO_LONG
import io.github.spencerduberry.hotelreservation.utils.Validator.EmptyInput
import io.github.spencerduberry.hotelreservation.utils.Validator.InputAlreadyExists
import io.github.spencerduberry.hotelreservation.utils.Validator.InputTooLong

    sealed interface Validator: State{

        class EmptyInput(
            val previous: State, val context: Context
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
                Input 'A ${context.name}' with this name already exists.
                Press any key to try again.
            """.trimIndent()
        }
    }

    enum class ValidationKey {
        EMPTY_INPUT,
        TOO_LONG,
        ALREADY_EXISTS;
    }

    fun getValidationRule(key: ValidationKey, context: Context): (String) -> Boolean {
        return when (key) {
            EMPTY_INPUT -> { input -> input.isBlank() }
            TOO_LONG -> { input -> input.length > 20 }
            ALREADY_EXISTS -> { input -> inputAlreadyExists(input, context)}
        }

    }

    fun inputAlreadyExists(input: String, context: Context): Boolean {
        return when (context) {
            is AddBedContext -> context.bedTypes.contains(input)
            is AddRoomContext -> context.roomTypes.any{ it.name() == input }
            else -> {false}
        }
    }

    fun getErrorState(key: ValidationKey, previousState: State, context: Context): State {
        return when (key) {
            EMPTY_INPUT -> EmptyInput(previousState, context)
            TOO_LONG -> InputTooLong(previousState, context)
            ALREADY_EXISTS -> InputAlreadyExists(previousState, context)
        }
    }

    fun validate(keys: List<ValidationKey>, context: Context, input: String, previousState: State, nextState: State): State{
        for (key in keys) {
            val predicate = getValidationRule(key, context)

            if(predicate(input)) {
                return getErrorState(key, previousState, context)
            }
        }
        return nextState
    }
