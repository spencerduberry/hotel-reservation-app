//package io.github.spencerduberry.hotelreservation.room
//
//import io.github.spencerduberry.hotelreservation.bed.AddBedState.AddBedTypeAlreadyExists
//import io.github.spencerduberry.hotelreservation.bed.AddBedState.ConfirmAddAddBedType
//import io.github.spencerduberry.hotelreservation.utils.State
//import io.github.spencerduberry.hotelreservation.utils.Validator.EmptyInput
//import io.github.spencerduberry.hotelreservation.utils.Validator.InputTooLong
//
//sealed interface AddRoomState: State {
//
//    val previous: AddRoomState
//    val context: AddRoomContext
//
//    object None : AddRoomState {
//        override val previous: AddRoomState = this
//        override val context: AddRoomContext = AddRoomContext(emptyList(), emptyList(), "")
//
//        override fun process(input: String): AddRoomState = this
//
//        override fun ui(): String = ""
//    }
//
//    class EnterNewRoomState(
//        override val previous: AddRoomState,
//        override val context: AddRoomContext
//    ) : AddRoomState {
//
//        override fun process(input: String): AddRoomState {
//            val next = context.copy(previousInput = input)
//            return when {
//                input.isBlank() -> EmptyInput(this, next)
//                input.length > 20 -> InputTooLong(this, next)
//                input.lowercase() in context.bedTypes -> AddBedTypeAlreadyExists(this, next)
//                else -> ConfirmAddAddBedType(this, next)
//            }
//        }
//
//        override fun ui(): String = """
//            Please enter new room name.
//        """.trimIndent()
//    }
//
//    class AddBedTypeState(
//        override val previous: AddRoomState,
//        override val context: AddRoomContext
//    ) : AddRoomState {
//        val bedMap = context.bedTypes.mapIndexed { index, string ->
//            (index + 1).toString() to string
//        }.toMap()
//
//        override fun process(input: String): AddRoomState {
//            val next = context.copy(previousInput = input)
//
//            return when {
//                input.trim() in bedMap.keys -> AddRateState(this, next)
//                else -> previous
//            }
//        }
//
//        override fun ui(): String = """
//            Please select a bed type:
//
//            '${bedMap}'
//        """.trimIndent()
//    }
//
//    class AddRateState(
//        override val previous: AddRoomState,
//        override val context: AddRoomContext
//    ) : AddRoomState {
//        override fun process(input: String): AddRoomState {
//            TODO("Not yet implemented")
//        }
//
//        override fun ui(): String {
//            TODO("Not yet implemented")
//        }
//    }
//}