package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.utils.CustomInput
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.takeWhile

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
            //takes emitted item, previous state and current repo list, and adds to reducer function
            .scan<String, AddBedViewState>(InputNamePhase) { previousState, input ->
                evaluateBedInput(previousState, input, repository.getAll())
            }
            // error states are pushed into the flow. Success state terminates it
            .takeWhile { state -> state !is SuccessPhase }
            //UI updated onSuccess
            .collect { state -> renderBedUi(state)

            if (state is SuccessPhase) {
                repository.addBed(state.newBed)
            }}
    }
}