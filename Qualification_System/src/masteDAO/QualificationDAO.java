package masteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import masteDTO.QualificationDTO;

public class QualificationDAO {

	public static void Regeistration(int cid, String name, String level, int sid, int subject, int department,
			int course, int school_year, int set_in, String date, String result) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "INSERT INTO QUALIFICATION\r\n" +
					"SELECT null,?,?,?,?,?,?,?,?,?,now(),now(),cast(? as date),?\r\n" +
					"WHERE NOT EXISTS (SELECT 1 FROM QUALIFICATION WHERE CNAME =? AND EXAM_DATE = ?);";
			ps = co.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setString(2, name);
			ps.setString(3, level);
			ps.setInt(4, sid);
			ps.setInt(5, subject);
			ps.setInt(6, department);
			ps.setInt(7, course);
			ps.setInt(8, school_year);
			ps.setInt(9, set_in);
			ps.setString(10, date);
			ps.setString(11, result);
			ps.setString(12, name);
			ps.setString(13, date);

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

	public static ArrayList<QualificationDTO> search(int key) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "SELECT * FROM Qualification WHERE SID = ? order by EXAM_DATE desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString("CNAME");
				String level = rs.getString("DIFFICULTY");
				String day = rs.getString("EXAM_DATE");
				String result = rs.getString("RESULT");
				results.add(new QualificationDTO(name, level, day, result));
			}
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
		return results;
	}

	public static void Update(int sid, String name, String date, String dates, String result) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "UPDATE Qualification SET RESULT = ? , UPDATE_DATE = ? WHERE SID = ? AND CNAME = ? AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, result);
			ps.setString(2, dates);
			ps.setInt(3, sid);
			ps.setString(4, name);
			ps.setString(5, date);

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

	public static ArrayList<QualificationDTO> search1(int key) {
		ArrayList<QualificationDTO> resultList = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "SELECT DISTINCT(C.CNAME) AS 資格名,\r\n" +
					"CLASS.CLASSIFICATION_NAME AS 主催者名,\r\n" +
					"C1.CNAME AS 上位資格1,\r\n" +
					"C1.DIFFICULTY AS 上位難易度1,\r\n" +
					"D1.PASS_RATE AS 合格点1,\r\n" +
					"D1.PASSING_GRADE AS 合格基準1,\r\n" +
					"D1.URL AS URL1,\r\n" +
					"C2.CNAME AS 上位資格2,\r\n" +
					"C2.DIFFICULTY AS 上位難易度2,\r\n" +
					"D2.PASS_RATE AS 合格点2,\r\n" +
					"D2.PASSING_GRADE AS 合格基準2,\r\n" +
					"D2.URL AS URL2,\r\n" +
					"C3.CNAME AS 上位資格3,\r\n" +
					"C3.DIFFICULTY AS 上位難易度3,\r\n" +
					"D3.PASS_RATE AS 合格点3,\r\n" +
					"D3.PASSING_GRADE AS 合格基準3,\r\n" +
					"D3.URL AS URL3,\r\n" +
					"C4.CNAME AS 上位資格4,\r\n" +
					"C4.DIFFICULTY AS 上位難易度4,\r\n" +
					"D4.PASS_RATE AS 合格点4,\r\n" +
					"D4.PASSING_GRADE AS 合格基準4,\r\n" +
					"D4.URL AS URL4\r\n" +
					"FROM\r\n" +
					"QUALIFICATION AS Q,\r\n" +
					"CLASSIFICATION AS CLASS,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C1\r\n" +
					"ON C.SUPERIOR1 = C1.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"ON C.SUPERIOR2 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"ON C.SUPERIOR3 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"ON C.SUPERIOR4 = C4.CID\r\n" +
					"LEFT JOIN DESRCPITION AS D1\r\n" +
					"ON C.SUPERIOR1 = D1.CID\r\n" +
					"LEFT JOIN DESRCPITION AS D2\r\n" +
					"ON C.SUPERIOR2 = D2.CID\r\n" +
					"LEFT JOIN DESRCPITION AS D3\r\n" +
					"ON C.SUPERIOR3 = D3.CID\r\n" +
					"LEFT JOIN DESRCPITION AS D4\r\n" +
					"ON C.SUPERIOR4 = D4.CID\r\n" +
					"WHERE C.CID = Q.CID\r\n" +
					"AND CLASS.CLASSIFICATION_ID = C.CLASSIFICATION_ID\r\n" +
					"AND Q.RESULT = '合格'\r\n" +
					"AND Q.SID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				String cname = rs.getString("資格名");
				String classname = rs.getString("主催者名");
				String cname1 = rs.getString("上位資格1");
				String level1 = rs.getString("上位難易度1");
				String pass1 = rs.getString("合格点1");
				String passing1 = rs.getString("合格基準1");
				String link1 = rs.getString("URL1");
				String cname2 = rs.getString("上位資格2");
				String level2 = rs.getString("上位難易度2");
				String pass2 = rs.getString("合格点2");
				String passing2 = rs.getString("合格基準2");
				String link2 = rs.getString("URL2");
				String cname3 = rs.getString("上位資格3");
				String level3 = rs.getString("上位難易度3");
				String pass3 = rs.getString("合格点3");
				String passing3 = rs.getString("合格基準3");
				String link3 = rs.getString("URL3");
				String cname4 = rs.getString("上位資格4");
				String level4 = rs.getString("上位難易度4");
				String pass4 = rs.getString("合格点4");
				String passing4 = rs.getString("合格基準4");
				String link4 = rs.getString("URL4");
				resultList.add(new QualificationDTO(cname, classname, cname1, level1, pass1, passing1, link1, cname2,
						level2, pass2, passing2, link2, cname3,
						level3, pass3, passing3, link3, cname4, level4, pass4, passing4, link4));
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
		return resultList;
	}

	public static ArrayList<QualificationDTO> serach(int key, String result) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "SELECT * FROM Qualification WHERE SID = ? AND RESULT = ? order by EXAM_DATE desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setString(2, result);
			rs = ps.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("CID");
				String name = rs.getString("CNAME");
				results.add(new QualificationDTO(cid, name));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> department(int did, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.department_id = ? " +
					"AND C.CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO count(int did, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND DEPARTMENT_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, did);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static QualificationDTO count(String cid, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND COURSE_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, cid);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> course(int course_id, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.course_id = ? " +
					"AND C.CNAME = ? \r\n";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course_id);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasies(int subject, int year, int clasies, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ?\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO count(int subject, int year, int clasies, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND SUBJECT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> student(int id) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND S.SID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO count(int id) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> date(String key, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.exam_date = ? \r\n" +
					"AND C.CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> period(String date, String date2, String level) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.DIFFICULTY = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, level);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO dcount(String date, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, date);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static QualificationDTO pcount(String date, String date2, String level) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE DIFFICULTY = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, level);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> searchAll() {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C1.CNAME AS 上位資格1,\r\n" +
					"C2.CNAME AS 上位資格2,\r\n" +
					"C3.CNAME AS 上位資格3,\r\n" +
					"C4.CNAME AS 上位資格4,\r\n" +
					"C5.CNAME AS 下位資格1,\r\n" +
					"C6.CNAME AS 下位資格2,\r\n" +
					"C7.CNAME AS 下位資格3,\r\n" +
					"C8.CNAME AS 下位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C1\r\n" +
					"on C.SUPERIOR1 = C1.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR2 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR3 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR4 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.LOW_ORDER1 = C5.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C6\r\n" +
					"on C.LOW_ORDER2 = C6.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C7\r\n" +
					"on C.LOW_ORDER3 = C7.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C8\r\n" +
					"on C.LOW_ORDER4 = C8.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID \r\n" +
					"ORDER BY EXAM_DATE ASC;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				String l1 = rs.getString("下位資格1");
				String l2 = rs.getString("下位資格2");
				String l3 = rs.getString("下位資格3");
				String l4 = rs.getString("下位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4, l1, l2, l3, l4));
			}
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
		return results;
	}

	public static QualificationDTO search(String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT \r\n" +
					"資格情報.CID AS 資格ID,\r\n" +
					"資格情報.DIFFICULTY AS 資格難易度\r\n" +
					"FROM\r\n" +
					"CREDENTIAL_INFORMATION AS 資格情報\r\n" +
					"WHERE 資格情報.CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			rs = ps.executeQuery();
			rs.next();
			int cid = rs.getInt("資格ID");
			String level = rs.getString("資格難易度");
			results = new QualificationDTO(level, cid);
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
		return results;
	}

	public static void regeistration(int sid, int sid2, String cfname, String date, int cid, String level) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "INSERT INTO QUALIFICATION\r\n" +
					"SELECT null,?,?,?,?,?,?,?,?,?,now(),now(),cast(? as date),?\r\n" +
					"WHERE NOT EXISTS (SELECT 1 FROM QUALIFICATION WHERE SID = ? AND CNAME =? AND EXAM_DATE = ?);";
			ps = co.prepareStatement(sql);
			ArrayList<QualificationDTO> student = QualificationDAO.search(sid, sid2);
			for (QualificationDTO result : student) {
				ps.setInt(1, cid);
				ps.setString(2, cfname);
				ps.setString(3, level);
				ps.setInt(4, result.getSid());
				ps.setString(5, result.getSubject());
				ps.setInt(6, result.getDid());
				ps.setInt(7, result.getCoid());
				ps.setInt(8, result.getYear());
				ps.setInt(9, result.getClasies());
				ps.setString(10, date);
				ps.setString(11, "未受験");
				ps.setInt(12, result.getSid());
				ps.setString(13, cfname);
				ps.setString(14, date);

				ps.executeUpdate();
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

	private static ArrayList<QualificationDTO> search(int sid, int sid2) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT\r\n" +
					"生徒.SID AS 学籍番号,\r\n" +
					"生徒.SUBJECT_ID AS 3学科,\r\n" +
					"生徒.DEPARTMENT_ID AS 学科ID,\r\n" +
					"生徒.COURSE_ID AS コースID,\r\n" +
					"生徒.SCHOOL_YEAR AS 学年,\r\n" +
					"生徒.CLASS AS クラス\r\n" +
					"FROM STUDENT AS 生徒\r\n" +
					"WHERE SID\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setInt(2, sid2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int key = rs.getInt("学籍番号");
				int sub = rs.getInt("3学科");
				int did = rs.getInt("学科ID");
				int coid = rs.getInt("コースID");
				int year = rs.getInt("学年");
				int clasies = rs.getInt("クラス");
				results.add(new QualificationDTO(key, sub, did, coid, year, clasies));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> logSearch(int key) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT  DISTINCT\r\n" +
					"生徒.SID AS 学籍番号,\r\n" +
					"生徒.SNAME AS 氏名,\r\n" +
					"資格.CNAME AS 資格名,\r\n" +
					"資格.DIFFICULTY AS 資格難易度,\r\n" +
					"資格.EXAM_DATE AS 受験日,\r\n" +
					"資格.RESULT AS 結果\r\n" +
					"FROM STUDENT AS 生徒,QUALIFICATION AS 資格,TEACHER AS 教師\r\n" +
					"WHERE 資格.SID = 生徒.SID\r\n" +
					"AND 教師.TID = ?\r\n" +
					"AND 資格.UPDATE_DATE\r\n" +
					"BETWEEN 教師.LOGIN AND NOW()\r\n" +
					"ORDER BY 資格.EXAM_DATE DESC;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("氏名");
				String cname = rs.getString("資格名");
				String level = rs.getString("資格難易度");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");

				results.add(new QualificationDTO(sid, sname, cname, level, dates, result));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> search(int key, int school_year, int set, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT\r\n" +
					"資格情報.CID AS 資格ID,\r\n" +
					"資格情報.CNAME AS 資格名,\r\n" +
					"資格情報.DIFFICULTY AS 資格難易度,\r\n" +
					"生徒.SID AS 学籍番号,\r\n" +
					"生徒.SNAME AS 生徒氏名,\r\n" +
					"生徒.SUBJECT_ID AS 学科,\r\n" +
					"生徒.DEPARTMENT_ID AS 学科ID,\r\n" +
					"生徒.COURSE_ID AS コース名ID,\r\n" +
					"生徒.SCHOOL_YEAR AS 学年,\r\n" +
					"生徒.CLASS AS クラス\r\n" +
					"FROM\r\n" +
					"CREDENTIAL_INFORMATION AS 資格情報,\r\n" +
					"STUDENT AS 生徒\r\n" +
					"WHERE 資格情報.CNAME = ?\r\n" +
					"AND 生徒.SUBJECT_ID = ?\r\n" +
					"AND 生徒.SCHOOL_YEAR = ?\r\n" +
					"AND 生徒.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, key);
			ps.setInt(3, school_year);
			ps.setInt(4, set);
			rs = ps.executeQuery();
			while (rs.next()) {
				int cid = rs.getInt("資格ID");
				String cname = rs.getString("資格名");
				String level = rs.getString("資格難易度");
				int sid = rs.getInt("学籍番号");
				int subject = rs.getInt("学科");
				int depart = rs.getInt("学科ID");
				int course = rs.getInt("コース名ID");
				int school = rs.getInt("学年");
				int clasies = rs.getInt("クラス");
				results.add(new QualificationDTO(cid, cname, level, sid, subject, depart, course, school, clasies));
			}
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
		return results;
	}

	public static void regeistration(int sid, String cfname, String date, int cid, String level, int subject,
			int did, int coid, int school_year, int clasies) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "INSERT INTO QUALIFICATION\r\n" +
					"SELECT null,?,?,?,?,?,?,?,?,?,now(),now(),cast(? as date),?\r\n" +
					"WHERE NOT EXISTS (SELECT 1 FROM QUALIFICATION WHERE SID = ? AND CNAME =? AND EXAM_DATE = ?);";
			ps = co.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setString(2, cfname);
			ps.setString(3, level);
			ps.setInt(4, sid);
			ps.setInt(5, subject);
			ps.setInt(6, did);
			ps.setInt(7, coid);
			ps.setInt(8, school_year);
			ps.setInt(9, clasies);
			ps.setString(10, date);//today
			ps.setString(11, "未受験");
			ps.setInt(12, sid);
			ps.setString(13, cfname);
			ps.setString(14, date);

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

	public static ArrayList<QualificationDTO> search() {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID \r\n" +
					"ORDER BY EXAM_DATE ASC;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				results.add(new QualificationDTO(sid, sname, cname, dates, result));
			}
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
		return results;
	}

	public static void delete(String name, String date) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			sql = "DELETE FROM\r\n" +
					"QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND EXAM_DATE = ?;";
			ps = co.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, date);
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

	public static ArrayList<QualificationDTO> deleteConfirm(int key) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT\r\n" +
					"Q.CNAME AS 資格名,\r\n" +
					"S.SNAME AS 受験者氏名,\r\n" +
					"D.DEPARTMENT_NAME AS 所属学科,\r\n" +
					"C.COURSE_NAME AS 所属コース,\r\n" +
					"Q.SCHOOL_YEAR AS 学年,\r\n" +
					"Q.CLASS AS クラス,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果\r\n" +
					"FROM\r\n" +
					"QUALIFICATION AS Q,\r\n" +
					"STUDENT AS S,\r\n" +
					"DEPARTMENT AS D,\r\n" +
					"COURSE AS C\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND S.SID = ?\r\n" +
					"AND S.DEPARTMENT_ID = D.DEPARTMENT_ID\r\n" +
					"AND S.COURSE_ID = C.COURSE_ID;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				String cname = rs.getString("資格名");
				String sname = rs.getString("受験者氏名");
				String depart = rs.getString("所属学科");
				String course = rs.getString("所属コース");
				int school = rs.getInt("学年");
				int clasies = rs.getInt("クラス");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				results.add(new QualificationDTO(cname, sname, depart, course, school, clasies, dates, result));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> deleteConfirm(int id, int sid2) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT\r\n" +
					"Q.CNAME AS 資格名,\r\n" +
					"S.SNAME AS 受験者氏名,\r\n" +
					"D.DEPARTMENT_NAME AS 所属学科,\r\n" +
					"C.COURSE_NAME AS 所属コース,\r\n" +
					"Q.SCHOOL_YEAR AS 学年,\r\n" +
					"Q.CLASS AS クラス,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果\r\n" +
					"FROM\r\n" +
					"QUALIFICATION AS Q,\r\n" +
					"STUDENT AS S,\r\n" +
					"DEPARTMENT AS D,\r\n" +
					"COURSE AS C\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND S.DEPARTMENT_ID = D.DEPARTMENT_ID\r\n" +
					"AND S.COURSE_ID = C.COURSE_ID\r\n" +
					"AND S.SID\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(1, sid2);
			rs = ps.executeQuery();
			while (rs.next()) {
				String cname = rs.getString("資格名");
				String sname = rs.getString("受験者氏名");
				String depart = rs.getString("所属学科");
				String course = rs.getString("所属コース");
				int school = rs.getInt("学年");
				int clasies = rs.getInt("クラス");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				results.add(new QualificationDTO(cname, sname, depart, course, school, clasies, dates, result));
			}
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
		return results;
	}

	public static void delete(int key) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			sql = "DELETE FROM\r\n" +
					"QUALIFICATION\r\n" +
					"WHERE sid = ?";
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

	public static void delete(int key, int key2) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			sql = "DELETE FROM\r\n" +
					"QUALIFICATION\r\n" +
					"WHERE SID ID (\r\n" +
					"SELECT SID FROM STUDENT\r\n" +
					"WHERE SID BETWEEN ? AND ?\r\n" +
					");";
			ps = co.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setInt(2, key2);
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

	public static QualificationDTO search(int key, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT \r\n" +
					"資格情報.CID AS 資格ID,\r\n" +
					"資格情報.CNAME AS 資格名,\r\n" +
					"資格情報.DIFFICULTY AS 資格難易度,\r\n" +
					"生徒.SID AS 学籍番号,\r\n" +
					"生徒.SNAME AS 生徒氏名,\r\n" +
					"生徒.SUBJECT_ID AS 学科,\r\n" +
					"生徒.DEPARTMENT_ID AS 学科ID,\r\n" +
					"生徒.COURSE_ID AS コース名ID,\r\n" +
					"生徒.SCHOOL_YEAR AS 学年,\r\n" +
					"生徒.CLASS AS クラス\r\n" +
					"FROM\r\n" +
					"CREDENTIAL_INFORMATION AS 資格情報,\r\n" +
					"STUDENT AS 生徒\r\n" +
					"WHERE 資格情報.CNAME = ?\r\n" +
					"AND 生徒.SID = ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, key);
			rs = ps.executeQuery();
			rs.next();
			int cid = rs.getInt("資格ID");
			String cname = rs.getString("資格名");
			String level = rs.getString("資格難易度");
			int sid = rs.getInt("学籍番号");
			int subject = rs.getInt("学科");
			int depart = rs.getInt("学科ID");
			int course = rs.getInt("コース名ID");
			int school = rs.getInt("学年");
			int clasies = rs.getInt("クラス");
			results = new QualificationDTO(cid, cname, level, sid, subject, depart, course, school, clasies);
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
		return results;
	}

	public static void update(int sid, String cname, String examdate, String result) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql1 = "UPDATE QUALIFICATION\r\n" +
					"SET UPDATE_DATE = now(),\r\n" +
					"RESULT = ?\r\n" +
					"WHERE SID = ?\r\n" +
					"AND CNAME = ?\r\n" +
					"AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql1);

			ps.setString(1, result);
			ps.setInt(2, sid);
			ps.setString(3, cname);
			ps.setString(4, examdate);

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

	public static void update(String string, String cname, String examdate, String result) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql1 = "UPDATE QUALIFICATION\r\n" +
					"SET UPDATE_DATE = now(),\r\n" +
					"RESULT = ?\r\n" +
					"WHERE SID = ?\r\n" +
					"AND CNAME = ?\r\n" +
					"AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql1);

			ps.setString(1, result);
			int sid = Integer.parseInt(string);
			ps.setInt(2, sid);
			ps.setString(3, cname);
			ps.setString(4, examdate);

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

	public static ArrayList<QualificationDTO> department(int did) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.department_id = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countD(int did) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE DEPARTMENT_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> course(int course) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.COURSE_ID = ? ";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countC(int course) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE COURSE_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static QualificationDTO ccount(int course, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE COURSE_ID = ?\r\n" +
					"AND CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasies(int subject, int year, int clasies) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO count(int subject, int year, int clasies) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SUBJECT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> date(String key) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.EXAM_DATE = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO dcount(String date) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE EXAM_DATE = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> departmentR(int did, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.DEPARTMENT_ID = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countDR(int did, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE DEPARTMENT_ID = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> departmentR(int did, String cfname, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.DEPARTMENT_ID = ?\r\n" +
					"AND C.CNAME = ? \r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			ps.setString(2, cfname);
			ps.setString(3, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countDR(int did, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE DEPARTMENT_ID = ?\r\n" +
					"AND RESULT = ?\r\n" +
					"AND CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, did);
			ps.setString(2, outcome);
			ps.setString(3, cfname);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> courseR(int course, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.COURSE_ID = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countCR(int course, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE COURSE_ID = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> courseR(int course, String cfname, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.COURSE_ID = ?\r\n" +
					"AND CNAME = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			ps.setString(2, cfname);
			ps.setString(3, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO ccountR(int course, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE COURSE_ID = ?\r\n" +
					"AND RESULT = ?\r\n" +
					"AND CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, course);
			ps.setString(2, outcome);
			ps.setString(3, cfname);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasiesR(int subject, int year, int clasies, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			ps.setString(4, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countR(int subject, int year, int clasies, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SUBJECT_ID = ?\r\n" +
					"AND SCHOOL_YEAR = ?\r\n" +
					"AND CLASS = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			ps.setString(4, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasiesR(int subject, int year, int clasies, String cfname,
			String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ?\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			ps.setString(5, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countR(int subject, int year, int clasies, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND SUBJECT_ID = ?\r\n" +
					"AND SCHOOL_YEAR = ?\r\n" +
					"AND CLASS = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			ps.setString(5, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> studentR(int id, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND S.SID = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countR(int id, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SID = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> dateR(String key, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND C.CNAME = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO dcountR(String date, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE EXAM_DATE = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> dateR(String key, String cfname, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.exam_date = ? \r\n" +
					"AND C.CNAME = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			ps.setString(2, cfname);
			ps.setString(3, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO dcountR(String date, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND EXAM_DATE = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, date);
			ps.setString(3, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> period(String date, String date2) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcount(String date, String date2) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			ps.setString(2, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> periodR(String date, String date2, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.RESULT = ? \r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcountR(String date, String date2, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE RESULT = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> periodR(String date, String date2, String level, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.RESULT = ? \r\n" +
					"AND Q.DIFFICULTY = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setString(2, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcountR(String date, String date2, String level, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE RESULT = ?\r\n" +
					"AND DIFFICULTY = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setString(2, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> qualification(String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "\r\n" +
					"SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countQ(String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> qualificationR(String cfname, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "\r\n" +
					"SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countQR(String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> searchStudent() {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID) AS 資格番号,\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"D.DEPARTMENT_NAME AS 学科名,\r\n" +
					"CO.COURSE_NAME AS コース名,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"QUALIFICATION AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C,\r\n" +
					"DEPARTMENT AS D,\r\n" +
					"COURSE AS CO\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID \r\n" +
					"AND CO.COURSE_ID = S.COURSE_ID\r\n" +
					"AND D.DEPARTMENT_ID = S.DEPARTMENT_ID\r\n" +
					"ORDER BY EXAM_DATE DESC LIMIT 300;";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				int qid = rs.getInt("資格番号");
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String dname = rs.getString("学科名");
				String coname = rs.getString("コース名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				results.add(new QualificationDTO(qid, sid, sname, dname, coname, cname, dates, result));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasies(int subject, int year, int clasies, String cfname,
			String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ?\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?\r\n" +
					"AND Q.RESULT = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			ps.setString(5, outcome);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO count(int subject, int year, int clasies, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND SUBJECT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?\r\n" +
					"AND QUALIFICATION.RESULT = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			ps.setString(5, outcome);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static QualificationDTO searchD(int key) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"Q.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"QUALIFICATION AS Q\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND Q.QID = ?\r\n" +
					"ORDER BY EXAM_DATE ASC;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			rs.next();
			int qid = rs.getInt("Q.QID");
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String dates = rs.getString("受験日");
			String result = rs.getString("結果");
			results = new QualificationDTO(qid, sid, sname, cname, dates, result);
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
		return results;
	}

	public static void deleteQ(int key) {
		Connection co = null;
		PreparedStatement ps = null;
		String sql;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			sql = "DELETE FROM\r\n" +
					"QUALIFICATION\r\n" +
					"WHERE QID = ?";
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

	public static ArrayList<QualificationDTO> clasiesO(int subject, int year, int clasies) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countO(int subject, int year, int clasies) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SUBJECT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, subject);
			ps.setInt(2, year);
			ps.setInt(3, clasies);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasiesOut(int subject, int year, int clasies, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.RESULT = ?\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO countOut(int subject, int year, int clasies, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE RESULT = ? \r\n " +
					"SUBJECT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> clasiesFull(int subject, int year, int clasies, String cfname,
			String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.RESULT = ?\r\n" +
					"AND Q.CNAME  = ?\r\n" +
					"AND Q.SUBJECT_ID = ?\r\n" +
					"AND S.SCHOOL_YEAR = ?\r\n" +
					"AND S.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, outcome);
			ps.setString(2, cfname);
			ps.setInt(2, subject);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String date = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, date, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static ArrayList<QualificationDTO> periodC(String date, String date2, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ? \r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcountC(String date, String date2, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> periodC(String date, String date2, String cfname, String level) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ? \r\n" +
					"AND Q.DIFFICULTY = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcountC(String date, String date2, String cfname, String level) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND DIFFICULTY = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> periodRC(String date, String date2, String cfname, String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ? \r\n" +
					"AND Q.RESULT = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcountRC(String date, String date2, String cfname, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND RESULT = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

	public static ArrayList<QualificationDTO> period(String date, String date2, String cfname, String level,
			String outcome) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT DISTINCT(Q.QID),\r\n" +
					"S.SID AS 学籍番号,\r\n" +
					"S.SNAME AS 生徒氏名,\r\n" +
					"C.CID AS 資格ID,\r\n" +
					"C.CNAME AS 資格名,\r\n" +
					"Q.EXAM_DATE AS 受験日,\r\n" +
					"Q.RESULT AS 結果,\r\n" +
					"C2.CNAME AS 上位資格1,\r\n" +
					"C3.CNAME AS 上位資格2,\r\n" +
					"C4.CNAME AS 上位資格3,\r\n" +
					"C5.CNAME AS 上位資格4\r\n" +
					"FROM\r\n" +
					"STUDENT AS S,\r\n" +
					"Qualification AS Q,\r\n" +
					"CREDENTIAL_INFORMATION AS C\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C2\r\n" +
					"on C.SUPERIOR1 = C2.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C3\r\n" +
					"on C.SUPERIOR2 = C3.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C4\r\n" +
					"on C.SUPERIOR3 = C4.CID\r\n" +
					"LEFT JOIN CREDENTIAL_INFORMATION AS C5\r\n" +
					"on C.SUPERIOR4 = C5.CID\r\n" +
					"WHERE S.SID = Q.SID\r\n" +
					"AND C.CID = Q.CID\r\n" +
					"AND Q.CNAME = ? \r\n" +
					"AND Q.RESULT = ?\r\n" +
					"AND Q.DIFFICULTY = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ? ;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			ps.setString(3, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("学籍番号");
				String sname = rs.getString("生徒氏名");
				String cname = rs.getString("資格名");
				String dates = rs.getString("受験日");
				String result = rs.getString("結果");
				String s1 = rs.getString("上位資格1");
				String s2 = rs.getString("上位資格2");
				String s3 = rs.getString("上位資格3");
				String s4 = rs.getString("上位資格4");
				results.add(new QualificationDTO(sid, sname, cname, dates, result, s1, s2, s3, s4));
			}
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
		return results;
	}

	public static QualificationDTO pcount(String date, String date2, String cfname, String level, String outcome) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND RESULT = ?\r\n" +
					"AND DIFFICULTY = ?\r\n" +
					"AND EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2, outcome);
			ps.setString(3, level);
			ps.setString(3, date);
			ps.setString(4, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new QualificationDTO(counta, counto, counts);
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
		return results;
	}

}
