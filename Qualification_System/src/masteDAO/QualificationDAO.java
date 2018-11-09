package masteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import masteDTO.QualificationDTO;

public class QualificationDAO {

	public static void Regeistration(int cid, String name, String level, int sid, int department, int course,
			int school_year, int set_in, String dates, String dates2, String date, String result) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students",
					"seito");
			String sql = "INSERT INTO Qualification VALUES(null,?,?,?,?,?,?,?,?,cast(? as date),cast(? as date),cast(? as date),?)";
			ps = co.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setString(2, name);
			ps.setString(3, level);
			ps.setInt(4, sid);
			ps.setInt(5, department);
			ps.setInt(6, course);
			ps.setInt(7, school_year);
			ps.setInt(8, set_in);
			ps.setString(9, dates);
			ps.setString(10, dates2);
			ps.setString(11, date);
			ps.setString(12,result);

			ps.executeUpdate();
			new QualificationDTO(0,cid,name,level,sid,department,course,school_year,set_in,dates,dates2,date,result);
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

	public static ArrayList<QualificationDTO> serach(int key) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT * FROM Qualification WHERE SID = ? order by EXAM_DATE desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while(rs.next()){
			int qid = rs.getInt("QID");
			int cid = rs.getInt("CID");
			String name = rs.getString("CNAME");
			String d = rs.getString("DIFFICULTY");
			int sid = rs.getInt("SID");
			int did = rs.getInt("DEPARTMENT_ID");
			int coid = rs.getInt("COURSE_ID");
			int s = rs.getInt("SCHOOL_YEAR");
			int c = rs.getInt("CLASS");
			String day1 = rs.getString("REGISTRATION_DATE");
			String day2 = rs.getString("UPDATE_DATE");
			String day3 = rs.getString("EXAM_DATE");
			String result = rs.getString("RESULT");

			results.add(new  QualificationDTO(qid,cid,name,d,sid,did,coid,s,c,day1,day2,day3,result));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			Class.forName("com.mysql.jdbc.Driver");
			String sql = "UPDATE Qualification SET RESULT = ? , UPDATE_DATE = ? WHERE SID = ? AND CNAME = ? AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, result);
			ps.setString(2,dates);
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

	public static ArrayList<QualificationDTO> serach(int key, String result) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT * FROM Qualification WHERE SID = ? AND RESULT = ? order by EXAM_DATE desc";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			ps.setString(2,result);
			rs = ps.executeQuery();
			while(rs.next()){
			int cid = rs.getInt("CID");
			String name = rs.getString("CNAME");
			results.add(new  QualificationDTO(cid,name));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
					"AND C.CNAME = ? \r\n";
			ps = con.prepareStatement(sql);
			ps.setInt(1,did);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String date = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,date,result,s1,s2,s3,s4));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND DEPARTMENT_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2,did);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND COURSE_ID = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setString(2,cid);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
			ps.setInt(1,course_id);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String date = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,date,result,s1,s2,s3,s4));
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

	public static ArrayList<QualificationDTO> clasies(int id, int year, int clasies, String cfname) {
		ArrayList<QualificationDTO> results = new ArrayList<QualificationDTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
					"AND C.CID = Q.CID\r\n"+
					"AND Q.CNAME = ?" +
					"AND Q.DEPARTMENT_ID = ?" +
					"AND S.SCHOOL_YEAR = ?" +
					"AND S.CLASS = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2,id);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String date = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,date,result,s1,s2,s3,s4));
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

	public static QualificationDTO count(int id, int year, int clasies, String cfname) {
		QualificationDTO results = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND DEPARTMENT_ID = ?\r\n" +
					"AND QUALIFICATION.SCHOOL_YEAR = ?\r\n" +
					"AND QUALIFICATION.CLASS = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1, cfname);
			ps.setInt(2,id);
			ps.setInt(3, year);
			ps.setInt(4, clasies);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String date = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,date,result,s1,s2,s3,s4));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE SID = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
			ps.setString(1,key);
			ps.setString(2, cfname);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String date = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,date,result,s1,s2,s3,s4));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
			ps.setString(1,level);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("学籍番号");
			String sname = rs.getString("生徒氏名");
			String cname = rs.getString("資格名");
			String dates = rs.getString("受験日");
			String result = rs.getString("結果");
			String s1 = rs.getString("上位資格1");
			String s2 = rs.getString("上位資格2");
			String s3 = rs.getString("上位資格3");
			String s4 = rs.getString("上位資格4");
			results.add(new  QualificationDTO(sid,sname,cname,dates,result,s1,s2,s3,s4));
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE CNAME = ?\r\n" +
					"AND EXAM_DATE = ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1,cfname);
			ps.setString(2, date);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT RESULT,\r\n" +
					"COUNT(RESULT='合格' or null)AS 合格者,\r\n" +
					"COUNT(RESULT='不合格' or null)AS 不合格者,\r\n" +
					"COUNT(RESULT='未受験' or null)AS 未受験者\r\n" +
					"FROM QUALIFICATION\r\n" +
					"WHERE Q.DIFFICULTY = ?\r\n" +
					"AND Q.EXAM_DATE\r\n" +
					"BETWEEN ? AND ?;";
			ps = con.prepareStatement(sql);
			ps.setString(1,level);
			ps.setString(2, date);
			ps.setString(3, date2);
			rs = ps.executeQuery();
			rs.next();
			int counta = rs.getInt("合格者");
			int counto = rs.getInt("不合格者");
			int counts = rs.getInt("未受験者");
			results = new  QualificationDTO(counta,counto,counts);
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
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
			while(rs.next()){
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
			results.add(new  QualificationDTO(sid,sname,cname,dates,result,s1,s2,s3,s4,l1,l2,l3,l4));
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

	public static void regeistration(int sid, int sid2, String date) {
		Connection co = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher",
					"sensei");
			String sql = "INSERT INTO Qualification VALUES(null,?,?,?,?,?,?,?,?,cast(? as date),cast(? as date),cast(? as date),?)";
			ps = co.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.setString(2, name);
			ps.setString(3, level);
			ps.setInt(4, sid);
			ps.setInt(5, department);
			ps.setInt(6, course);
			ps.setInt(7, school_year);
			ps.setInt(8, set_in);
			ps.setString(9, dates);
			ps.setString(10, dates2);
			ps.setString(11, date);
			ps.setString(12,result);

			ps.executeUpdate();
			new QualificationDTO(0,cid,name,level,sid,department,course,school_year,set_in,dates,dates2,date,result);
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
}
