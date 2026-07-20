package io.github.spencerduberry.hotelreservation.bed

interface BedTypeRepository {
    fun addBed(newBed: Bed);
    fun removeBed(bed: Bed);
    fun getAll(): List<Bed>
}