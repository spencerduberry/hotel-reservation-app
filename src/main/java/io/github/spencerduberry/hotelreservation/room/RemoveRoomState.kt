package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.utils.State
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.INVALID_CHOICE
import io.github.spencerduberry.hotelreservation.utils.validate

sealed interface RemoveRoomState : State {
    val previous: RemoveRoomState
    val context: RemoveRoomContext

    object None : RemoveRoomState {
        override val previous: RemoveRoomState = this
        override val context: RemoveRoomContext = RemoveRoomContext(emptyList(), "")

        override fun process(input: String): RemoveRoomState = this

        override fun ui(): String = ""
    }

    class ChooseRoomState(
        override val previous: RemoveRoomState,
        override val context: RemoveRoomContext
    ) : RemoveRoomState {
        override fun process(input: String): State {
            val next = context.copy(previousInput = input)

            return validate(
                listOf(
                    INVALID_CHOICE
                ),
                next,
                input,
                this,
                ConfirmRemoveState(
                    this,
                    next.copy(removalKey = input.toInt())
                )
            )
        }

        override fun ui(): String {
            val roomsUi = context.roomTypes
                .mapIndexed { i, roomType -> "    ${i + 1}. $roomType" }
                .joinToString("\n")

            return "Please select a room type:\n$roomsUi"
        }
    }

    class ConfirmRemoveState(
        override val previous: RemoveRoomState,
        override val context: RemoveRoomContext
    ) : RemoveRoomState {
        val retrieveIndex = context.previousInput.toInt() - 1
        override fun process(input: String): State {
            return when {
                input.trim().lowercase() == "y" -> RemoveRoomTerminalState(
                    this,
                    context.copy(roomTypes = context.roomTypes - context.roomTypes[retrieveIndex])
                )

                else -> previous
            }
        }

        override fun ui(): String = """
            Are you sure you want to remove room: ${context.roomTypes[retrieveIndex]}?
            Press "y" to confirm, or another other key to abort.
        """.trimIndent()
    }

    class RemoveRoomTerminalState(
        override val previous: RemoveRoomState,
        override val context: RemoveRoomContext
    ) : RemoveRoomState {
        override fun process(input: String): State {
            return super.process(input)
        }

        override fun ui(): String {
            return super.ui()
        }
    }

    override fun process(input: String): State {
        TODO("Not yet implemented")
    }

    override fun ui(): String {
        TODO("Not yet implemented")
    }
}