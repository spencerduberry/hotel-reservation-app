package org;
import java.util.Map;

public class Room {

	private final String type;
	private final String description;
    int minRoomNumber;
	int maxRoomNumber;
	private final String bedType;
	private final int roomTypeTotal;
	private final int rate;
	private int totalBooked = 0;

	private Room (String typeIn, String descriptionIn, int minRoomNumberIn, int maxRoomNumberIn, String bedTypeIn, int roomTypeTotalIn, int rateIn)
	{
		this.type = typeIn;
		this.description = descriptionIn;
        this.minRoomNumber = minRoomNumberIn;
		this.maxRoomNumber = maxRoomNumberIn;
		this.bedType = bedTypeIn;
		this.roomTypeTotal = roomTypeTotalIn;
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

	public String getType()
	{
		return type;
	}
	
	public int getRoomTypeTotal()
	{
		return roomTypeTotal;
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
		System.out.println("Total Rooms: " + room.getRoomTypeTotal());
		System.out.println();
		}
	}
    public static class RoomBuilder {
        String type;
        String description;
        int minRoomNumber;
        int maxRoomNumber;
        String bedType;
        int roomTypeTotal;
        int rate;

        public RoomBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public RoomBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public RoomBuilder setMinRoomNumber(int minRoomNumber) {
            this.minRoomNumber = minRoomNumber;
            return this;
        }

        public RoomBuilder setMaxRoomNumber(int maxRoomNumber) {
            this.maxRoomNumber = maxRoomNumber;
            return this;
        }

        public RoomBuilder setBedType(String bedType) {
            this.bedType = bedType;
            return this;
        }

        public RoomBuilder setRoomTypeTotal(int roomTypeTotal) {
            this.roomTypeTotal = roomTypeTotal;
            return this;
        }

        public RoomBuilder setRate(int rate) {
            this.rate = rate;
            return this;
        }

        public Room build() {
            return new Room(type, description, minRoomNumber, maxRoomNumber, bedType, roomTypeTotal, rate);
        }
    }
}
