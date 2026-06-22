package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.InputNamePhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.SuccessPhase
import io.github.spencerduberry.hotelreservation.bed.AddBedViewState.ValidationErrorPhase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test

class EvaluateBedInputTest {
    val bed = Bed("Deluxe")
    val existingBeds: List<Bed> = listOf(bed)

    @Test
    fun `when empty String received then return empty validation error`() {
        val result = evaluateBedInput(InputNamePhase, "", existingBeds)
        val errorState = assertInstanceOf(ValidationErrorPhase::class.java, result)

        assertEquals("Bed name cannot be empty.", errorState.errorReason)
    }

    @Test
    fun `when String longer than 20 characters received the return length error`() {
        val result = evaluateBedInput(InputNamePhase, "123456789123456789123", existingBeds)
        val errorState = assertInstanceOf(ValidationErrorPhase::class.java, result)

        assertEquals("Bed name cannot exceed 20 characters.", errorState.errorReason)
    }

    @Test
    fun `when bed name received matches existing bed name then return existing bed error`() {
        val result = evaluateBedInput(InputNamePhase, "Deluxe", existingBeds)
        val errorState = assertInstanceOf(ValidationErrorPhase::class.java, result)

        assertEquals("A bed type with that name already exists.", errorState.errorReason)
    }

    @Test
    fun `when bed name received matches existing bed name and case is different then return existing bed error`() {
        val result = evaluateBedInput(InputNamePhase, "deluxe", existingBeds)
        val errorState = assertInstanceOf(ValidationErrorPhase::class.java, result)

        assertEquals("A bed type with that name already exists.", errorState.errorReason)
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