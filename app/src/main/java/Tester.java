import java.util.*;


public class Tester {
	private static Map<String, Room> roomMap = new HashMap<>();
	private static TreeSet<Booking> bookingTree = new TreeSet<>(new LastNameComparator());
	
	public static void main (String[] args)
	{
		roomMap.put("standardtwin", new Room ("Standard (Twin)", "Comfortable and budget friendly "
				+ "accommodation", 1, 250, "Twin", 250, 250, 1000));
		roomMap.put("standarddouble", new Room ("Standard (Double)", "Comfortable and budget friendly "
				+ "accommodation", 1, 250, "Double", 250, 250, 1000));
		roomMap.put("deluxe", new Room ("Deluxe", "Enhanced comfort and additional "
				+ "space", 251, 500, "Queen-size", 250, 250, 1200));
		roomMap.put("superiorking", new Room ("Superior (King)", "Luxury and premium comfort "
				+ "services", 501, 530, "king-size", 30, 30, 1800));
		roomMap.put("superiorqueen", new Room ("Superior (Queen)", "Luxury and premium comfort "
				+ "services", 501, 530, "Queen-size", 30, 30, 1800));
		
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
			Scanner sc = new Scanner(System.in);
			System.out.println ("1: Make reservation");
			System.out.println ("2: Display reservation details");
			System.out.println ("3: Generate revenue report");
			System.out.println ("4: Sort reservations");
			System.out.println ("5: Display available rooms");
			System.out.println ("6: Close program");
			
			choice= Booking.getIntInput(sc, "Please select an operation:");
			
			switch (choice)
			{
			case 1: 
				Booking.reservation(roomMap, bookingTree);
				break;
			case 2:
				Booking.findBooking(bookingTree);
				break;
			case 3:
				Room.revenueReport(roomMap);
				break;
			case 4:
				Booking.printSortedBookings(bookingTree);	
				break;
			case 5:
				Room.roomOccupancy(roomMap);	
				break;
			default:
				if(choice!=6) System.out.println ("Unknown option");
			}
		} while (choice !=6);
	}
	
	static class LastNameComparator implements Comparator<Booking>
	{
		public int compare(Booking booking1, Booking booking2)
		{
			return booking1.getLastName().compareTo(booking2.getLastName());
		}
	}
}
