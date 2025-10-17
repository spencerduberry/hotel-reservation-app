package org;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import org.Booking;
import org.Room;


public class Tester {
	
	/*public static void main (String[] args)
	{

		Map<String, Room> roomMap = new HashMap<>();
		TreeSet<Booking> bookingTree = new TreeSet<>(new LastNameComparator());
		RoomTypeRepository testRepo = new InMemoryRoomTypeRespository();

		roomMap.put("standardtwin", new Room ("Standard (Twin)", "Comfortable and budget friendly "
				+ "accommodation", 1, 250, "Twin", 250, 1000));
		roomMap.put("standarddouble", new Room ("Standard (Double)", "Comfortable and budget friendly "
				+ "accommodation", 1, 250, "Double", 250, 1000));
		roomMap.put("deluxe", new Room ("Deluxe", "Enhanced comfort and additional "
				+ "space", 251, 500, "Queen-size", 250, 1200));
		roomMap.put("superiorking", new Room ("Superior (King)", "Luxury and premium comfort "
				+ "services", 501, 530, "king-size", 30, 1800));
		roomMap.put("superiorqueen", new Room ("Superior (Queen)", "Luxury and premium comfort "
				+ "services", 501, 530, "Queen-size", 30, 1800));
		
		Room standardTwin = roomMap.get("standardtwin");
		Room standardDouble = roomMap.get("standarddouble");
		Room superiorKing = roomMap.get("superiorking");
		Room superiorQueen = roomMap.get("superiorqueen");
		standardTwin.setAssociatedObject(standardDouble);
		standardDouble.setAssociatedObject(standardTwin);
		superiorKing.setAssociatedObject(superiorQueen);
		superiorQueen.setAssociatedObject(superiorKing);
		
		int choice;
		do
		{
			CustomInput input = new ScannerCustomInput();
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
				Booking.reservation(roomMap, bookingTree);
				break;
			case 2:
				Booking.findBooking(bookingTree, input);
				break;
			case 3:
				Room.revenueReport(roomMap);
				break;
			case 4:
				Booking.printSortedBookings(bookingTree);	
				break;
			case 5:
				Room.roomOccupancy(roomMap, input);	
				break;
			case 6:
			    Room newRoom = Room.createRoom(input);
				testRepo.addRoom(newRoom);
				break;
			case 7:
			    testRepo.removeRoom(input);
				break;
			case 8: 
				testRepo.getRoom(input);
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
	}*/
}
