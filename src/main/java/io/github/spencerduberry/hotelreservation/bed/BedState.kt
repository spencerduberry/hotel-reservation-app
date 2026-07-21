package io.github.spencerduberry.hotelreservation.bed

sealed interface BedState {

    object None : BedState {
        override val previous: BedState = this
        override val context: BedContext = BedContext(emptyList(), "")

        override fun process(input: String): BedState = this

        override fun ui(): String = ""
    }

    class EnterNewBedState(
        override val previous: BedState,
        override val context: BedContext,
    ) : BedState {

        override fun process(input: String): BedState {
            val next = context.copy(previousInput = input)
            return when {
                input.isBlank() -> EmptyInput(this, next)
                input.length > 20 -> InputTooLong(this, next)
                input.lowercase() in context.bedTypes -> BedTypeAlreadyExists(this, next)
                else -> ConfirmAddBedType(this, next.copy(bedTypes = next.bedTypes + input))
            }
        }


        override fun ui(): String = """
            Please enter new bed type. Input must:
                - Not be empty.
                - Be fewer than 20 characters in length.
                - Be unique.
        """.trimIndent()
    }

    class ConfirmAddBedType(
        override val previous: BedState, override val context: BedContext
    ) : BedState {
        override fun process(input: String): BedState {
            return when {
                input.trim().lowercase() == "y" -> NewBedType(previous, context.copy())
                else -> previous
            }
        }


        override fun ui(): String = """
            Do you want to add '${context.previousInput}' as a new bed type?
            Press "y" to confirm, or another other key to abort.
        """.trimIndent()

    }

    class NewBedType(
        override val previous: BedState,
        override val context: BedContext,
    ) : BedState {
        override fun process(input: String): BedState = this
        override fun ui(): String = ""

    }

    class BedTypeAlreadyExists(
        override val previous: BedState, override val context: BedContext
    ) : BedState {

        override fun process(input: String): BedState = previous

        override fun ui(): String = """
            Input '${context.previousInput}' matches a bed type that already exists. 
            Press any key to try again.
        """.trimIndent()

    }

    class EmptyInput(
        override val previous: BedState, override val context: BedContext
    ) : BedState {
        override fun process(input: String): BedState = previous

        override fun ui(): String = """
            Input cannot be empty. 
            Press any key to try again.
        """.trimIndent()
    }

    class InputTooLong(
        override val previous: BedState, override val context: BedContext
    ) : BedState {
        override fun process(input: String): BedState = previous

        override fun ui(): String = """
            Input '${context.previousInput}' is longer than 20 characters.
            Press any key to try again.
        """.trimIndent()
    }

    val previous: BedState
    val context: BedContext

    fun process(input: String): BedState
    fun ui(): String
}