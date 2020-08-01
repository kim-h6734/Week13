
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Main obj1 = new Main();
    obj1.add();
    obj1.edit();
    obj1.delete();
//
//    Inventory jj1 = new Inventory();
//    jj1.updateTable();
//    jj1.setVisible(true);
    }

    public static void add() throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        Class.forName("com.mysql.jdbc.Driver");

        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

        Scanner key = new Scanner(System.in);

        String catCode = "", catDesc = "";

        System.out.println("Enger a category code ");
        catCode = key.nextLine();

        System.out.println("Enger a category description ");
        catDesc = key.nextLine();

        // create the sql statement that will add the data
        query = con1.prepareStatement("insert into category values (?,?)");
        query.setString(1, catCode);
        query.setString(2, catDesc);

        query.executeUpdate();

        System.out.println("Data was added");
        display();
    }

    public static void display() throws ClassNotFoundException, SQLException {
        PreparedStatement query;
        // load the jdbc driver
        Class.forName("com.mysql.jdbc.Driver");
        // connect to the database
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");


        String catcode, catdesc;

        // select all the records
        query = con1.prepareStatement("select * from category");
        ResultSet rs = query.executeQuery();

//        Category obj1 = new Category();

        // check if there is data
        if (!rs.isBeforeFirst()) {
            System.out.println("There is no data");
            return;
        }

        // display the records
        while (rs.next()) {
//            obj1.setCatcode(rs.getString("catCode"));
//            obj1.setCatdesc(rs.getString("catDesc"));

            System.out.println(rs.getString("catCode"));
            System.out.println(rs.getString("catDesc"));

        }

    }

    public static void edit() throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        // load the jdbc driver
        Class.forName("com.mysql.jdbc.Driver");
        // connect to the database
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

        Scanner key = new Scanner(System.in);

        String catCode = "", catDesc = "";


        System.out.println("Edit : Enter a category code ");
        catCode = key.nextLine();

        System.out.println("Enter a category description ");
        catDesc = key.nextLine();

        // create the sql statement that will add the data
        query = con1.prepareStatement("update category set catdesc = ? where catCode = ?");
        query.setString(1, catDesc);
        query.setString(2, catCode);

        query.executeUpdate();

        System.out.println("Data was edited");
        display();

    }

    public static void delete() throws SQLException, ClassNotFoundException {
        PreparedStatement query;
        // load the jdbc driver
        Class.forName("com.mysql.jdbc.Driver");
        // connect to the database
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

        Scanner key = new Scanner(System.in);
        String catCode = "", catDesc = "";

        System.out.println("Delete : Enter a category code ");
        catCode = key.nextLine();


        // create the sql statement that will add the data
        query = con1.prepareStatement("Delete from category where catcode = ?");
        query.setString(1, catCode);

        query.executeUpdate();

        System.out.println("Data was deleted");
        display();
    }
}
