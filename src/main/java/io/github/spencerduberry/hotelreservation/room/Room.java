package io.github.spencerduberry.hotelreservation.room;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Room {
    public static final Room EMPTY = Room.builder().build();
	public abstract String type();
	public abstract String description();
	public abstract int minRoomNumber();
	public abstract int maxRoomNumber();
	public abstract String bedType();
	public abstract int roomTypeTotal();
	public abstract int rate();
    public abstract RoomBuilder toBuilder();

    public static RoomBuilder builder() {
        return new AutoValue_Room.Builder()
                .type("")
                .description("")
                .minRoomNumber(0)
                .maxRoomNumber(0)
                .bedType("")
                .roomTypeTotal(0)
                .rate(0);
    }

    @AutoValue.Builder
    public abstract static class RoomBuilder {
		public abstract RoomBuilder type(String value);
        public abstract RoomBuilder description(String value);
        public abstract RoomBuilder minRoomNumber(int value);
        public abstract RoomBuilder maxRoomNumber(int value);
        public abstract RoomBuilder bedType(String value);
        public abstract RoomBuilder roomTypeTotal(int value);
        public abstract RoomBuilder rate(int value);
        public abstract Room build();
    }
}
