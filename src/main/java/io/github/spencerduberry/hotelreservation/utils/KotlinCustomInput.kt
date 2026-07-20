package io.github.spencerduberry.hotelreservation.utils

class KotlinCustomInput: CustomInput {
    override fun inputString(): String {
        val input: String = readln()

        return input
    }

    override fun inputInt(): Int {
        val input: Int = readln().toInt()

        return input
    }
}