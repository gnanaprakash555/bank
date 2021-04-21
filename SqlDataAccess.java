package bank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class SqlDataAccess {
	static long transfer_id;
	static String name;
	static int age;
	static String dob;
	static long aadhar;
	static long phone;
	static String email;
	static String ifsc;
	static String a_ifsc;
	static String pass;
	static long t_id;
	static long amount = 0;

	static public void display(int a_id) throws Exception {
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
					name = rs.getString("name");
					age = rs.getInt("age");
					dob = rs.getString("dob");
					aadhar = rs.getLong("aadhar_no");
					phone = rs.getLong("phone_no");
					email = rs.getString("email");
					ifsc = rs.getString("ifsc");
					amount = rs.getInt("amount");
					System.out.println("NAME= " + name);
					System.out.println("AGE = " + age);
					System.out.println("DOB = " + dob);
					System.out.println("AADHAR_NO = " + aadhar);
					System.out.println("PHONE_NO= " + phone);
					System.out.println("EMAIL= " + email);
					System.out.println("IFSC = " + ifsc);
					System.out.println("AMOUNT = " + amount);
					
					break;
				}
			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			rs.close();
			stmt.close();
			c.close();
		}

	}

	static public void createAccount(int id1) throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection c = null;
		Statement stmt = null;
		PreparedStatement pt = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			System.out.print("ENTER YOUR NAME");
			String name1 = sc.nextLine();
			System.out.print("ENTER YOUR AGE");
			age = sc.nextInt();
			System.out.println("ENTER YOUR DATE OF BIRTH");
			dob = sc.next();
			System.out.println("ENTER YOUR AADHAR_NO");
			aadhar = sc.nextLong();
			System.out.println("ENTER YOUR PHONE_NO");
			phone = sc.nextLong();
			System.out.println("ENTER YOUR EMAIL_ID");
			email = sc.next();
			System.out.println("ENTER YOUR IFSC_CODE");
			ifsc = sc.next();
			System.out.println("ENTER YOUR PASSWORD FOR YOUR ACCOUNT");
			pass = sc.next();
			String sql = "INSERT INTO accounts(id,name,age,dob,aadhar_no,phone_no,email,ifsc,password,amount)VALUES (?,?,?,?,?,?,?,?,?,?)";
			pt = c.prepareStatement(sql);
			pt.setInt(1, id1);
			pt.setString(2, name1);
			pt.setInt(3, age);
			pt.setString(4, dob);
			pt.setLong(5, aadhar);
			pt.setLong(6, phone);
			pt.setString(7, email);
			pt.setString(8, ifsc);
			pt.setString(9, pass);
			pt.setLong(10, 0);
			pt.executeUpdate();
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			pt.close();
			stmt.close();
			c.close();
		}
		System.out.println("ACCOUNT CREATED SUCCESSFULLY");

	}

	static public void deposit(int a_id, int amt) throws Exception {

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String m = String.valueOf(formatter.format(date));
		Connection c = null;
		Statement stmt = null;
		PreparedStatement s = null;
		PreparedStatement pt = null;
		ResultSet rs = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM accounts;");

			while (rs.next()) {
				int id = rs.getInt("id");
				if (a_id == id) {
					amount = rs.getInt("amount") + amt;
					s = c.prepareStatement("update accounts set amount=? where id=?");
					s.setLong(1, amount);
					s.setInt(2, a_id);
					s.executeUpdate();
					String sql = "INSERT INTO transaction_history(accounts_id,amount,type,date)VALUES (?,?,?,?)";
					pt = c.prepareStatement(sql);
					pt.setInt(1, a_id);
					pt.setLong(2, amt);
					pt.setString(3, "deposit");
					pt.setString(4, m);
					pt.executeUpdate();
					break;
				}

			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			s.close();
			pt.close();
			rs.close();
			stmt.close();
			c.close();
		}
		System.out.println("DEPOSITED SUCCESSFULLY");
	}

	static public void withdraw(int a_id, int amt) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String m = String.valueOf(formatter.format(date));
		Connection c = null;
		Statement stmt = null;
		PreparedStatement s = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM accounts;");
			while (rs.next()) {
				int id = rs.getInt("id");
				if (a_id == id) {
					amount = rs.getInt("amount") - amt;
					s = c.prepareStatement("update accounts set amount=? where id=?");
					s.setLong(1, amount);
					s.setInt(2, a_id);
					s.executeUpdate();
					String sql = "INSERT INTO transaction_history(accounts_id,amount,type,date)VALUES (?,?,?,?)";
					pt = c.prepareStatement(sql);
					pt.setInt(1, a_id);
					pt.setLong(2, amt);
					pt.setString(3, "withdraw");
					pt.setString(4, m);
					pt.executeUpdate();

				}

			}
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			s.close();
			pt.close();
			rs.close();
			stmt.close();
			c.close();
		}

		System.out.println("AMOUNT WITHDRAWN  SUCCESSFULLY");

	}

	static public void deleteAccount(int id) throws Exception {
		Connection c = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement s = null;
		PreparedStatement s1 = null;
		PreparedStatement s2 = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			rs = stmt.executeQuery("SELECT * FROM transaction_history;");
			while (rs.next()) {
				int a_id = rs.getInt("accounts_id");
				if (a_id == id) {
					int t_id = rs.getInt("transfer_id");
					s = c.prepareStatement("delete from transfer_transactions where fetch_id = ?");
					s.setLong(1, t_id);
					s.executeUpdate();
				}
			}

			s1 = c.prepareStatement("delete from accounts where id = ?");
			s1.setLong(1, id);
			s2 = c.prepareStatement("delete from transaction_history where accounts_id = ?");
			s2.setLong(1, id);

			s2.executeUpdate();
			s1.executeUpdate();
			c.commit();

		}

		catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			s.close();
			s1.close();
			s2.close();
			rs.close();
			stmt.close();
			c.close();

		}
		System.out.println("ACCOUNT DELETED SUCCESSFULLY");
	}

	static public void transferAccount(int id1, int id2, int amt) throws Exception {
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String m = String.valueOf(formatter.format(date));
		Connection c = null;

		Statement st1 = null;
		Statement st2 = null;
		Statement st3 = null;
		Statement st4 = null;

		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;

		PreparedStatement s = null;
		PreparedStatement s1 = null;
		PreparedStatement pt = null;
		PreparedStatement pt1 = null;
		PreparedStatement p = null;
		PreparedStatement p1 = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			st1 = c.createStatement();
			st2 = c.createStatement();
			st3 = c.createStatement();
			st4 = c.createStatement();
			rs4 = st4.executeQuery("SELECT * FROM accounts;");
			while (rs4.next()) {
				int id = rs4.getInt("id");
				if (id == id2) {
					ifsc = rs4.getString("ifsc");
					break;
				}
			}

			rs1 = st1.executeQuery("SELECT * FROM accounts;");
			do {
				System.out.println("ENTER IFSC FOR " + id2);
				a_ifsc = sc.next();
				if (a_ifsc.equals(ifsc)) {

					while (rs1.next()) {
						int id = rs1.getInt("id");

						if (id == id1 || id == id2) {

							if (id == id1) {
								amount = rs1.getLong("amount") - amt;

								s = c.prepareStatement("update accounts set amount=? where id=?");
								s.setLong(1, amount);
								s.setInt(2, id1);
								s.executeUpdate();
								String sql = "INSERT INTO transaction_history(accounts_id,amount,type,date)VALUES (?,?,?,?)";
								pt = c.prepareStatement(sql);
								pt.setInt(1, id1);
								pt.setLong(2, amt);
								pt.setString(3, "transfer");
								pt.setString(4, m);
								pt.executeUpdate();

								rs2 = st2.executeQuery("SELECT * FROM transaction_history;");
								while (rs2.next()) {
									int a_id = rs2.getInt("accounts_id");
									if (a_id == id1) {
										transfer_id = rs2.getLong("transfer_id");

									}

								}

								pt1 = c.prepareStatement(
										"INSERT INTO transfer_transactions(fetch_id,from_id,to_id,status)VALUES (?,?,?,?)");
								pt1.setLong(1, transfer_id);
								pt1.setInt(2, id1);
								pt1.setInt(3, id2);
								pt1.setString(4, "debited");
								pt1.executeUpdate();

							} else {
								amount = rs1.getLong("amount") + amt;

								s1 = c.prepareStatement("update accounts set amount=? where id=?");
								s1.setLong(1, amount);
								s1.setInt(2, id2);
								s1.executeUpdate();
								String sql = "INSERT INTO transaction_history(accounts_id,amount,type,date)VALUES (?,?,?,?)";
								p = c.prepareStatement(sql);
								p.setInt(1, id2);
								p.setLong(2, amt);
								p.setString(3, "transfer");
								p.setString(4, m);
								p.executeUpdate();
								rs3 = st3.executeQuery("SELECT * FROM transaction_history;");
								while (rs3.next()) {
									int a_id = rs3.getInt("accounts_id");
									if (a_id == id2) {
										transfer_id = rs3.getLong("transfer_id");

									}

								}

								p1 = c.prepareStatement(
										"INSERT INTO transfer_transactions(fetch_id,from_id,to_id,status)VALUES (?,?,?,?)");

								p1.setLong(1, transfer_id);
								p1.setInt(2, id1);
								p1.setInt(3, id2);
								p1.setString(4, "credited");
								p1.executeUpdate();

							}

						}

					}

				} else {
					System.out.println("INCORRECT PASSWORD");
				}
			} while (a_ifsc.equals(ifsc) == false);
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			s.close();
			s1.close();
			pt.close();
			pt1.close();
			p.close();
			p1.close();
			rs1.close();
			rs2.close();
			rs3.close();
			rs4.close();
			st1.close();
			st2.close();
			st3.close();
			st4.close();
			c.close();
		}
		System.out.println("AMOUNT TRANSFERED SUCCESSFULLY");
	}

	
	static public void editAccount(int a_id) throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection c = null;

		Statement stmt = null;

		PreparedStatement s = null;

		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt = c.createStatement();

			int opt;
			do {
				accountMenu.menu();
				opt = sc.nextInt();

				switch (opt) {
				case 1:
					s = c.prepareStatement("UPDATE accounts SET name=? WHERE id=?");
					System.out.println("ENTER YOUR NAME");
					name = sc.next();

					s.setString(1, name);
					s.setInt(2, a_id);
					s.executeUpdate();

					System.out.println("YOUR NAME UPDATED SUCCESSFULLY");

					break;
				case 2:
					s = c.prepareStatement("update accounts set age=? where id=?");
					System.out.println("ENTER YOUR AGE");
					age = sc.nextInt();
					s.setInt(1, age);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR AGE UPDATED SUCCESSFULLY");
					break;
				case 3:
					s = c.prepareStatement("update accounts set dob=? where id=?");
					System.out.println("ENTER YOUR DATE OF BIRTH");
					dob = sc.next();
					s.setString(1, dob);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR DOB UPDATED SUCCESSFULLY");
					break;
				case 4:
					s = c.prepareStatement("update accounts set aadhar_no=? where id=?");
					System.out.println("ENTER YOUR AADHAR_NO");
					aadhar = sc.nextLong();
					s.setLong(1, aadhar);
					s.setInt(2, a_id);
					s.executeUpdate();

					System.out.println("YOUR AADHAR_NO UPDATED SUCCESSFULLY");

					break;
				case 5:
					s = c.prepareStatement("update accounts set phone_no=? where id=?");
					System.out.println("ENTER YOUR PHONE_NO");
					phone = sc.nextLong();
					s.setLong(1, phone);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR PHONE_NO UPDATED SUCCESSFULLY");
					break;
				case 6:
					s = c.prepareStatement("update accounts set email=? where id=?");
					System.out.println("ENTER YOUR EMAIL_ID");
					email = sc.next();
					s.setString(1, email);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR EMAIL_ID UPDATED SUCCESSFULLY");
					break;
				case 7:
					s = c.prepareStatement("update accounts set ifsc=? where id=?");
					System.out.println("ENTER YOUR IFSC_CODE");
					ifsc = sc.next();
					s.setString(1, ifsc);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR IFSC_CODE UPDATED SUCCESSFULLY");
					break;
				case 8:
					s = c.prepareStatement("update accounts set password=? where id=?");
					System.out.println("ENTER YOUR PASSWORD");
					pass = sc.next();
					s.setString(1, pass);
					s.setInt(2, a_id);
					s.executeUpdate();
					System.out.println("YOUR PASSWORD UPDATED SUCCESSFULLY");
					break;
				case 0:
					System.out.println("THANK YOU");
					break;
				}
			} while (opt != 0);
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			s.close();
			stmt.close();
			c.close();
		}

	}

	static public void transactionHistory(int a_id) throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection c = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		PreparedStatement s3 = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/gnanaprakash", "postgres", "root");
			c.setAutoCommit(false);
			stmt1 = c.createStatement();
			stmt2 = c.createStatement();
		
			
			int opt;
			do {
				accountMenu.menu1();
				opt = sc.nextInt();

				switch (opt) {
				case 1:
					rs1 = stmt1.executeQuery("SELECT * FROM transaction_history;");
					while (rs1.next()) {
						int id = rs1.getInt("accounts_id");
						String type = rs1.getString("type");

						if (id == a_id && type.equals("deposit")) {
							amount = rs1.getLong("amount");
							String date = rs1.getString("date");

							System.out.println(amount);
							System.out.println(date);
							System.out.println(type);

						}
					}
					break;
				case 2:
					rs1 = stmt1.executeQuery("SELECT * FROM transaction_history;");
					while (rs1.next()) {
						int id = rs1.getInt("accounts_id");
						String type = rs1.getString("type");

						if (id == a_id && type.equals("withdraw")) {
							amount = rs1.getLong("amount");
							String date = rs1.getString("date");

							System.out.println(amount);
							System.out.println(date);
							System.out.println(type);

						}
					}
					break;
				case 3:
					rs1 = stmt1.executeQuery("SELECT * FROM transaction_history;");
					while (rs1.next()) {
						int id = rs1.getInt("accounts_id");
						String type = rs1.getString("type");

						if (id == a_id && type.equals("transfer")) {
							amount = rs1.getLong("amount");
							String date = rs1.getString("date");
							t_id = rs1.getLong("transfer_id");
							System.out.println("AMOUNT:" + amount);
							System.out.println("DATE:" + date);
							System.out.println("TYPE:" + type);
							s3 = c.prepareStatement("SELECT * FROM transfer_transactions where fetch_id=?");

							s3.setLong(1, t_id);
							rs2 = s3.executeQuery();
							while (rs2.next()) {

								String status = rs2.getString("status");
								int to_id = rs2.getInt("to_id");
								int from_id = rs2.getInt("from_id");
								System.out.println("FROM_ID:" + from_id);
								System.out.println("TO_ID:" + to_id);
								System.out.println("STATUS:" + status);

							}
						}
					}
					break;
				case 4:
					rs1 = stmt1.executeQuery("SELECT * FROM transaction_history;");
					while (rs1.next()) {
						int id = rs1.getInt("accounts_id");

						if (id == a_id) {
							String type = rs1.getString("type");
							if (type.equals("deposit") || type.equals("withdraw")) {
								amount = rs1.getLong("amount");
								String date = rs1.getString("date");

								System.out.println("AMOUNT:" + amount);
								System.out.println("DATE:" + date);
								System.out.println("TYPE:" + type);

							} else {
								amount = rs1.getLong("amount");
								String date = rs1.getString("date");
								t_id = rs1.getLong("transfer_id");
								System.out.println("AMOUNT:" + amount);
								System.out.println("DATE:" + date);
								System.out.println("TYPE:" + type);
								s3 = c.prepareStatement("SELECT * FROM transfer_transactions where fetch_id=?");
								s3.setLong(1, t_id);
								rs2 = s3.executeQuery();
								while (rs2.next()) {

									String status = rs2.getString("status");
									int to_id = rs2.getInt("to_id");
									int from_id = rs2.getInt("from_id");
									System.out.println("FROM_ID:" + from_id);
									System.out.println("TO_ID:" + to_id);
									System.out.println("STATUS:" + status);

								}
							}
						}
					}

					break;
				case 0:
					System.out.println("THANK YOU");
					break;
				}
			} while (opt != 0);
			c.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			rs1.close();
			stmt1.close();
			stmt2.close();
			c.close();
		}
	}

	public static void main(String[] args) throws Exception {

		transactionHistory(3);
	}

}
