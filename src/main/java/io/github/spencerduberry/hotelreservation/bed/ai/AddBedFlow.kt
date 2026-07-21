package io.github.spencerduberry.hotelreservation.bed.ai

import io.github.spencerduberry.hotelreservation.bed.BedTypeRepository
import io.github.spencerduberry.hotelreservation.utils.CustomInput
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.scan
import kotlinx.coroutines.flow.transformWhile

class AddBedFlow(private val repository: BedTypeRepository) {

    suspend fun run(input: CustomInput) {
        //loop will catch user input, push down the pipe, then wait until next input
        val userInputFlow = flow {
            //on until explicitly switched off
            while (true) {
                val nextLine = input.inputString()
                emit(nextLine)
            }
        }

        userInputFlow
            /*takes current state and user input string and feeds into reducer. Current state is
            initially InputNamePhase but will be error state on the second run unless success*/
            .scan<String, AddBedViewState>(AddBedViewState.InputNamePhase) {
                previousState, text ->
                evaluateBedInput(previousState, text, repository.getAll())
            }
            //emissions stop once the condition evaluates to false
            .transformWhile { state ->
                emit(state)
                state !is AddBedViewState.SuccessPhase
            }
            .collect { state ->
                renderBedUi(state)
                if (state is AddBedViewState.SuccessPhase) {
                    repository.addBed(state.newBed)
                }
            }
    }
}