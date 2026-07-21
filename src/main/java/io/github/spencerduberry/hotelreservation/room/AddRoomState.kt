//package io.github.spencerduberry.hotelreservation.room
//
//import io.github.spencerduberry.hotelreservation.bed.BedContext
//import io.github.spencerduberry.hotelreservation.bed.BedState
//
//sealed interface AddRoomState {
//
//    val previous: AddRoomState
//    val context: AddRoomContext
//
//    fun process(input: String): AddRoomState
//    fun ui(): String
//
//    object None : AddRoomState {
//        override val previous: AddRoomState = this
//        override val context: AddRoomContext = AddRoomContext(emptyList(), "")
//
//        override fun process(input: String): AddRoomState = this
//
//        override fun ui(): String = ""
//    }
//
//    class EnterNameState(
//        override val previous: AddRoomState,
//        override val context: AddRoomContext
//    ) : AddRoomState {
//        override fun process(input: String): AddRoomState {
//            val next = context.copy(previousInput = input)
//
//            return AddBedtypeState(this, next)
//        }
//
//        override fun ui(): String = """
//            Please enter new room name.
//        """.trimIndent()
//    }
//}