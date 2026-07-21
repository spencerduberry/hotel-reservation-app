package io.github.spencerduberry.hotelreservation.bed

class InMemoryBedTypeRepository(initialBeds: List<String> = listOf()) : BedTypeRepository{

    private var bedTypes: List<String> = initialBeds

    override fun addBed(newBed: String) {
        this.bedTypes += newBed
    }

    override fun removeBed(bed: String) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<String> {
        return bedTypes
    }

    override fun setAll(beds: List<String>) {
        TODO("Not yet implemented")
    }
}