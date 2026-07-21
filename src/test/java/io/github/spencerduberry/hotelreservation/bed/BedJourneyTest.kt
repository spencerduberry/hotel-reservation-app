package io.github.spencerduberry.hotelreservation.bed

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BedJourneyTest {

    val stubBedRepo = object: BedTypeRepository {
        private val types = mutableListOf<String>()

        override fun addBed(newBed: String) {
            TODO("Not yet implemented")
        }

        override fun removeBed(bed: String) {
            TODO("Not yet implemented")
        }

        override fun getAll(): List<String> =
            ArrayList(types)

        override fun setAll(beds: List<String>) {
            types.clear()
            types.addAll(beds)
        }

    }

    val stubIO = object: IO {
        private var line: String = ""
        override fun readLine(): String = line

        override fun printLine(input: String) {
            line = input
        }
    }

    @Test
    fun name() {
        BedJourney(stubBedRepo, stubIO).process()

    }
}