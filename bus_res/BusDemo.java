package busResv;

import java.util.Scanner;

public class BusDemo {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int userInput;

        do {
            System.out.println("\n=== Bus Reservation System ===");
            System.out.println("1. Display Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Show Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            userInput = scanner.nextInt();

            switch (userInput) {
                case 1:
                    Bus.displayAvailableBuses();
                    break;
                case 2:
                    Booking booking = new Booking();
                    booking.confirmBooking();
                    break;
                case 3:
                    Booking.cancelBooking();
                    break;
                case 4:
                    Booking.showBookingsForBus();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } while (userInput != 5);
    }
}

