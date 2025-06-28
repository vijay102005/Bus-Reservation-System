package busResv;

import java.sql.*;

public class Bus {
    private int busNo;
    private boolean ac;
    private int capacity;

    public Bus(int busNo, boolean ac, int capacity) {
        this.busNo = busNo;
        this.ac = ac;
        this.capacity = capacity;
    }

    public int getBusNo() {
        return busNo;
    }

    public boolean isAc() {
        return ac;
    }

    public int getCapacity() {
        return capacity;
    }

    public static void displayAvailableBuses() throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM buses");

        System.out.printf("%-10s %-10s %-10s\n", "Bus No", "AC", "Capacity");
        System.out.println("-----------------------------------");

        while (rs.next()) {
            System.out.printf("%-10d %-10s %-10d\n",
                    rs.getInt("bus_no"),
                    rs.getBoolean("ac") ? "Yes" : "No",
                    rs.getInt("capacity"));
        }

        con.close();
    }

    public static boolean busExists(int busNo) throws Exception {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bus_reservation", "root", "password");
        String query = "SELECT * FROM buses WHERE bus_no = ?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setInt(1, busNo);
        ResultSet rs = pstmt.executeQuery();
        boolean exists = rs.next();
        con.close();
        return exists;
    }
}

