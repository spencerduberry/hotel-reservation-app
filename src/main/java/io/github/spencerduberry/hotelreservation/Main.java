package io.github.spencerduberry.hotelreservation;

import io.github.spencerduberry.hotelreservation.booking.BookingAuthenticator;
import io.github.spencerduberry.hotelreservation.booking.BookingRepository;
import io.github.spencerduberry.hotelreservation.booking.BookingService;
import io.github.spencerduberry.hotelreservation.booking.InMemoryBookingRepository;
import io.github.spencerduberry.hotelreservation.room.InMemoryRoomTypeRepository;
import io.github.spencerduberry.hotelreservation.room.Room;
import io.github.spencerduberry.hotelreservation.room.RoomService;
import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository;
import io.github.spencerduberry.hotelreservation.utils.CustomInput;
import io.github.spencerduberry.hotelreservation.utils.ScannerCustomInput;

public class Main {

	public static void main (String[] args)
	{
		CustomInput input = new ScannerCustomInput();
		RoomTypeRepository roomRepo = new InMemoryRoomTypeRepository();
		BookingRepository bookingRepo = new InMemoryBookingRepository();
		BookingAuthenticator authenticator = new BookingAuthenticator();
		RoomService roomService = new RoomService(input, roomRepo);
		BookingService bookingService = new BookingService(input, bookingRepo, authenticator, roomRepo);

		Room seedRoom = Room.builder()
				.type("deluxe")
				.description("juicy")
				.minRoomNumber(1)
				.maxRoomNumber(60)
				.bedType("raggedy")
				.roomTypeTotal(50)
				.rate(60)
				.build();

		roomRepo.addRoom(seedRoom);

		int choice;
		do
		{
			System.out.println ("1: Make reservation");
			System.out.println ("2: Display reservation details");
			System.out.println ("3: Generate revenue report");
			System.out.println ("4: Sort reservations");
			System.out.println ("5: Display available rooms");
			System.out.println ("6: Add new room type");
			System.out.println ("7: Delete room type");
			System.out.println ("8: Inspect room details");
			System.out.println ("9: Show all available room types");
			System.out.println ("10: Close program");

			System.out.println ("please select an operation");
			choice= input.inputInt();

			switch (choice)
			{
			case 1:
				bookingService.addBooking();
				break;
//			case 2:
//				Booking.findBooking(bookingTree, input);
//				break;
//			case 3:
//				Room.revenueReport(roomMap);
//				break;
//			case 4:
//				Booking.printSortedBookings(bookingTree);
//				break;
//			case 5:
//				Room.roomOccupancy(roomMap, input);
//				break;
			case 6:
			    roomService.addRoomType();
				break;
			case 7:
			    roomRepo.removeRoom(input);
				break;
			case 9:
				System.out.println(roomRepo.getAll());

			default:
				if(choice!=10) System.out.println ("Unknown option");
			}
		} while (choice !=10);
	}

//	static class LastNameComparator implements Comparator<Booking>
//	{
//		public int compare(Booking booking1, Booking booking2)
//		{
//			return booking1.getLastName().compareTo(booking2.getLastName());
//		}
//	}
}
