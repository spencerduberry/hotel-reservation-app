package io.github.spencerduberry.hotelreservation

import io.github.spencerduberry.hotelreservation.bed.AddBedFlow
import io.github.spencerduberry.hotelreservation.bed.Bed
import io.github.spencerduberry.hotelreservation.bed.InMemoryBedTypeRepository
import io.github.spencerduberry.hotelreservation.booking.BookingAuthenticator
import io.github.spencerduberry.hotelreservation.booking.BookingRepository
import io.github.spencerduberry.hotelreservation.booking.BookingService
import io.github.spencerduberry.hotelreservation.booking.InMemoryBookingRepository
import io.github.spencerduberry.hotelreservation.room.InMemoryRoomTypeRepository
import io.github.spencerduberry.hotelreservation.room.Room
import io.github.spencerduberry.hotelreservation.room.RoomService
import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository
import io.github.spencerduberry.hotelreservation.utils.CustomInput
import io.github.spencerduberry.hotelreservation.utils.KotlinCustomInput


suspend fun main(args: Array<String>) {
    val input: CustomInput = KotlinCustomInput()

    val bedRepo = InMemoryBedTypeRepository()
    val seedBed1 = Bed("Single")
    val seedBed2 = Bed("Double")
    val seedBed3 = Bed("King")
    bedRepo.addBed(seedBed1)
    bedRepo.addBed(seedBed2)
    bedRepo.addBed(seedBed3)
    val addBedFlow = AddBedFlow(bedRepo)

    val roomRepo: RoomTypeRepository = InMemoryRoomTypeRepository()
    val roomService = RoomService(input, roomRepo)
    val seedRoom = Room.builder()
        .type("deluxe")
        .description("juicy")
        .minRoomNumber(1)
        .maxRoomNumber(60)
        .bedType(seedBed1)
        .roomTypeTotal(50)
        .rate(60)
        .build()
    roomRepo.addRoom(seedRoom)

    val bookingRepo: BookingRepository = InMemoryBookingRepository()
    val authenticator = BookingAuthenticator()
    val bookingService = BookingService(input, bookingRepo, authenticator, roomRepo)

    var choice: Int
    do {
        println("1: Make reservation")
        println("2: Display reservation details")
        println("3: Generate revenue report")
        println("4: Sort reservations")
        println("5: Display available rooms")
        println("6: Add new room type")
        println("7: Delete room type")
        println("8: Inspect room details")
        println("9: Show all available room types")
        println("10: Add new bed type")
        println("11: Delete bed type")
        println("12: Close program")

        println("please select an operation")
        choice = input.inputInt()

        when (choice) {
            1 -> bookingService.addBooking()
            6 -> roomService.addRoomType()
            7 -> roomRepo.removeRoom(input)
            9 -> println(roomRepo.getAll())
            10 -> addBedFlow.run(input)
            else -> if (choice != 12) println("Unknown option")
        }
    } while (choice != 12)
}

