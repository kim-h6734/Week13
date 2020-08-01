import javax.sql.*;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.Scanner;

public class JavaDB {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		addData();
		delete();
	}
	
	public static void delete() throws ClassNotFoundException, SQLException	{
		PreparedStatement query;
		String catcode = "", catdesc = "";
		Scanner key = new Scanner(System.in);

		// load the jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connect to the database
		Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

		// ask a user
		System.out.println("Enter the category code : ");
		catcode = key.nextLine();

		// update query
		query = con1.prepareStatement("Delete from category where catcode = ?");

		query.setString(1, catcode);

		query.executeUpdate();

		System.out.println("One record deleted");
		
		displayRec();
	}

	public static void update() throws ClassNotFoundException, SQLException {
		PreparedStatement query;
		String catcode = "", catdesc = "";
		Scanner key = new Scanner(System.in);

		// load the jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connect to the database
		Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

		// ask a user
		System.out.println("Enter the category code : ");
		catcode = key.nextLine();

		System.out.println("Enter the category desc : ");
		catdesc = key.nextLine();

		// update query
		query = con1.prepareStatement("update category set catcode = ?, catdesc = ? where catcode = ?");

		query.setString(1, catcode);
		query.setString(2, catdesc);
		query.setString(3, catcode);

		query.executeUpdate();

		System.out.println("One record edited");
		
		displayRec();
	}

	public static void displayRec() throws ClassNotFoundException, SQLException {
		PreparedStatement query;
		String catcode, catdesc;

		// load the jdbc driver
		Class.forName("com.mysql.cj.jdbc.Driver");

		// connect to the database
		Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

		// select all the records
		query = con1.prepareStatement("select * from category");

		ResultSet rs = query.executeQuery();

		Category obj1 = new Category();

		// check if there is data
		if (!rs.isBeforeFirst()) {
			System.out.println("There is no data");
			return;
		}

		// display the records
		while (rs.next()) {
			obj1.setCatcode(rs.getString("CatCode"));
			obj1.setCatdesc(rs.getString("Catdesc"));

			System.out.println("Category code : " + obj1.getCatcode());
			System.out.println("Category desc : " + obj1.getCatdesc());

		}

	}

	public static void addData() throws ClassNotFoundException, SQLException {
		PreparedStatement query;
		String catcode, catdesc;

		// load the jdbc driver
		Class.forName("com.mysql.jdbc.Driver");

		// connect to the database
		Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

		// enter data
		Scanner key = new Scanner(System.in);

		System.out.println("Enger a category code ");
		catcode = key.nextLine();

		System.out.println("Enger a category description ");
		catdesc = key.nextLine();

		// create the sql statement that will add the data
		query = con1.prepareStatement("insert into category values (?,?)");
		query.setString(1, catcode);
		query.setString(2, catdesc);

		query.executeUpdate();

		displayRec();

	}
}
