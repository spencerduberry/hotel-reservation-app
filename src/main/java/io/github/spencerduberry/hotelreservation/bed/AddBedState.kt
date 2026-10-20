package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.utils.State
import io.github.spencerduberry.hotelreservation.utils.ValidationKey
import io.github.spencerduberry.hotelreservation.utils.validate

sealed interface AddBedState: State {

    object None : AddBedState {
        override val previous: AddBedState = this
        override val context: AddBedContext = AddBedContext(emptyList(), "")

        override fun process(input: String): AddBedState = this

        override fun ui(): String = ""
    }

    class EnterNewBedState(
        override val previous: AddBedState,
        override val context: AddBedContext,
    ) : AddBedState {

        override fun process(input: String): State {
            val next = context.copy(previousInput = input)

            return validate(
                ValidationKey.entries,
                next,
                input,
                this,
                ConfirmAddAddBedType(this, next)
            )
        }


        override fun ui(): String = """
            Please enter new bed type. Input must:
                - Not be empty.
                - Be fewer than 20 characters in length.
                - Be unique.
        """.trimIndent()
    }

    class ConfirmAddAddBedType(
        override val previous: AddBedState, override val context: AddBedContext
    ) : AddBedState {
        override fun process(input: String): AddBedState {
            return when {
                input.trim().lowercase() == "y" -> AddNewBedState(previous, context.copy(bedTypes = context.bedTypes + context.previousInput))
                else -> previous
            }
        }


        override fun ui(): String = """
            Do you want to add '${context.previousInput}' as a new bed type?
            Press "y" to confirm, or another other key to abort.
        """.trimIndent()

    }

    class AddNewBedState(
        override val previous: AddBedState,
        override val context: AddBedContext,
    ) : AddBedState {
        override fun process(input: String): AddBedState = this
        override fun ui(): String = ""

    }

    val previous: AddBedState
    val context: AddBedContext


}