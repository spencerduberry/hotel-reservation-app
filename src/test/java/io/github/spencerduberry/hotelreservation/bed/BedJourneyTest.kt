//package io.github.spencerduberry.hotelreservation.bed
//
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
//
//class BedJourneyTest {
//
//    val stubBedRepo = object: BedTypeRepository {
//        private val types = mutableListOf<String>()
//
//        override fun addBed(newBed: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun removeBed(bed: String) {
//            TODO("Not yet implemented")
//        }
//
//        override fun getAll(): List<String> =
//            ArrayList(types)
//
//        override fun setAll(beds: List<String>) {
//            types.clear()
//            types.addAll(beds)
//        }
//
//    }
//
//    class StubIo : IO {
//        val lines: MutableList<String> = mutableListOf()
//        override fun readLine(): String = lines.lastOrNull().orEmpty()
//
//        override fun printLine(input: String) {
//            lines.add(input)
//        }
//    }
//
//    @Test
//    fun name() {
//        val io = StubIo()
//        BedJourney(stubBedRepo, io).process()
//        assertEquals(io.readLine(), """
//            Please enter new bed type. Input must:
//                - Not be empty.
//                - Be fewer than 20 characters in length.
//                - Be unique.
//        """.trimIndent())
//    }
//}