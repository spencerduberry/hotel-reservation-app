package io.github.spencerduberry.hotelreservation.bed

import io.github.spencerduberry.hotelreservation.utils.Context

data class AddBedContext(
    val bedTypes: List<String>,
    override val previousInput: String
): Context