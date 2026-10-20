package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.BedJourney
import io.github.spencerduberry.hotelreservation.bed.BedTypeRepository
import io.github.spencerduberry.hotelreservation.bed.IO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds
@OptIn(ExperimentalCoroutinesApi::class)
class RoomJourneyTest {
    val stubRoomRepo = object: RoomTypeRepository {
        private val types = mutableListOf<Room>()

        override fun addRoom(newRoom: Room) {
            TODO("Not yet implemented")
        }

        override fun removeRoom(room: Room) {
            TODO("Not yet implemented")
        }

        override fun getAll(): List<Room> =
            ArrayList(types)

        override fun setAll(rooms: List<Room>) {
            types.clear()
            types.addAll(rooms)
        }

    }

    val stubBedRepo = object: BedTypeRepository {
        private val types = mutableListOf<String>()

        override fun addBed(newBed: String) {
            types.add(newBed)
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

    class StubIo : IO {
        val userInput = Channel<String>(Channel.UNLIMITED)

        val output: MutableList<String> = mutableListOf()

        override suspend fun line(): String = userInput.receive()

        fun enterUserInput(input: String) {
            userInput.trySend(input)
        }

        override fun printLine(input: String) {
            output.add(input)
        }

        fun ui() : String = output.last()
    }

    @Test
    fun `when starting room journey, then UI shows input options`() = runTest {
        val io = StubIo()
        val job = backgroundScope.launch {
            RoomJourney(stubRoomRepo, stubBedRepo, io).process()
        }
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals(io.ui(), """
            Please enter room name. Input must:
                - Not be empty.
                - Be fewer than 20 characters in length.
                - Be unique.
        """.trimIndent())
    }

    @Test
    fun `when user enters empty input, then UI shows input cannot be empty`() = runTest {
        val io = StubIo()
        val job = backgroundScope.launch {
            RoomJourney(stubRoomRepo, stubBedRepo, io).process()
        }
        io.enterUserInput("")
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals(io.ui(), """
                Input cannot be empty. 
                Press any key to try again.
            """.trimIndent())
    }

    @Test
    fun `when user enters whitespace, then UI shows input cannot be empty`() = runTest {
        val io = StubIo()
        val job = backgroundScope.launch {
            RoomJourney(stubRoomRepo, stubBedRepo, io).process()
        }
        io.enterUserInput("  \t")
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals(io.ui(), """
                Input cannot be empty. 
                Press any key to try again.
            """.trimIndent())
    }

    @Test
    fun `when user enters very long string, then UI shows max characters exceeded`() = runTest {
        val io = StubIo()
        val job = backgroundScope.launch {
            RoomJourney(stubRoomRepo, stubBedRepo, io).process()
        }
        io.enterUserInput("12345678912345678912345")
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals("""
            Input '12345678912345678912345' is longer than 20 characters.
            Press any key to try again.
        """.trimIndent(), io.ui(),)
    }

    @Test
    fun `when user enters valid string, then progress to choose bed state`() = runTest {
        val io = StubIo()
        val job = backgroundScope.launch {
            RoomJourney(stubRoomRepo, stubBedRepo, io).process()
        }
        stubBedRepo.addBed("Deluxe")
        io.enterUserInput("Sexy")
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals("""
            Please select a bed type:

            1: Deluxe
        """.trimIndent(), io.ui())
    }
//
//    @Test
//    fun `when user enters valid string and does not validate, then return to prior state`() = runTest {
//        val io = StubIo()
//        val job = backgroundScope.launch {
//            BedJourney(stubBedRepo, io).process()
//        }
//        io.enterUserInput("Quite nice")
//        advanceTimeBy(1.seconds)
//        io.enterUserInput("n")
//        advanceTimeBy(1.seconds)
//        job.cancelAndJoin()
//
//        assertEquals("""
//            Please enter new bed type. Input must:
//                - Not be empty.
//                - Be fewer than 20 characters in length.
//                - Be unique.
//        """.trimIndent(), io.ui(), )
//    }
//
//    @Test
//    fun `when user enters valid string and validates, then add new bed`() = runTest {
//        val io = StubIo()
//        val job = backgroundScope.launch {
//            BedJourney(stubBedRepo, io).process()
//        }
//        io.enterUserInput("Quite nice")
//        advanceTimeBy(1.seconds)
//        io.enterUserInput("y")
//        advanceTimeBy(1.seconds)
//        job.cancelAndJoin()
//
//        assertEquals(1, stubBedRepo.getAll().size)
//    }
}