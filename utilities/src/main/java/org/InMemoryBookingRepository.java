package org;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookingRepository implements BookingRepository{

    List<Booking> bookings = new ArrayList<>();
    public void addBooking(Booking newBooking) {
        bookings.add(newBooking);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
    }

    public List<Booking> getAll() {
        return bookings;
    }
//    public static void revenueReport(Map<String, Room> map)
//    {
//        for (Map.Entry<String, Room> entry : map.entrySet())
//        {
//            Room obj = entry.getValue();
//            System.out.println(obj.getType());
//            System.out.println("Number booked: " + obj.getTotalBooked());
//            System.out.println("Revenue: £" + obj.getTotalBooked()*obj.getRate());
//            System.out.println();
//        }
//    }

    //	public static void roomOccupancy(Map<String, Room> map, CustomInput input)
//	{
//		{
//		System.out.println ("Enter valid room type");
//		String roomType=input.inputString();
//		String roomTypeLowerCase = roomType.toLowerCase();
//		boolean valid = Booking.roomValidityChecker(map, roomTypeLowerCase);
//		while (!valid)
//		{
//			System.out.println ("Enter valid room type");
//			roomType=input.inputString();
//			valid = Booking.roomValidityChecker(map, roomType);
//		}
//		Room room = map.get(roomType);
//		System.out.println("Rooms Booked: " + room.getTotalBooked());
//		System.out.println("Total Rooms: " + room.getRoomTypeTotal());
//		System.out.println();
//		}
//	}
}
