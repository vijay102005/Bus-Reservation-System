🚌 Online Bus Reservation System (Java + JDBC + MySQL)
🔧 Technologies Used
Java (Core Java, OOPs)

JDBC (Java Database Connectivity)

MySQL

Eclipse / IntelliJ (or any IDE)

Command-Line Interface

📌 Project Description
This is a console-based Java application that allows users to book, search, cancel, and view bus reservations. The system demonstrates object-oriented programming principles such as encapsulation, inheritance, and polymorphism, and uses JDBC for database operations with MySQL.

✅ Key Features
🪑 Seat Booking

🔍 Bus Search (by source & destination)

❌ Ticket Cancellation

🧾 Fare Calculation

📊 Display of Bus Details

💾 Data stored and retrieved using MySQL database

💡 OOP Concepts Applied
Encapsulation: All bus data and operations are encapsulated within classes

Inheritance: Common functionality extended in booking and cancellation modules

Polymorphism: Method overriding for different types of buses/fare calculations

🗃️ Database Structure (MySQL)
buses
bus_id, source, destination, total_seats, available_seats, fare

bookings
booking_id, passenger_name, bus_id, seats_booked, booking_date

users (if login implemented)
user_id, username, password

⚙️ How to Run
Clone or download the project

Make sure MySQL is installed and running

Import the database schema from bus_schema.sql (create your tables)

Open the Java project in your IDE

Update DB credentials in the code (url, username, password)

Compile and run the Main.java class

Use console menu to perform booking, cancellation, and other operations

