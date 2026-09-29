package io.github.spencerduberry.hotelreservation.utils

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
}