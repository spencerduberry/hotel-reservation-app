package io.github.spencerduberry.hotelreservation.bed

data class BedContext(
    val bedTypes: List<String>,
    val previousInput: String
)