package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.IO
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.ChooseRoomState
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.None
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.RemoveRoomTerminalState
import io.github.spencerduberry.hotelreservation.utils.State

class RemoveRoomJourney(
    private val roomRepo: RoomTypeRepository,
    private val io: IO,
    )
 {
     suspend fun process() {
         var state: State = ChooseRoomState(
             None,
             RemoveRoomContext(
                 roomRepo.getAll(),
                 ""
             )
         )

         do {
             io.printLine(state.ui())
             state = state.process(io.line())
         } while (state !is RemoveRoomTerminalState)

         roomRepo.setAll(state.context.roomTypes)

     }
}