package org.example;

import java.util.List;
import java.util.Map;

public class BookingService {

    private final CustomInput inputSource;
    private final BookingRepository bookingRepo;

    BookingAuthenticator authenticator;

    public BookingService(CustomInput inputSource, BookingRepository bookingRepo, BookingAuthenticator authenticator) {
        this.inputSource = inputSource;
        this.bookingRepo = bookingRepo;
        this.authenticator = authenticator;
    }

//    public void addBooking() {
//        System.out.println("Please enter the customer's first name.");
//        String firstName = inputSource.inputString();
//
//        System.out.println("Please enter the customer's surname.");
//        String surname = inputSource.inputString();
//
//        System.out.println("Please enter the length of the stay.");
//
//        System.out.println("Please enter the room number.");
//
//        System.out.println("Please enter the type of room.");
//        String roomType = inputSource.inputString();
//
//        bookingRepo.addBooking(newBooking);
//    }

//    public static void makeReservation(Map<String, Room> map, TreeSet<Booking> set)
//    {
//        String roomType = getRoomType1(map, sc);
//        Room roomDetails = map.get(roomType);
//        boolean vacancy = availabilityChecker(roomDetails);
//
//        if (vacancy)
//        {
//            int roomNumber = getRoomNumber(roomDetails, set);
//            System.out.println ("Please enter customer's first name:");
//            String firstName=sc.nextLine();
//            System.out.println ("Please enter customer's last name:");
//            String lastName=sc.nextLine();
//            int lengthOfStay=getIntInput(sc, "Please enter the length of the stay:");
//            sc.nextLine();
//            Booking b = new Booking(firstName, lastName, lengthOfStay, roomNumber,
//                    roomDetails);
//            set.add(b);
//        }
//        else
//        {
//            System.out.println ("No vacancy in this room");
//            System.out.println();
//            break;
//        }
//
//    }

    public static boolean availabilityChecker(Room roomType)
    {

        if (roomType.roomTypeTotal() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

//    public static int getRoomNumber(Room roomType, TreeSet<Booking> set)
//    {
//        boolean validRoomNumber = false;
//        Scanner sc = new Scanner(System.in);
//        int roomNumber=getIntInput(sc, "Please enter a valid room number:");
//        sc.nextLine();
//
//        while (validRoomNumber == false)
//        {
//            if (!roomNumberValidityChecker(roomType, roomNumber))
//            {
//                roomNumber=getIntInput(sc, "Please enter a valid room number:");
//                sc.nextLine();
//            }
//            else if (!doubleBookingChecker(set, roomNumber))
//            {
//                roomNumber=getIntInput(sc, "Room is already booked. Please enter a different room number.");
//                sc.nextLine();
//            }
//            else
//            {
//                validRoomNumber = true;
//            }
//        }
//        return roomNumber;
//    }

    public static boolean roomNumberValidityChecker (Room roomType, int roomNumber)
    {
        if (roomNumber>=roomType.minRoomNumber() && roomNumber<=roomType.maxRoomNumber())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

//    public static boolean doubleBookingChecker (TreeSet<Booking> bookingTree, int roomNumber)
//    {
//        boolean roomAvailable = true;
//        for (Booking obj : bookingTree)
//        {
//            if (obj.getRoomNumber() == (roomNumber))
//            {
//                roomAvailable = false;
//                break;
//            }
//        }
//        return roomAvailable;
//    }

//    public static String getRoomType1(Map<String, Room> map, Scanner sc)
//    {
//        boolean validRoom = false;
//        System.out.println ("Please enter the room type:");
//        String name=sc.nextLine();
//        String nameToLowerCase = name.toLowerCase();
//        while (validRoom == false)
//        {
//            validRoom = roomValidityChecker(map, nameToLowerCase);
//            if (validRoom == false)
//            {
//                System.out.println ("Please enter valid room type:");
//                name=sc.nextLine();
//                nameToLowerCase = name.toLowerCase();
//            }
//        }
//        return nameToLowerCase;
//    }

//    public static void findBooking(TreeSet<Booking>bookingTree, CustomInput input)
//    {
//        boolean found = false;
//        System.out.println("Enter the last name of the customer");
//        String lastName1 = input.inputString();
//        for (Booking obj : bookingTree)
//        {
//            if (obj.getLastName().equalsIgnoreCase(lastName1))
//            {
//                System.out.println(obj);
//                found = true;
//            }
//        }
//        if(!found)
//        {
//            System.out.println("No booking under that name could be found");
//        }
//    }

//    public static <T> void printSortedBookings(TreeSet<T> bookingTree)
//    {
//        for (T node : bookingTree)
//        {
//            System.out.println(node);
//        }
//    }

}
