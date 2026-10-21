package io.github.spencerduberry.hotelreservation.utils

interface Context {
    val type: String
    val previousInput: String
}