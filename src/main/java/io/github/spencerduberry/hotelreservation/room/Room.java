package io.github.spencerduberry.hotelreservation.room;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Room {
    public static final Room EMPTY = Room.builder().build();
	public abstract String name();
	public abstract String bedType();
	public abstract int rate();
    public abstract RoomBuilder toBuilder();

    public static RoomBuilder builder() {

        return new AutoValue_Room.Builder()
                .name("")
                .bedType("")
                .rate(0);
    }

    @AutoValue.Builder
    public abstract static class RoomBuilder {
		public abstract RoomBuilder name(String value);
        public abstract RoomBuilder bedType(String value);
        public abstract RoomBuilder rate(int value);
        public abstract Room build();
    }
}
