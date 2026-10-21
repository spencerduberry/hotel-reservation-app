package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.BedTypeRepository
import io.github.spencerduberry.hotelreservation.bed.IO
import io.github.spencerduberry.hotelreservation.room.AddRoomState.AddNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.AddRateState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.ChooseBedState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.EnterNewRoomState
import io.github.spencerduberry.hotelreservation.room.AddRoomState.InvalidBed
import io.github.spencerduberry.hotelreservation.room.AddRoomState.None
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class RoomJourneyTest {
    private lateinit var io: StubIo

    val stubRoomRepo = object : RoomTypeRepository {
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

    val stubBedRepo = object : BedTypeRepository {
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

        fun ui(): String = output.last()
    }

    @BeforeEach
    fun setup() {
        io = StubIo()
    }

    val defaultContext: AddRoomContext = AddRoomContext(
        stubRoomRepo.getAll(),
        stubBedRepo.getAll(),
        "",
        "Room"
    )

    private fun TestScope.startJourney(
        state: AddRoomState = EnterNewRoomState(
            None,
            AddRoomContext(
                stubRoomRepo.getAll(),
                stubBedRepo.getAll(),
                ""
            )
        )
    ) = backgroundScope.launch {
        RoomJourney(stubRoomRepo, stubBedRepo, io, state).process()
    }

    private fun TestScope.enterInputs(vararg inputs: String) {
        for (input in inputs) {
            io.enterUserInput(input)
            advanceTimeBy(1.seconds)
        }
    }

    private fun generateRoomState(
        stateFactory: (previous: AddRoomState, context: AddRoomContext) -> AddRoomState = ::EnterNewRoomState,
        context: AddRoomContext,
        previousState: AddRoomState = None
    ): AddRoomState {
        return stateFactory(previousState, context)
    }

    @Test
    fun `when starting room journey, then UI shows input options`() = runTest {
        val job = startJourney()
        advanceTimeBy(1.seconds)
        job.cancelAndJoin()

        assertEquals(
            io.ui(), """
            Please enter room name. Input must:
                - Not be empty.
                - Be fewer than 20 characters in length.
                - Be unique.
        """.trimIndent()
        )
    }

    @Test
    fun `when user enters empty input, then UI shows input cannot be empty`() = runTest {
        val job = startJourney()
        enterInputs("")
        job.cancelAndJoin()

        assertEquals(
            io.ui(), """
                Input cannot be empty. 
                Press any key to try again.
            """.trimIndent()
        )
    }

    @Test
    fun `when user enters whitespace, then UI shows input cannot be empty`() = runTest {
        val job = startJourney()
        enterInputs("  \t")
        job.cancelAndJoin()

        assertEquals(
            io.ui(), """
                Input cannot be empty. 
                Press any key to try again.
            """.trimIndent()
        )
    }

    @Test
    fun `when user enters very long string, then UI shows max characters exceeded`() = runTest {
        val job = startJourney()
        enterInputs("12345678912345678912345")
        job.cancelAndJoin()

        assertEquals(
            """
            Input '12345678912345678912345' is longer than 20 characters.
            Press any key to try again.
        """.trimIndent(),
            io.ui(),
        )
    }

    @Test
    fun `when user enters valid string, then progress to choose bed state`() = runTest {
        stubBedRepo.addBed("Deluxe")

        val job = startJourney()
        enterInputs("Sexy")
        job.cancelAndJoin()

        assertEquals(
            """
            Please select a bed type:

            1. Deluxe
        """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is add bed stage, when user enters invalid key, then display error message`() = runTest {
        stubBedRepo.addBed("Deluxe")

        val job = startJourney(
            generateRoomState(::ChooseBedState, defaultContext)
        )
        enterInputs("2")
        job.cancelAndJoin()

        assertEquals(
            """
                Please choose a valid bed type. Press enter to continue.
            """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is add bed stage, when user enters valid key, then progress to add rate`() = runTest {
        stubBedRepo.addBed("Deluxe")

        val job = startJourney(
            generateRoomState(::ChooseBedState, AddRoomContext(
                stubRoomRepo.getAll(),
                stubBedRepo.getAll(),
                ""
            ))
        )
        enterInputs("1")
        job.cancelAndJoin()

        assertEquals(
            """
            Please enter the nightly rate for the room. 
            Value entered must be a positive integer.
        """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is invalid key, when user input not new line, then repeat error message`() = runTest {
        stubBedRepo.addBed("Deluxe")

        val job = startJourney(
            generateRoomState(::InvalidBed, AddRoomContext(
                stubRoomRepo.getAll(),
                stubBedRepo.getAll(),
                ""
            ))
        )
        enterInputs("fluff")
        job.cancelAndJoin()

        assertEquals(
            """
                Please choose a valid bed type. Press enter to continue.
            """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is invalid key, when user inputs new line, then revert to prior state`() = runTest {
        stubBedRepo.addBed("Deluxe")

        val job = startJourney(
            generateRoomState(::InvalidBed, AddRoomContext(
                stubRoomRepo.getAll(),
                stubBedRepo.getAll(),
                ""
            ))
        )
        enterInputs("\n")
        job.cancelAndJoin()

        assertEquals(
            """
            Please select a bed type:

            1. Deluxe
        """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is add rate, when user inputs negative integer, then negative integer error`() = runTest {
        val job = startJourney(
            AddRateState(None, defaultContext, "")
        )
        enterInputs("-1")
        job.cancelAndJoin()

        assertEquals(
            """
                Input must be a positive integer.
                Press any key to try again.
            """.trimIndent(), io.ui()
        )
    }

    @Test
    fun `given state is add rate, when user inputs positive integer, then progress to AddNewRoomState`() = runTest {
        val job = startJourney(
            AddRateState(None, defaultContext, "")
        )
        enterInputs("1")
        job.cancelAndJoin()

        assertEquals(
            "hiya", io.ui()
        )
    }
}