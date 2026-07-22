package io.github.spencerduberry.hotelreservation.room

sealed interface AddRoomState {

    val previous: AddRoomState
    val context: AddRoomContext

    fun process(input: String): AddRoomState
    fun ui(): String

    object None : AddRoomState {
        override val previous: AddRoomState = this
        override val context: AddRoomContext = AddRoomContext(emptyList(), emptyList(), "")

        override fun process(input: String): AddRoomState = this

        override fun ui(): String = ""
    }

    class EnterNameState(
        override val previous: AddRoomState,
        override val context: AddRoomContext
    ) : AddRoomState {
        override fun process(input: String): AddRoomState {
            val next = context.copy(previousInput = input)

            return AddBedTypeState(this, next)
        }

        override fun ui(): String = """
            Please enter new room name.
        """.trimIndent()
    }

    class AddBedTypeState(
        override val previous: AddRoomState,
        override val context: AddRoomContext
    ) : AddRoomState {
        val bedMap = context.bedTypes.mapIndexed { index, string ->
            (index + 1).toString() to string
        }.toMap()

        override fun process(input: String): AddRoomState {
            val next = context.copy(previousInput = input)

            return when {
                input.trim() in bedMap.keys -> AddRateState(this, next)
                else -> previous
            }
        }

        override fun ui(): String = """
            Please select a bed type:
            
            '${bedMap}'
        """.trimIndent()
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
}