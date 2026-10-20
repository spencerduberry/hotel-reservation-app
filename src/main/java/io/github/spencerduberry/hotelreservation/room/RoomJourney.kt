package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.BedTypeRepository
import io.github.spencerduberry.hotelreservation.bed.IO
import io.github.spencerduberry.hotelreservation.room.AddRoomState.AddNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.EnterNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.None
import io.github.spencerduberry.hotelreservation.utils.State

class RoomJourney(
    private val roomRepo: RoomTypeRepository,
    private val bedRepo: BedTypeRepository,
    private val io: IO,
    ) {
        suspend fun process() {
            val context = AddRoomContext(roomRepo.getAll(), bedRepo.getAll(), "")
            var state: State = EnterNewRoomState(None, context)
            do {
                io.printLine(state.ui())
                state = state.process(io.line())
            } while(state !is AddNewRoomState)

            roomRepo.setAll(state.context.roomTypes)
        }
}
