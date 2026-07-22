package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.InputNamePhase
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.SuccessPhase
import io.github.spencerduberry.hotelreservation.utils.CustomInput
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.transformWhile

class RemoveRoomFlow (private val repository: RoomTypeRepository) {

    suspend fun run(input: CustomInput) {
        val userInputFlow = flow {
            while (true) {
                val nextLine = input.inputString()
                emit(nextLine)
            }
        }

        userInputFlow
            .scan<String, RemoveRoomState>(InputNamePhase) {
                    previousState, text ->
                evaluateRoomRemovalInput(previousState, text, repository.getAll())
            }
            .transformWhile { state: RemoveRoomState ->
                emit(state)
                state !is SuccessPhase
            }
            .collect { state ->
                renderRemoveRoomUi(state, repository)
                if (state is SuccessPhase) {
                    repository.removeRoom(state.removedRoom)
                }
            }
    }
}