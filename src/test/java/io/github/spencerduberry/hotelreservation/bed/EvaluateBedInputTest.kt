package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ErrorPhase
import io.github.spencerduberry.hotelreservation.bed.BedValidationError.Empty
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class EvaluateBedInputTest {
    val bed = Bed("Deluxe")
    val existingBeds: List<Bed> = listOf(bed)

    @Test
    fun `when empty String then return empty validation error`() {
        val result = evaluateBedInput(InputNamePhase, "", existingBeds)

        assertEquals(ErrorPhase(Empty), result)
    }

    @Test
    fun `when String longer than 20 characters then return length error`() {
        val result = evaluateBedInput(InputNamePhase, "123456789123456789123", existingBeds)

        assertEquals(ErrorPhase(BedValidationError.TooLong(21)), result)
    }

    @Test
    fun `when bed name matches existing bed name then return existing bed error`() {
        val result = evaluateBedInput(InputNamePhase, "Deluxe", existingBeds)

        assertEquals(ErrorPhase(BedValidationError.AlreadyExists("Deluxe")), result)
    }

    @Test
    fun `when bed name matches existing bed name and case is different then return existing bed error`() {
        val result = evaluateBedInput(InputNamePhase, "deluxe", existingBeds)

        assertEquals(ErrorPhase(BedValidationError.AlreadyExists("deluxe")), result)
    }

    @Test
    fun `given valid bed name when trailing whitespace return success with white space trimmed`() {
        val result = evaluateBedInput(InputNamePhase, "King ", existingBeds)
        val successState = result as SuccessPhase

        assertEquals("King", successState.newBed.bedType)
    }

    @Test
    fun `when valid bed name then return success`() {
        val result = evaluateBedInput(InputNamePhase, "King", existingBeds)

        assertInstanceOf(SuccessPhase::class.java, result)
    }
}