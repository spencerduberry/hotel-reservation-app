package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.utils.CustomInput
import jdk.jfr.internal.OldObjectSample.emit
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.takeWhile

class AddBedWorkflow(private val repository: BedTypeRepository) {

    suspend fun run(input: CustomInput) {
        //loop will catch user input, push down the pipe, then wait until next input
        val userInputFlow = flow {
            while (true) {
                val nextLine = input.inputString()
                emit(nextLine)
            }
        }
        //renders UI prompt before stream begins
        renderBedUi(AddBedViewState.InputNamePhase)

        userInputFlow
            //takes emitted item, previous state and current repo list, and adds to reducer function
            .scan<String, AddBedViewState>(AddBedViewState.InputNamePhase) {previousState, input ->
                pureBedReducer(previousState, input, repository.getAll())
            }
            // error states are pushed into the flow. Success tate terminates it
            .takeWhile { state -> state !is AddBedViewState.SuccessPhase }
            //UI updated onSuccess
            .collect { state -> renderBedUi(state)

            if (state is AddBedViewState.SuccessPhase) {
                repository.addBed(state.newBed)
            }}
    }
}