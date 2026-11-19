package io.github.spencerduberry.hotelreservation.room

import io.github.spencerduberry.hotelreservation.bed.IO
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.ChooseRoomState
import io.github.spencerduberry.hotelreservation.room.RemoveRoomState.None
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

class RemoveRoomJourneyTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    class AddRoomJourneyTest {
        private lateinit var io: StubIo

        val mockRoom: Room = Room.builder().name("Deluxe").build()

        val stubRoomRepo = object : RoomTypeRepository {
            private val types = mutableListOf<Room>(mockRoom)

            override fun addRoom(newRoom: Room) {
                types.add(newRoom)
            }

            override fun removeRoom(room: Room) {
                types.remove(room)
            }

            override fun getAll(): List<Room> =
                ArrayList(types)

            override fun setAll(rooms: List<Room>) {
                types.clear()
                types.addAll(rooms)
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

        val defaultContext = RemoveRoomContext(
            stubRoomRepo.getAll(),
            ""
        )

        private fun TestScope.startJourney(
            state: RemoveRoomState = ChooseRoomState(
                None,
                RemoveRoomContext(
                    stubRoomRepo.getAll(),
                    ""
                )
            )
        ) = backgroundScope.launch {
            RemoveRoomJourney(stubRoomRepo, io).process()
        }

        private fun TestScope.enterInputs(vararg inputs: String) {
            for (input in inputs) {
                io.enterUserInput(input)
                advanceTimeBy(1.seconds)
            }
        }

        @Test
        fun `when starting remove room journey, then UI shows removal options`() = runTest {
            val job = startJourney()
            advanceTimeBy(1.seconds)
            job.cancelAndJoin()

            assertEquals(
                "Please select a room type:\n    1. Deluxe", io.ui()
            )
        }

        @Test
        fun `when user enters invalid option, then UI shows invalid choice error`() {
            runTest {
                val job = startJourney()
                enterInputs("2")
                job.cancelAndJoin()

                assertEquals(
                    """
                Please choose a valid option.
            """.trimIndent(), io.ui()
                )
            }
        }

        @Test
        fun `when user enters valid choice, then UI shows confirmation message`() {
            runTest {
                val job = startJourney()
                enterInputs("1")
                job.cancelAndJoin()

                assertEquals(
                    """
            Are you sure you want to remove room: Deluxe?
            Press "y" to confirm, or another other key to abort.
        """.trimIndent(), io.ui()
                )
            }
        }

        @Test
        fun `when user declines confirmation, then revert to choose option state`() {
            runTest {
                val job = startJourney()
                enterInputs("1", "n")
                job.cancelAndJoin()

                assertEquals(
                    "Please select a room type:\n    1. Deluxe", io.ui()
                )
            }
        }

        @Test
        fun `when user confirms choice, then remove room from repository`() {
            runTest {
                val job = startJourney()
                enterInputs("1", "y")
                job.cancelAndJoin()

                assertEquals(
                    0, stubRoomRepo.getAll().size
                )
            }
        }
    }
}
