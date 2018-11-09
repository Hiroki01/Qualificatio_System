package masteDAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import masteDTO.StudentDTO;

public class StudentDAO {

	public static StudentDTO Insert(int id, String name, String namek, String email, int department,
			int course, int school_year, int set_in, int question, String answer, String pass) {
		StudentDTO result = null;
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "INSERT INTO Student VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			ps = co.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, namek);
			ps.setString(4, email);
			ps.setInt(5, department);
			ps.setInt(6, course);
			ps.setInt(7, school_year);
			ps.setInt(8, set_in);
			ps.setInt(9, question);
			ps.setString(10, answer);
			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(11, key);

			ps.executeUpdate();
			result = new StudentDTO(id, name, namek, email, department, course, school_year, set_in, question, answer,
					key);
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static StudentDTO search(int key) {
		StudentDTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "SELECT * FROM Student where SID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			rs.next();
			int id = rs.getInt("SID");
			String name = rs.getString("SNAME");
			String namek = rs.getString("SKNAME");
			String mail = rs.getString("SEMAIL");
			int did = rs.getInt("DEPARTMENT_ID");
			int coid = rs.getInt("COURSE_ID");
			int year = rs.getInt("SCHOOL_YEAR");
			int clasies = rs.getInt("CLASS");
			int question = rs.getInt("QUESTION");
			String answer = rs.getString("ANSWER");
			String pass = rs.getString("PASSWORD");
			result = new StudentDTO(id, name, namek, mail, did, coid, year, clasies, question, answer, pass);
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static StudentDTO update(int id, String name, String namek, String email, int department, int course,
			int school_year, int set_in, int question, String answer, String pass) {
		StudentDTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql1 = "UPDATE\r\n" +
					"STUDENT\r\n" +
					"SET SNAME = ?,\r\n" +
					"SKNAME =?,\r\n" +
					"SEMAIL = ?,\r\n" +
					"DEPARTMENT_ID = ?,\r\n" +
					"COURSE_ID = ?,\r\n" +
					"SCHOOL_YEAR = ?,\r\n" +
					"CLASS = ?,\r\n" +
					"QUESTION = ?,\r\n" +
					"ANSWER  = ?\r\n" +
					"WHERE SID = ?\r\n" +
					"AND PASSWORD = ?";
			ps = con.prepareStatement(sql1);

			ps.setString(1, name);
			ps.setString(2, namek);
			ps.setString(3, email);
			ps.setInt(4, department);
			ps.setInt(5, course);
			ps.setInt(6, school_year);
			ps.setInt(7, set_in);
			ps.setInt(8, question);
			ps.setString(9, answer);
			ps.setInt(10, id);
			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(11, key);

			ps.executeUpdate();
			result = new StudentDTO(id, name, namek, email, department, course, school_year, set_in, question, answer,
					key);

		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static StudentDTO update(int id, String name, String namek, String email, int department, int course,
			int school_year, int set_in, int question, String answer, String pass, String pass2) {
		StudentDTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql1 = "UPDATE\r\n" +
					"STUDENT\r\n" +
					"SET SNAME = ?,\r\n" +
					"SKNAME =?,\r\n" +
					"SEMAIL = ?,\r\n" +
					"DEPARTMENT_ID = ?,\r\n" +
					"COURSE_ID = ?,\r\n" +
					"SCHOOL_YEAR = ?,\r\n" +
					"CLASS = ?,\r\n" +
					"QUESTION = ?,\r\n" +
					"ANSWER  = ?,\r\n" +
					"PASSWORD = ?\r\n" +
					"WHERE SID = ?\r\n";
			ps = con.prepareStatement(sql1);

			ps.setString(1, name);
			ps.setString(2, namek);
			ps.setString(3, email);
			ps.setInt(4, department);
			ps.setInt(5, course);
			ps.setInt(6, school_year);
			ps.setInt(7, set_in);
			ps.setInt(8, question);
			ps.setString(9, answer);
			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(10, key);
			ps.setInt(11, id);

			ps.executeUpdate();
			result = new StudentDTO(id, name, namek, email, department, course, school_year, set_in, question, answer,
					key);

		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static ArrayList<StudentDTO> searchAll() {
		ArrayList<StudentDTO> resultlist = new ArrayList<StudentDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "admin",
					"Shroomish");
			String sql = "SELECT\r\n" +
					"STUDENT.SID,\r\n" +
					"STUDENT.SNAME,\r\n" +
					"DEPARTMENT.DEPARTMENT_NAME,\r\n" +
					"COURSE.COURSE_NAME,\r\n" +
					"STUDENT.SCHOOL_YEAR,\r\n" +
					"STUDENT.CLASS\r\n" +
					"FROM STUDENT,DEPARTMENT,COURSE\r\n" +
					"WHERE STUDENT.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID\r\n" +
					"AND STUDENT.DEPARTMENT_ID = COURSE.DEPARTMENT_ID\r\n" +
					"AND STUDENT.COURSE_ID = COURSE.COURSE_ID;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("STUDENT.SID");
				String name = rs.getString("STUDENT.SNAME");
				String dname = rs.getString("DEPARTMENT.DEPARTMENT_NAME");
				String cname = rs.getString("COURSE.COURSE_NAME");
				int year = rs.getInt("STUDENT.SCHOOL_YEAR");
				int clasies = rs.getInt("STUDENT.CLASS");
				resultlist.add(new StudentDTO(id, name, dname, cname, year, clasies));
			}
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return resultlist;
	}

	//Delete multiple
	public static void delete(int key, int id) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "admin",
					"Shroomish");
			sql = "DELETE FROM Student WHERE SID = ?;";
			ps = co.prepareStatement(sql);
			while (key == id) {
				ps.setInt(1, key);
				ps.executeUpdate();
				key++;
			}
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	//Single delete
	public static void delete(int key) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "admin",
					"Shroomish");
			sql = "DELETE FROM Student WHERE SID = ?;";
			ps = co.prepareStatement(sql);
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static void reset(int key) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "admin",
					"Shroomish");
			String sql1 = "UPDATE STUDENT SET PASSWORD = 0 WHERE SID = ?;";
			ps = con.prepareStatement(sql1);

			ps.setInt(1, key);

			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("数字を指定してください。");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<StudentDTO> search() {
		ArrayList<StudentDTO> resultlist = new ArrayList<StudentDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT sid,sname FROM STUDENT;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("SID");
				String name = rs.getString("SNAME");
				resultlist.add(new StudentDTO(id, name));
			}
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return resultlist;
	}

	public static ArrayList<StudentDTO> searching() {
		ArrayList<StudentDTO> resultlist = new ArrayList<StudentDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "admin",
					"Shroomish");
			String sql = "SELECT SID , SNAME FROM STUDENT;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("SID");
				String name = rs.getString("SNAME");
				resultlist.add(new StudentDTO(id, name));
			}
		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return resultlist;
	}

}
