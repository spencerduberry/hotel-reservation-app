package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.utils.CustomInput
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.takeWhile
import kotlinx.coroutines.flow.transformWhile

class AddBedFlow(private val repository: BedTypeRepository) {

    suspend fun run(input: CustomInput) {
        //loop will catch user input, push down the pipe, then wait until next input
        val userInputFlow = flow {
            while (true) {
                val nextLine = input.inputString()
                emit(nextLine)
            }
        }
        //renders UI prompt before stream begins
        renderBedUi(InputNamePhase)

        userInputFlow
            .scan<String, AddBedViewState>(InputNamePhase) {
                previousState, text ->
                evaluateBedInput(previousState, text, repository.getAll())
            }
            .drop(1)
            .transformWhile { state ->
                emit(state)
                state !is SuccessPhase
            }
            .collect { state ->
                renderBedUi(state)
                if (state is SuccessPhase) {
                    repository.addBed(state.newBed)
                }
            }
    }
}