package io.github.spencerduberry.hotelreservation.room;
import androidx.annotation.Nullable;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Room {
    public static final Room EMPTY = Room.builder().build();
	public abstract String name();
	public abstract String bedType();
    @Nullable
	public abstract Integer rate();
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
        public abstract RoomBuilder rate(@Nullable Integer value);
        public abstract Room build();
    }
}
