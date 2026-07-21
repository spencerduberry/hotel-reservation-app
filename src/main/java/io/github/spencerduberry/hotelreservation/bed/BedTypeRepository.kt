package io.github.spencerduberry.hotelreservation.bed

interface BedTypeRepository {
    fun addBed(newBed: String);
    fun removeBed(bed: String);
    fun getAll(): List<String>
    fun setAll(beds: List<String>)
}