package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.utils.State
import io.github.spencerduberry.hotelreservation.utils.ValidationKey
import io.github.spencerduberry.hotelreservation.utils.validate

sealed interface AddRoomState: State {
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
                ValidationKey.entries,
                next,
                input,
                this,
                ChooseBedState(this, next)
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
        override val context: AddRoomContext
    ) : AddRoomState {
        val bedMap = context.bedTypes.mapIndexed { index, bedType ->
            (index + 1).toString() to bedType
        }.toMap()

        override fun process(input: String): AddRoomState {
            val next = context.copy(previousInput = input)

            return when {
                input.trim() in bedMap.keys -> AddRateState(this, next)
                else -> previous
            }
        }

        override fun ui(): String {
            val formattedBeds = bedMap.entries.joinToString(separator = "\n") { (key, value) ->
                "$key: $value"
            }

            return """
            Please select a bed type:

            $formattedBeds
        """.trimIndent()
        }

    }

    class AddRateState(
        override val previous: AddRoomState,
        override val context: AddRoomContext
    ) : AddRoomState {
        override fun process(input: String): AddRoomState {
            TODO("Not yet implemented")
        }

        override fun ui(): String {
            TODO("Not yet implemented")
        }
    }

    class AddNewRoomState(
        override val previous: AddRoomState,
        override val context: AddRoomContext
    ) : AddRoomState {
        override fun process(input: String): AddRoomState {
            TODO("Not yet implemented")
        }

        override fun ui(): String {
            TODO("Not yet implemented")
        }
    }
}