package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.ai.AddBedFlow
import io.github.spencerduberry.hotelreservation.utils.FakeScannerCustomInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlinx.coroutines.test.runTest

class AddBedFlowTest {
    private val existingBeds = InMemoryBedTypeRepository()
    private val workflow = AddBedFlow(existingBeds)
    private val fakeInput = FakeScannerCustomInput()

    @Test
    fun `when valid bed added then save to repository`() = runTest {
        fakeInput.enqueueInput("Queen")

        workflow.run(fakeInput)

        val savedBeds = existingBeds.getAll()
        assertEquals("Queen", savedBeds.first())
    }

}