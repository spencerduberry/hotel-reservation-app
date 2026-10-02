package io.github.spencerduberry.hotelreservation.utils

import io.github.spencerduberry.hotelreservation.bed.AddBedContext
import io.github.spencerduberry.hotelreservation.utils.ValidationKey.EMPTY_INPUT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ValidatorTest {
    @Test
    fun `when empty input then return empty input predicate`() {
        val bedTypes: List<String> = emptyList()
        val emptyContext = AddBedContext(bedTypes, "")
        assertEquals({ input: String -> input.isBlank() }, getValidationRule(EMPTY_INPUT, emptyContext))
    }

}