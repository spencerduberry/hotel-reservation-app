package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.BedTypeRepository
import io.github.spencerduberry.hotelreservation.bed.IO
import io.github.spencerduberry.hotelreservation.room.AddRoomState.AddNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.EnterNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.None
import io.github.spencerduberry.hotelreservation.utils.State

class AddRoomJourney(
    private val roomRepo: RoomTypeRepository,
    private val bedRepo: BedTypeRepository,
    private val io: IO,
) {
    suspend fun process() {
        var state: State = EnterNewRoomState(
            None,
            AddRoomContext(
                roomRepo.getAll(),
                bedRepo.getAll(),
                ""
            )
        )

        do {
            io.printLine(state.ui())
            state = state.process(io.line())
        } while (state !is AddNewRoomState)

        roomRepo.setAll(state.context.roomTypes)

    }
}
