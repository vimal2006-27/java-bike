import java.util.*;
import java.sql.*;
public class Bike {
    String username, password;
    Scanner s = new Scanner(System.in);
    void Bikemenu() {
        int ch;
        do {
            System.out.println("===== BIKE RENTAL MANAGEMENT =====");
            System.out.println("1. Bike Details");
            System.out.println("2. Customer Details");
            System.out.println("3. Rental Details");
            System.out.println("4. Return Details ");
            System.out.println("5. payment Details ");
            System.out.println("6. Exit");
            System.out.print("Enter Choice: ");
            ch = s.nextInt();

            switch (ch) {
                case 1:
                    bikeDetail();
                    break;
                case 2:
                    customerDetail();
                    break;
                case 3:
                    rentalDetail();
                    break;
                case 4:
                    returnDetail();
                    break;
                case 5:
                    PaymentDetail();
                    break;
                case 6:
                    System.out.println("---Thank You..!!!");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        } while (ch != 6);
    }
    void bikeDetail() {
        int ch = 0;
        while (ch != 5) {
            System.out.println("--------BIKE TABLE--------");
            System.out.println("1. Add Bike");
            System.out.println("2. View Bikes");
            System.out.println("3. Update Bike ");
            System.out.println("4. Delete Bike");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    addBike();
                    break;
                case 2:
                    viewBikes();
                    break;
                case 3:
                    updateBike();
                    break;
                case 4:
                    deleteBike();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void customerDetail() {
        int ch = 0;
        while (ch != 4) {
            System.out.println("--------CUSTOMER TABLE--------");
            System.out.println("1. Add Customers");
            System.out.println("2. View Customers");
            System.out.println("3. Delete Customers");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    addCustomers();
                    break;
                case 2:
                    viewCustomers();
                    break;
                case 3:
                    deleteCustomers();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void rentalDetail() {
        int ch = 0;
        while (ch != 3) {
            System.out.println("--------RENTAL TABLE--------");
            System.out.println("1. Add Rentals");
            System.out.println("2. View Rentals");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    addRental();
                    break;
                case 2:
                    viewRental();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void returnDetail() {
        int ch = 0;
        while (ch != 3) {
            System.out.println("--------RETURN DETAILS TABLE--------");
            System.out.println("1. Add Return Details");
            System.out.println("2. View Rerurn Details");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    addReturnDetail();
                    break;
                case 2:
                    viewReturnDetail();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void PaymentDetail() {
        int ch = 0;
        while (ch != 4) {
            System.out.println("\n-------- PAYMENT DETAILS TABLE --------");
            System.out.println("1. Add Payments");
            System.out.println("2. View Payment Details");
            System.out.println("3. Delete Payment");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    addPayment();
                    break;
                case 2:
                    viewPayment();
                    break;
                case 3:
                    deletePayment();
                    break;
                case 4:
                    System.out.println("Back to Main Menu");
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    void addBike() {
        try {
            s.nextLine();
            System.out.print("bike Id: ");
            String bikeId = s.nextLine();
            System.out.print("bike Name: ");
            String bikeName = s.nextLine();
            System.out.print("Model :");
            String bikeModel = s.nextLine();
            System.out.print("Bike Number: ");
            String BikeNumber = s.nextLine();
            System.out.print("RentPerDay : ");
            String RentPerDay = s.nextLine();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "INSERT INTO bike(Bikeid,Bikename,Model,Bikenumber,RentalperDay) VALUES(?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, bikeId);
            ps.setString(2, bikeName);
            ps.setString(3, bikeModel);
            ps.setString(4, BikeNumber);
            ps.setString(5, RentPerDay);
            ps.executeUpdate();
            System.out.println("Bike Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void viewBikes() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Bike");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                        rs.getString(3) + " " + rs.getString(4) + " " +
                        rs.getString(5) + " " + rs.getString(6));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void updateBike() {
        try {
            System.out.print("Bike ID: ");
            int id = s.nextInt();
            System.out.print("Status : ");
            s.nextLine();
            String status = s.nextLine();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "UPDATE Bike SET status=? WHERE BikeID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("bike Deatils Updated");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void deleteBike() {
        try {
            System.out.print("Bike ID: ");
            int id = s.nextInt();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "DELETE FROM Bike WHERE BikeID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Bike Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void addCustomers() {
        try {
            s.nextLine();
            System.out.print("Customer Name: ");
            String name = s.nextLine();
            System.out.print("Phone: ");
            String phone = s.nextLine();
            System.out.print("LicenseNumber: ");
            String Licens = s.nextLine();
            System.out.print("Address: ");
            String address = s.nextLine();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "INSERT INTO Customers(CustomerName,Phone,LicenseNumber,Address) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, Licens);
            ps.setString(4, address);
            ps.executeUpdate();
            System.out.println("Customer Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void deleteCustomers() {
        try {
            System.out.print("Customers ID: ");
            int id = s.nextInt();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "DELETE FROM  Customers WHERE CustomerID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Customer Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void viewCustomers() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Customers");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " +
                        rs.getString(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void addRental() {
        try {
            System.out.print("Customer ID: ");
            int cid = s.nextInt();
            s.nextLine();
            System.out.print("Bike ID: ");
            int bid = s.nextInt();
            System.out.print("Total Amount: ");
            double total = s.nextDouble();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "INSERT INTO Rentals(CustomerID, BikeID, TotalAmount) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cid);
            ps.setInt(2, bid);
            ps.setDouble(3, total);
            ps.executeUpdate();
            System.out.println("Rental Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void viewRental() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Rentals");
            while (rs.next()) {
                System.out.println(rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(5));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void addReturnDetail() {
        try {
            System.out.print("Rental ID: ");
            int rid = s.nextInt();
            System.out.print("Bike ID: ");
            int bid = s.nextInt();
            System.out.print("Rent Date (dd-MM-yyyy): ");
            String rday = s.next();
            System.out.print("Leat fine: ");
            double latefee = s.nextDouble();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "INSERT INTO ReturnDetails(RentalID, BikeID,ReturnDate, LateFee) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, rid);
            ps.setInt(2, bid);
            ps.setString(3,rday);
            ps.setDouble(4, latefee);
            ps.executeUpdate();
            System.out.println("Return Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    void viewReturnDetail() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM ReturnDetails");
            while (rs.next()) {
                System.out.println(rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getString(4) + " " + rs.getString(5));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void addPayment() {
        try {
            s.nextLine();
            System.out.print("Rental ID  : ");
            String rId = s.nextLine();
            System.out.print("Amount:");
            String amo = s.nextLine();
            System.out.print("Payment Date: ");
            String  pay= s.next();
            System.out.print("Payment Mode : ");
            String pmode = s.next();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "INSERT INTO  payments(RentalID,Amount,PaymentDate,PaymentMode) VALUES(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, rId);
            ps.setString(2, amo);
            ps.setString(3, pay);
            ps.setString(4, pmode);
            ps.executeUpdate();
            System.out.println("Bike Added");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void viewPayment() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM payments");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " +
                        rs.getInt(3) + " " + rs.getString(4) );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void deletePayment() {
        try {
            System.out.print("Bike ID: ");
            int id = s.nextInt();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            String sql = "DELETE FROM payments WHERE RentalID=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Bike Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    void customermenu() {
        int ch = 0;
        while (ch != 2) {
            System.out.println("1. View Bikes Details ");
            System.out.println("2.exit ");
            System.out.print(" Enter Your Choice : ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    viewBikes();
                    break;
                case 2:
                    System.out.println("---Thank you..!!!");
                    break;
                default:
                    System.out.println(" Invalid Choice ");
            }
        }
    }
    void admin() {
        try {
            System.out.print("Username : ");
            username = s.next();
            System.out.print("Password : ");
            password = s.next();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            PreparedStatement ps = con.prepareStatement("select * from Admin where (username=? and password=?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successful");
                Bikemenu();
            } else {
                System.out.println("Invalid Username or Password ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    void Customerlogin() {
        try {
            System.out.print("Username : ");
            username = s.next();
            System.out.print("Password : ");
            password = s.next();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            PreparedStatement ps = con.prepareStatement("select * from customer_login where (username=? and password=?)");;
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("-------Login Successful---------");
                customermenu();
            } else {
                System.out.println("Invalid Username or Password ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        Bike u = new Bike();
        Scanner s = new Scanner(System.in);
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/bikes", "root", "vimha28");
            System.out.println("======= LOGIN =======");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3.Exit");
            System.out.print("Enter Choice: ");
            int ch = s.nextInt();
            switch (ch) {
                case 1:
                    u.admin();
                    break;
                case 2:
                    u.Customerlogin();
                    break;
                case 3:
                    System.out.println("----thank you----");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
