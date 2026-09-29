package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.BedState.EnterNewBedState
import io.github.spencerduberry.hotelreservation.bed.BedState.NewBedType
import io.github.spencerduberry.hotelreservation.bed.BedState.None
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BedJourney(
    private val repo: BedTypeRepository,
    private val io: IO,
) {
    suspend fun process() {
        // establish an initial context and BedState
        val context = BedContext(repo.getAll(), "")
        var state : BedState = EnterNewBedState(None, context)
        // repeat user prompts until bed successfully created
        do {
            io.printLine(state.ui())
            state = state.process(io.line())
        } while(state !is NewBedType)

        repo.setAll(state.context.bedTypes)
    }
}

interface IO {
    suspend fun line() = withContext(Dispatchers.IO) {
        readln()
    }
    fun printLine(input: String) = println(input)
}