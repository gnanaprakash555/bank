package bank;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class account {
	static String password;
    static int id;
	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER ID NO");
		int a_id = sc.nextInt();
		System.out.println("PASSWORD");
		String p = sc.next();
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM accounts;");
			while (rs.next()) {
				int id = rs.getInt("id");
				if (id == a_id) {
					password = rs.getString("password");
				}

			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
			try {
				if (c != null) {
					c.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
				System.exit(0);
			}
		}

		if (p.equals(password)) {
			int amt, opt;

			IDataAccess b = DataAccessProvider.getDataAccess();
			do {
				accountMenu.menu3();
				opt = sc.nextInt();
				switch (opt) {
				case 1:

					b.display(a_id);

					break;

				case 2:
					System.out.println("enter the amount to be depoited");
					amt = sc.nextInt();
					b.deposit(a_id, amt);
					break;
				case 3:
					System.out.println("enter the amount to be withdraw");
					amt = sc.nextInt();
					b.withdraw(a_id, amt);
					break;
				case 4:
					b.deleteAccount(a_id);
					break;

				case 5:
					System.out.println("enter the transfering id");
					int t_id = sc.nextInt();
					System.out.println("enter the amount to be transfer");
					amt = sc.nextInt();
					b.transferAccount(a_id, t_id, amt);
					break;
				case 6:
					System.out.println("enter the  id_no");
					 id=sc.nextInt();
					b.editAccount(id);
					break;
				case 7:

					b.transactionHistory(a_id);
					break;
				case 8:
					System.out.println("enter the  id_no");
					 id=sc.nextInt();
					b.createAccount(id);
					break;
				}
			} while (opt != 0);

		}

		else {
			System.out.println("incorrect password");

		}
		System.exit(1);
	}

}
