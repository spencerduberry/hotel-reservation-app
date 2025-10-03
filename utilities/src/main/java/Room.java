package utilities.src.main.java;

import java.util.Map;
import java.util.Scanner;

public class Room {

	private String type;
	int minRoomNumber;
	int maxRoomNumber;
	private String bedType;
	private int capacity;
	private int numberAvailable;
	private int rate;
	private int totalBooked = 0;
	private Room associatedObject;
	
	public Room (String typeIn, String descriptionIn, int minRoomNumberIn, int maxRoomNumberIn, String bedTypeIn, int capacityIn, int numberAvailableIn, int rateIn)
	{
		this.type = typeIn;
		this.minRoomNumber = minRoomNumberIn;
		this.maxRoomNumber = maxRoomNumberIn;
		this.bedType = bedTypeIn;
		this.capacity = capacityIn;
		this.numberAvailable = numberAvailableIn;
		this.rate = rateIn;
	}
		
	public static Room addRoom()
	{
		try (Scanner sc = new Scanner(System.in)) {
			String roomName = sc.nextLine();
			String roomDescription = sc.nextLine();
			int minimumRoomNumber = sc.nextInt();
			int maximumRoomNumber = sc.nextInt();
			int capacity = sc.nextInt();
			int numAvailable = sc.nextInt();
			String bedType = sc.nextLine();
			int rate = sc.nextInt();
			Room newRoom = new Room(roomName, roomDescription, minimumRoomNumber, maximumRoomNumber, bedType, capacity, numAvailable, rate);

			return newRoom;
		}
	}

	public int getMinRoomNumber()
	{
		return minRoomNumber;
	}
	
	public int getMaxRoomNumber()
	{
		return maxRoomNumber;
	}
	
	public void incrementTotalBooked()
	{
		totalBooked++;
	}
	
	public int getTotalBooked()
	{
		return totalBooked;
	}
	
	public void decrementRemainingRooms()
	{
		numberAvailable--;
		if (associatedObject != null)
		{
			associatedObject.decrementAssociatedObject();
		}
	}
	
	public void decrementAssociatedObject()
	{
		numberAvailable--;
	}
	
	public void setAssociatedObject(Room associatedObject)
	{
		this.associatedObject = associatedObject;
	}
	
	public String getType()
	{
		return type;
	}
	
	public int getCapacity()
	{
		return capacity;
	}
	
	public int getNumberAvailable()
	{
		return numberAvailable;
	}
	
	public int getRate()
	{
		return rate;
	}
	
	public String toString()
	{
		return type + "\n Bed Type: " +
				bedType;
	}
	
	public static void revenueReport(Map<String, Room> map)
	{
		for (Map.Entry<String, Room> entry : map.entrySet())
		{
			Room obj = entry.getValue();
			System.out.println(obj.getType());
			System.out.println("Number booked: " + obj.getTotalBooked());
			System.out.println("Revenue: £" + obj.getTotalBooked()*obj.getRate());
			System.out.println();
		}
	}
	
	public static void roomOccupancy(Map<String, Room> map)
	{
		{
		Scanner sc2 = new Scanner(System.in);
		System.out.println ("Enter valid room type");
		String roomType=sc2.nextLine();
		String roomTypeLowerCase = roomType.toLowerCase();
		boolean valid = Booking.roomValidityChecker(map, roomTypeLowerCase);
		while (!valid)
		{
			System.out.println ("Enter valid room type");
			roomType=sc2.nextLine();
			valid = Booking.roomValidityChecker(map, roomType);
		}
		Room room = map.get(roomType);
		System.out.println("Rooms Booked: " + room.getTotalBooked());
		System.out.println("Remaining rooms: " + room.getNumberAvailable());
		System.out.println("Total Rooms: " + room.getCapacity());
		System.out.println();
		}
	}
}
