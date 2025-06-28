package busResv;
import java.sql.*;
import java.util.Scanner;

public class Booking {
    String customerName;
    int busNo;
    Date date;

    public Booking() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name of Customer: ");
        customerName = scanner.nextLine();

        while (true) {
            System.out.print("Enter Bus Number: ");
            busNo = scanner.nextInt();
            if (Bus.busExists(busNo)) break;
            System.out.println("Invalid bus number. Please try again.");
        }

        scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String dateInput = scanner.nextLine();
        date = Date.valueOf(dateInput);
    }

    public boolean isAvailable() throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");

        String capacityQuery = "SELECT capacity FROM buses WHERE bus_no = ?";
        PreparedStatement pstmt = con.prepareStatement(capacityQuery);
        pstmt.setInt(1, busNo);
        ResultSet rs = pstmt.executeQuery();

        if (!rs.next()) return false;
        int capacity = rs.getInt("capacity");

        String bookingCountQuery = "SELECT COUNT(*) FROM bookings WHERE bus_no = ? AND date = ?";
        pstmt = con.prepareStatement(bookingCountQuery);
        pstmt.setInt(1, busNo);
        pstmt.setDate(2, date);
        rs = pstmt.executeQuery();
        rs.next();
        int booked = rs.getInt(1);

        con.close();
        return booked < capacity;
    }

    public void confirmBooking() throws Exception {
        if (isAvailable()) {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");

            String insertQuery = "INSERT INTO bookings (customer_name, bus_no, date) VALUES (?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);
            pstmt.setString(1, customerName);
            pstmt.setInt(2, busNo);
            pstmt.setDate(3, date);
            pstmt.executeUpdate();

            System.out.println("✅ Booking Confirmed!");
            con.close();
        } else {
            System.out.println("❌ Sorry, Bus is Full!");
        }
    }

    public static void cancelBooking() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name of Customer: ");
        String name = scanner.nextLine();
        System.out.print("Enter Bus Number: ");
        int busNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        Date date = Date.valueOf(scanner.nextLine());

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");
        String deleteQuery = "DELETE FROM bookings WHERE customer_name = ? AND bus_no = ? AND date = ?";
        PreparedStatement pstmt = con.prepareStatement(deleteQuery);
        pstmt.setString(1, name);
        pstmt.setInt(2, busNo);
        pstmt.setDate(3, date);

        int rows = pstmt.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Booking Cancelled!");
        } else {
            System.out.println("❌ Booking Not Found.");
        }
        con.close();
    }

    public static void showBookingsForBus() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Bus Number: ");
        int busNo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        Date date = Date.valueOf(scanner.nextLine());

        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");
        String query = "SELECT customer_name FROM bookings WHERE bus_no = ? AND date = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, busNo);
        pstmt.setDate(2, date);

        ResultSet rs = pstmt.executeQuery();
        System.out.println("Bookings for Bus " + busNo + " on " + date + ":");
        boolean any = false;
        while (rs.next()) {
            any = true;
            System.out.println("- " + rs.getString("customer_name"));
        }
        if (!any) {
            System.out.println("No bookings found.");
        }

        con.close();
    }
}
