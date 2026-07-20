package io.github.spencerduberry.hotelreservation.booking;

import static io.github.spencerduberry.hotelreservation.utils.Utils.getValidInt;

import androidx.annotation.Nullable;
import com.google.auto.value.AutoValue;
import io.github.spencerduberry.hotelreservation.utils.CustomInput;
import io.github.spencerduberry.hotelreservation.room.Room;
import io.github.spencerduberry.hotelreservation.room.RoomTypeRepository;

public class BookingService {

    private final CustomInput inputSource;
    private final BookingRepository bookingRepo;
    private final RoomTypeRepository roomRepo;
    BookingAuthenticator authenticator;

    public  BookingService(CustomInput inputSource, BookingRepository bookingRepo, BookingAuthenticator authenticator, RoomTypeRepository roomRepo) {
        this.inputSource = inputSource;
        this.bookingRepo = bookingRepo;
        this.authenticator = authenticator;
        this.roomRepo = roomRepo;
    }

    public void addBooking() {
        int lengthOfStay;
        int roomNumber = 0;
        boolean validRoomNumber = false;
        boolean validRoomType = false;
        String roomName = "";
        Room room = Room.EMPTY;

        System.out.println("Please enter the customer's first name.");
        String firstName = inputSource.inputString();

        System.out.println("Please enter the customer's surname.");
        String surname = inputSource.inputString();

        lengthOfStay = getValidInt(inputSource, "Please enter the length of stay.",
                "The number must be a positive integer. Please try again.",
                val -> val > 0);

        System.out.println("Please enter the type of room.");

        while(!validRoomType) {
            roomName = inputSource.inputString();
            validRoomType = authenticator.isValidRoom(roomName, roomRepo);
        }

        for (Room aRoom: roomRepo.getAll().values()) {
            if (aRoom.type().equalsIgnoreCase(roomName)) {
                room = aRoom;
            }
        }

        System.out.println("Please enter the room number.");

        while (!validRoomNumber) {
            RoomNumberResult result = authenticator
                    .roomNumberValidityChecker(inputSource.inputInt(), room, bookingRepo);

            if (result.isSuccess()) {
                validRoomNumber = true;
                roomNumber = result.value();
            }
        }

        bookingRepo.addBooking(Booking.builder()
                .firstName(firstName)
                .lastName(surname)
                .lengthOfStay(lengthOfStay)
                .roomNumber(roomNumber)
                .room(room)
                .build());
    }

    @AutoValue
    public static abstract class RoomNumberResult {
        @Nullable
        public abstract Integer value();

        @Nullable
        public abstract ErrorType error();

        public enum ErrorType {
            DOUBLEBOOKING,

            OUTOFRANGE
        }

        static RoomNumberResult ok(Integer value) {
            return new AutoValue_BookingService_RoomNumberResult(value, null);
        }

        static RoomNumberResult error(ErrorType error) {
            return new AutoValue_BookingService_RoomNumberResult(null, error);
        }

        public boolean isSuccess() {
            return value() != null;
        }

    }

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
