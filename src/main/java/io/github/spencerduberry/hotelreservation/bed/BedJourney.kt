package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedState.EnterNewBedState
import io.github.spencerduberry.hotelreservation.bed.AddBedState.NewAddBedType
import io.github.spencerduberry.hotelreservation.bed.AddBedState.None
import io.github.spencerduberry.hotelreservation.utils.Validator
import io.github.spencerduberry.hotelreservation.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BedJourney(
    private val repo: BedTypeRepository,
    private val io: IO,
) {
    suspend fun process() {
        // establish an initial context and BedState
        val context = AddBedContext(repo.getAll(), "")
        var state: State = EnterNewBedState(None, context)
        // repeat user prompts until bed successfully created
        do {
            io.printLine(state.ui())
            state = state.process(io.line())
        } while(state !is NewAddBedType)

        repo.setAll(state.context.bedTypes)
    }
}

interface IO {
    suspend fun line() = withContext(Dispatchers.IO) {
        readln()
    }
    fun printLine(input: String) = println(input)
}