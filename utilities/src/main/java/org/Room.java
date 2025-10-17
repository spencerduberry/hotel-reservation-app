package org;
import java.util.Map;


public class Room {

	private String type;
	int minRoomNumber;
	int maxRoomNumber;
	private String bedType;
	private int roomTypeTotal;
	private int numberAvailable;
	private int rate;
	private int totalBooked = 0;
	private Room associatedObject;
	
	public Room (String typeIn, String descriptionIn, int minRoomNumberIn, int maxRoomNumberIn, String bedTypeIn, int roomTypeTotalIn, int rateIn)
	{
		this.type = typeIn;
		this.minRoomNumber = minRoomNumberIn;
		this.maxRoomNumber = maxRoomNumberIn;
		this.bedType = bedTypeIn;
		this.roomTypeTotal = roomTypeTotalIn;
		this.numberAvailable = roomTypeTotal;
		this.rate = rateIn;
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
	
	public int getroomTypeTotal()
	{
		return roomTypeTotal;
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
    return "Room {" +
            "type='" + type + '\'' +
            ", minRoomNumber=" + minRoomNumber +
            ", maxRoomNumber=" + maxRoomNumber +
            ", bedType='" + bedType + '\'' +
            ", rate=" + rate +
            '}';
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
	
	public static void roomOccupancy(Map<String, Room> map, CustomInput input)
	{
		{
		System.out.println ("Enter valid room type");
		String roomType=input.inputString();
		String roomTypeLowerCase = roomType.toLowerCase();
		boolean valid = Booking.roomValidityChecker(map, roomTypeLowerCase);
		while (!valid)
		{
			System.out.println ("Enter valid room type");
			roomType=input.inputString();
			valid = Booking.roomValidityChecker(map, roomType);
		}
		Room room = map.get(roomType);
		System.out.println("Rooms Booked: " + room.getTotalBooked());
		System.out.println("Remaining rooms: " + room.getNumberAvailable());
		System.out.println("Total Rooms: " + room.getroomTypeTotal());
		System.out.println();
		}
	}
}
