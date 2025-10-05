# Hotel Reservation Application
## Overview 
This application allows users to make hotel reservations for customers. Users can choose room types, assign room numbers, input customer names, and specify the length of stay. The system also allows viewing reservation details, generating a total revenue report, sorting reservations alphabetically, and displaying room availability.
## Key Features:
- **Make reservations** for customers, including room type, room number, customer name, and length of stay.
- **Display reservation details** by searching by customer name.
- **Generate a report** of total revenue generated from all bookings.
- **Sort reservations** alphabetically by last name.
- **Display available rooms** for each room type.
## Technologies Used:
- **Java** (Version 5 or higher recommended)
- **Eclipse** (for initial development, but now using Visual Studio Code for continued work)
- **Java Collections Framework** (Map, TreeSet, etc.)
## Installation
### Prerequisites
- **Java Development Kit (JDK)** - You will need to have Java 5 or later installed. 
[Download Oracle JDK here](https://www.oracle.com/java/technologies/javase-downloads.html)

- **Visual Studio Code (VSCode)** - For continued development, you can use VSCode. It's lightweight and supports Java development.
[Download Visual Studio Code here](https://code.visualstudio.com/Download)
- **Eclipse (Optional)** - If you prefer, you can also use Eclipse as an IDE for Java development.
### Steps to Run the Project Locally
1. **Clone the Repository**
git clone https://github.com/spencerduberry/Hotel-Reservation-App_Java.git
2. **Open the Project**
- Open the project folder in Visual Studio Code or Eclipse.
- If you're using VSCode, ensure you have the Java Extension Pack installed to support Java development.
3. **Compile and Run the Application**:  
**In VSCode**:
- Open a terminal (Ctrl + ~).
- Compile the Java files:
javac *.java
- Run the main class:
java Tester  
**In Eclipse**:
- Create a new Java project and import the provided .java files into the src folder.
- Run the Tester.java class as a Java application.
## Usage
1. **Make a reservation**: Input customer details and assign a room.
2. **Display reservation details**: Search for a reservation by last name.
3. **Generate revenue report**: See the revenue for all bookings.
4. **Sort reservations**: Sort and display bookings alphabetically by last name.
5. **Display available rooms**: See how many rooms are available for each type.
6. **Exit**: Close the program.
### Example Workflow
- **Make a reservation**: Select the option to make a new reservation. Provide details for the customer and room.
- **Display reservation details**: Search by last name to find and display a specific booking.
- **Generate a revenue report**: View the total revenue from all bookings based on room rates.
- **Sort reservations**: View reservations sorted by last name.
- **Display available rooms**: Check how many rooms of each type are still available for booking.
### Code Structure
## Main classes:
- **Tester**: The entry point for the program, where the main menu is displayed and user interactions are handled.
- **Booking**: Handles the creation and management of customer reservations, including input validation and room assignments.
- **Room**: Defines the properties of each room type (e.g., roomTypeTotal, room number, rate) and includes methods for calculating revenue and checking room availability.
### Notes
- **Java Version**: This application is compatible with Java 5 or later. It's recommended to use Java 8 for optimal performance and features.
- **Future Updates**: This project was initially developed in Eclipse, but it will now be maintained and developed using Visual Studio Code (VSCode).
