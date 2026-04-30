package org.example;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Booking {

	public abstract String firstName();
	public abstract String lastName();
	public abstract int lengthOfStay();
	public abstract int roomNumber();
	public abstract Room room();

	public static BookingBuilder builder() {
		return new AutoValue_Booking.Builder()
				.firstName(null)
				.lastName(null)
				.lengthOfStay(0)
				.roomNumber(0)
				.room(null);
	}

	@AutoValue.Builder
	public abstract static class BookingBuilder {
		public abstract BookingBuilder firstName(String Value);
		public abstract BookingBuilder lastName(String Value);
		public abstract BookingBuilder lengthOfStay(int Value);
		public abstract BookingBuilder roomNumber(int Value);
		public abstract BookingBuilder room(Room Value);
		public abstract Booking build();
	}

	
}
