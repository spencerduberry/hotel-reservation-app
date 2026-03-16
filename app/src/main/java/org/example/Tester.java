package org.example;

import org.Booking;
import org.CustomInput;
import org.InMemoryRoomTypeRepository;
import org.RoomService;
import org.RoomTypeRepository;
import org.ScannerCustomInput;
import java.util.Comparator;
import java.util.TreeSet;

public class Tester {

	public static void main (String[] args)
	{
		CustomInput input = new ScannerCustomInput();
		TreeSet<Booking> bookingTree = new TreeSet<>(new LastNameComparator());
		RoomTypeRepository testRepo = new InMemoryRoomTypeRepository();
		RoomService service = new RoomService(input, testRepo);

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
//			case 1:
//				Booking.reservation(roomMap, bookingTree);
//				break;
			case 2:
				Booking.findBooking(bookingTree, input);
				break;
//			case 3:
//				Room.revenueReport(roomMap);
//				break;
			case 4:
				Booking.printSortedBookings(bookingTree);
				break;
//			case 5:
//				Room.roomOccupancy(roomMap, input);
//				break;
			case 6:
			    service.addRoomType();
				break;
			case 7:
			    testRepo.removeRoom(input);
				break;
			case 9:
				System.out.println(testRepo.getAll());

			default:
				if(choice!=10) System.out.println ("Unknown option");
			}
		} while (choice !=10);
	}

	static class LastNameComparator implements Comparator<Booking>
	{
		public int compare(Booking booking1, Booking booking2)
		{
			return booking1.getLastName().compareTo(booking2.getLastName());
		}
	}
}
