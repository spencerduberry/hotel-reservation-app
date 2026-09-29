package io.github.spencerduberry.hotelreservation.utils

import io.github.spencerduberry.hotelreservation.bed.AddBedState

interface State {
    fun process(input: String): State
    fun ui(): String
}