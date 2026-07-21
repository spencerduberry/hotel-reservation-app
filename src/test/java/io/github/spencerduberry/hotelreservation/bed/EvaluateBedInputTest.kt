package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.ai.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.ai.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.ai.AddBedViewState.ErrorPhase
import io.github.spencerduberry.hotelreservation.bed.ai.BedValidationError
import io.github.spencerduberry.hotelreservation.bed.ai.BedValidationError.Empty
import io.github.spencerduberry.hotelreservation.bed.ai.evaluateBedInput
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class EvaluateBedInputTest {
    val bed = "Deluxe"
    val existingBeds: List<String> = listOf(bed)

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

        assertEquals("King", successState.newBed)
    }

    @Test
    fun `when valid bed name then return success`() {
        val result = evaluateBedInput(InputNamePhase, "King", existingBeds)

        assertInstanceOf(SuccessPhase::class.java, result)
    }
}