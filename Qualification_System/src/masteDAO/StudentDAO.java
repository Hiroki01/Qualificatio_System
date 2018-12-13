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

	public static void Insert(int id, String name, String namek, String email, int subject, int department,
			int course, int school_year, int set_in, int question, String answer, String pass) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "INSERT INTO STUDENT\r\n" +
					"SELECT ?,?,?,?,?,?,?,?,?,?,?,?\r\n" +
					"WHERE NOT EXISTS (SELECT 1 FROM STUDENT WHERE SID = ?);";
			ps = co.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.setString(3, namek);
			ps.setString(4, email);
			ps.setInt(5, subject);
			ps.setInt(6, department);
			ps.setInt(7, course);
			ps.setInt(8, school_year);
			ps.setInt(9, set_in);
			ps.setInt(10, question);
			ps.setString(11, answer);
			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(12, key);
			ps.setInt(13, id);

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
			int subject = rs.getInt("SUBJECT_ID");
			int did = rs.getInt("DEPARTMENT_ID");
			int coid = rs.getInt("COURSE_ID");
			int year = rs.getInt("SCHOOL_YEAR");
			int clasies = rs.getInt("CLASS");
			int question = rs.getInt("QUESTION");
			String answer = rs.getString("ANSWER");
			String pass = rs.getString("PASSWORD");
			result = new StudentDTO(id, name, namek, mail, subject,did, coid, year, clasies, question, answer, pass);
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

	public static StudentDTO updateS(int id, String name, String namek, String email, int subject , int department, int course,
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
					"SUBJECT_ID = ?,\r\n" +
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
			ps.setInt(4, subject);
			ps.setInt(5, department);
			ps.setInt(6, course);
			ps.setInt(7, school_year);
			ps.setInt(8, set_in);
			ps.setInt(9, question);
			ps.setString(10, answer);
			ps.setInt(11, id);
			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(12, key);

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

	public static StudentDTO update(int id, String name, String namek, String email,int subject, int department, int course,
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
					"SUBJECT_ID = ?,\r\n" +
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
			ps.setInt(4, subject);
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
			ps.setInt(12, id);

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

			String value = String.valueOf(key);
			String pass = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				pass = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(1, pass);

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

	public static StudentDTO profile(int key) {
		StudentDTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT\r\n" +
					"生徒.SNAME AS 氏名,\r\n" +
					"生徒.SKNAME AS フリガナ,\r\n" +
					"生徒.SEMAIL AS メール,\r\n" +
					"生徒.SUBJECT_ID AS 3学科ID,\r\n" +
					"3学科.SUBJECT_NAME AS 3学科,\r\n" +
					"学科.DEPARTMENT_NAME AS 学科名,\r\n" +
					"生徒.DEPARTMENT_ID AS 学科ID,\r\n" +
					"コース.COURSE_NAME AS コース名,\r\n" +
					"生徒.COURSE_ID AS コースID,\r\n" +
					"生徒.SCHOOL_YEAR AS 学年,\r\n" +
					"生徒.CLASS AS クラス,\r\n" +
					"生徒.QUESTION AS 種類\r\n" +
					"FROM\r\n" +
					"STUDENT AS 生徒,\r\n" +
					"DEPARTMENT AS 学科,\r\n" +
					"COURSE AS コース,\r\n" +
					"SUBJECT AS 3学科\r\n" +
					"WHERE 生徒.SID = ?\r\n" +
					"AND 生徒.DEPARTMENT_ID = 学科.DEPARTMENT_ID\r\n" +
					"AND 生徒.SUBJECT_ID = 3学科.SUBJECT_ID\r\n" +
					"AND 生徒.COURSE_ID = コース.COURSE_ID;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			rs.next();
				String name = rs.getString("氏名");
				String namek = rs.getString("フリガナ");
				String mail = rs.getString("メール");
				int subid = rs.getInt("3学科ID");
				String subject = rs.getString("3学科");
				String dname = rs.getString("学科名");
				int did = rs.getInt("学科ID");
				String cname = rs.getString("コース名");
				int coid = rs.getInt("コースID");
				int year = rs.getInt("学年");
				int clasies = rs.getInt("クラス");
				int question = rs.getInt("種類");
				result = new StudentDTO(name,namek,mail,subject,subid,dname,did,cname,coid,year,clasies,question);
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

	public static StudentDTO pass(String email, int question, String answer) {
		StudentDTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT SID\r\n" +
					"FROM STUDENT\r\n" +
					"WHERE SEMAIL = ?\r\n" +
					"AND QUESTION = ?\r\n" +
					"AND ANSWER = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, question);
			ps.setString(3, answer);
			rs = ps.executeQuery();
			rs.next();
			int sid = rs.getInt("SID");
			result = new StudentDTO(sid);
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

	public static void passUpdate(String email, int question, String answer, String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql1 = "UPDATE STUDENT SET PASSWORD = ? "
					+ "WHERE SEMAIL = ?\r\n" +
					"AND QUESTION = ?\r\n" +
					"AND ANSWER = ?;";
			ps = con.prepareStatement(sql1);

			String value = pass;
			String key = "";
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-1");
				byte[] results = digest.digest(value.getBytes());
				key = String.format("%040x", new BigInteger(1, results));
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps.setString(1, key);
			ps.setString(2, email);
			ps.setInt(3, question);
			ps.setString(4, answer);

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
}
