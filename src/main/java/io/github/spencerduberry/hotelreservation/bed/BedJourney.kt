package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.BedState.EnterNewBedState
import io.github.spencerduberry.hotelreservation.bed.BedState.NewBedType
import io.github.spencerduberry.hotelreservation.bed.BedState.None

class BedJourney(
    private val repo: BedTypeRepository,
    private val io: IO,
) {
    fun process() {
        val context = BedContext(repo.getAll(), "")
        var state : BedState = EnterNewBedState(None, context)
        do {
            io.printLine(state.ui())
            state = state.process(io.readLine())
        } while(state !is NewBedType)

        repo.setAll(state.context.bedTypes)
    }
}

interface IO {
    fun readLine() = readln()
    fun printLine(input: String) = println(input)
}