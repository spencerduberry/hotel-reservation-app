import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;


public class Booking {

	private String firstName;
	private String lastName;
	private int lengthOfStay;
	private int roomNumber;
	private Room room;

	public Booking (String firstNameIn, String lastNameIn, int lengthOfStayIn, int roomNumberIn, Room room)
	{
	this.firstName=firstNameIn;
	this.lastName=lastNameIn;
	this.lengthOfStay=lengthOfStayIn;
	this.roomNumber=roomNumberIn;
	this.room = room;
	this.room.decrementRemainingRooms();
	this.room.incrementTotalBooked();
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getRoomType()
	{
		return room.getType();
	}
	
	public Room getRoom()
	{
		return room;
	}
	
	public int getRoomNumber()
	{
		return roomNumber;
	}
	
	public String toString()
	{
		return "First name: " + firstName + "\n Last name: " + lastName +
				"\n Length of stay: " + lengthOfStay + "\n Room Reserved: " +
				roomNumber + "\n Room type: " + room;
	}
	
	public static void reservation(Map<String, Room> map, TreeSet<Booking> set)
	{
		int counter =3;
		String nextBooking = "y";
		Scanner sc = new Scanner(System.in);
		
		while (counter>0 && nextBooking.equalsIgnoreCase ("y"))
			{
			String roomType = getRoomType1(map);
			Room roomDetails = map.get(roomType);
			boolean vacancy = availabilityChecker(roomDetails);
			
			if (vacancy)
			{
				int roomNumber = getRoomNumber(roomDetails, set);
				System.out.println ("Please enter customer's first name:");
				String firstName=sc.nextLine();
				System.out.println ("Please enter customer's last name:");
				String lastName=sc.nextLine();
				int lengthOfStay=getIntInput(sc, "Please enter the length of the stay:");
				sc.nextLine();
				Booking b = new Booking(firstName, lastName, lengthOfStay, roomNumber,
							roomDetails);
				set.add(b);
				counter--;
				
				do 
				{
					System.out.println("Would you like to make another booking (y/n)?");
					nextBooking = sc.nextLine();
				} while (!(nextBooking.equalsIgnoreCase("y")) && !(nextBooking.equalsIgnoreCase("n")));
			}
			else
			{
				System.out.println ("No vacancy in this room");
				System.out.println();
				break;
			}
		}
	}
	
	public static boolean availabilityChecker(Room roomType)
	{

			if (roomType.getNumberAvailable()>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		
	}
	
	public static int getRoomNumber(Room roomType, TreeSet<Booking> set)
	{
		boolean validRoomNumber = false;
		Scanner sc = new Scanner(System.in);
		int roomNumber=getIntInput(sc, "Please enter a valid room number:");
		sc.nextLine();
		
		while (validRoomNumber == false)
		{
			if (!roomNumberValidityChecker(roomType, roomNumber))
			{
				roomNumber=getIntInput(sc, "Please enter a valid room number:");
				sc.nextLine();
			}
			else if (!doubleBookingChecker(set, roomNumber))
			{
				roomNumber=getIntInput(sc, "Room is already booked. Please enter a different room number.");
				sc.nextLine();
			}
			else
			{
				validRoomNumber = true;
			}
		}
		return roomNumber;
	}
	
	public static int getIntInput(Scanner scanner, String prompt)
	{
		int input = 0;
		boolean isValidInput = false;
		
		while (!isValidInput)
		{
			System.out.print(prompt);
		if (scanner.hasNextInt())
		{
			input = scanner.nextInt();
			scanner.nextLine();
			isValidInput = true;
		}
		else
		{
			System.out.println("Invalid input. Please enter an integer.");
			scanner.next();
		}
		}
		return input;
	}
	
	public static boolean roomNumberValidityChecker (Room roomType, int roomNumber)
	{
		if (roomNumber>=roomType.getMinRoomNumber() && roomNumber<=roomType.getMaxRoomNumber())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean doubleBookingChecker (TreeSet<Booking> bookingTree, int roomNumber)
	{
		boolean roomAvailable = true;
		for (Booking obj : bookingTree)
		{
			if (obj.getRoomNumber() == (roomNumber))
			{
				roomAvailable = false;
				break;
			}
		}
		return roomAvailable;
	}
	
	public static String getRoomType1(Map<String, Room> map)
	{
		boolean validRoom = false;
		Scanner sc = new Scanner(System.in);
		System.out.println ("Please enter the room type:");
		String name=sc.nextLine();
		String nameToLowerCase = name.toLowerCase();
		while (validRoom == false)
		{
			validRoom = roomValidityChecker(map, nameToLowerCase);
			if (validRoom == false)
			{
				System.out.println ("Please enter valid room type:");
				name=sc.nextLine();
				nameToLowerCase = name.toLowerCase();
			}
		}
		return nameToLowerCase;
	}
	
	public static boolean roomValidityChecker(Map<String, Room> map, String roomType)
	{
		if (map.containsKey(roomType))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void findBooking(TreeSet<Booking>bookingTree)
	{
		Scanner sc3 = new Scanner(System.in);
		boolean found = false;
		System.out.println("Enter the last name of the customer");
		String lastName1 = sc3.nextLine();
		for (Booking obj : bookingTree)
		{
			if (obj.getLastName().equalsIgnoreCase(lastName1))
			{
				System.out.println(obj);
				found = true;
			}
		}
		if(!found)
		{
			System.out.println("No booking under that name could be found");
		}
	}
	
	public static <T> void printSortedBookings(TreeSet<T> bookingTree)
	{
		for (T node : bookingTree)
		{
		System.out.println(node);
		}
	}
	
}
