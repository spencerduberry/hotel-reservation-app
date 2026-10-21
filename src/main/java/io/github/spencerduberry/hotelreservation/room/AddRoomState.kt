package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.utils.State
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.ALREADY_EXISTS
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.EMPTY_INPUT
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.NEGATIVE_INTEGER
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.TOO_LONG
import io.github.spencerduberry.hotelreservation.utils.validate

sealed interface AddRoomState : State {
    val previous: AddRoomState
    val context: AddRoomContext

    object None : AddRoomState {
        override val previous: AddRoomState = this
        override val context: AddRoomContext = AddRoomContext(emptyList(), emptyList(), "")

        override fun process(input: String): AddRoomState = this

        override fun ui(): String = ""
    }

    class EnterNewRoomState(
        override val previous: AddRoomState,
        override val context: AddRoomContext
    ) : AddRoomState {

        override fun process(input: String): State {
            val next = context.copy(previousInput = input)

            return validate(
                listOf(
                    EMPTY_INPUT,
                    TOO_LONG,
                    ALREADY_EXISTS
                ),
                next,
                input,
                this,
                ChooseBedState(this, next, input)
            )
        }

        override fun ui(): String = """
            Please enter room name. Input must:
                - Not be empty.
                - Be fewer than 20 characters in length.
                - Be unique.
        """.trimIndent()
    }

    class ChooseBedState(
        override val previous: AddRoomState,
        override val context: AddRoomContext,
        val name: String
    ) : AddRoomState {
        override fun process(input: String): AddRoomState {
            val next = context.copy(previousInput = input)
            // catches the exceptions
            return runCatching {
                // no need to make nullable because of catch block
                input.toInt()
                    // subtract one from input to match index
                    .let { (it - 1) }
                    // get the selected bed
                    .let { context.bedTypes[it] }
            }.map { AddRateState(this, next, name, it) }
                .getOrElse {
                    InvalidBed(this, next, name)
                }
        }
    }

    override fun ui(): String {
        val displayable = context.bedTypes
            .mapIndexed { i, bedType -> "${i + 1}. $bedType" }
            .joinToString("\n")

        return """
            Please select a bed type:

            $displayable
        """.trimIndent()
    }

    class InvalidBed(
        override val previous: AddRoomState,
        override val context: AddRoomContext,
        val name: String
    ) : AddRoomState {
        override fun process(input: String): State {
            val next = context.copy(previousInput = input)
            return when (input) {
                "\n" -> ChooseBedState(this, next, name)
                else -> InvalidBed(this, next, name)
            }
        }

        override fun ui(): String = """
                Please choose a valid bed type. Press enter to continue.
            """.trimIndent()
    }

    class AddRateState(
        override val previous: AddRoomState,
        override val context: AddRoomContext,
        val name: String,
        val bed: String
    ) : AddRoomState {
        override fun process(input: String): State {
            val next = context.copy(previousInput = input)

            return validate(
                listOf(
                    NEGATIVE_INTEGER
                ),
                next,
                input,
                this,
                AddNewRoomState(this, next, bed)
            )
        }

        override fun ui(): String = """
            Please enter the nightly rate for the room. 
            Value entered must be a positive integer.
        """.trimIndent()
    }

    class AddNewRoomState(
        override val previous: AddRoomState,
        override val context: AddRoomContext,
        val bed: String
    ) : AddRoomState {
        override fun process(input: String): AddRoomState {
            TODO("Not yet implemented")
        }

        override fun ui(): String = "hiya"
    }
}