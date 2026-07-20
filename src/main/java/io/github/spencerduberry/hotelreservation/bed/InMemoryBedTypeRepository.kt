package io.github.spencerduberry.hotelreservation.bed

class InMemoryBedTypeRepository(initialBeds: List<Bed> = listOf()) : BedTypeRepository{

    private var bedTypes: List<Bed> = initialBeds

    override fun addBed(newBed: Bed) {
        this.bedTypes += newBed
    }

    override fun removeBed(bed: Bed) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<Bed> {
        return bedTypes
    }
}