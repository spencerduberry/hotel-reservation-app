package org;
import com.google.auto.value.AutoValue;

import java.util.Map;

@AutoValue
public abstract class Room {

	public abstract String type();
	public abstract String description();
	public abstract int minRoomNumber();
	public abstract int maxRoomNumber();
	public abstract String bedType();
	public abstract int roomTypeTotal();
	public abstract int rate();
    public abstract Builder toBuilder();

    public static Builder builder() {
        return new AutoValue_Room.Builder()
                .type(null)
                .description(null)
                .minRoomNumber(0)
                .maxRoomNumber(0)
                .bedType(null)
                .roomTypeTotal(0)
                .rate(0);
    }

    @AutoValue.Builder
    public abstract static class Builder{
		public abstract Builder type(String value);
        public abstract Builder description(String value);
        public abstract Builder minRoomNumber(int value);
        public abstract Builder maxRoomNumber(int value);
        public abstract Builder bedType(String value);
        public abstract Builder roomTypeTotal(int value);
        public abstract Builder rate(int value);
        public abstract Room build();
    }
}
