package io.github.spencerduberry.hotelreservation.utils


interface State {
    fun process(input: String): State
    fun ui(): String
}