package masteDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import masteDTO.DTO;

public class DAO {

	public static ArrayList<DTO> serach(int key) {
		ArrayList<DTO> resultList = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT\r\n" +
					"QUALIFICATION.CNAME, \r\n" +
					"CREDENTIAL_INFORMATION.CNAME,\r\n" +
					"CLASSIFICATION.CLASSIFICATION_NAME,\r\n" +
					"CREDENTIAL_INFORMATION.DIFFICULTY \r\n" +
					"from \r\n" +
					"QUALIFICATION,CREDENTIAL_INFORMATION,CLASSIFICATION\r\n" +
					"where QUALIFICATION.SID = ? \r\n" +
					"AND QUALIFICATION.RESULT = '合格'\r\n" +
					"AND CREDENTIAL_INFORMATION.CLASSIFICATION_ID = CREDENTIAL_INFORMATION.CLASSIFICATION_ID\r\n" +
					"AND CREDENTIAL_INFORMATION.LOW_ORDER1 = Qualification.CID\r\n;";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			while (rs.next()) {
				String cname = rs.getString("QUALIFICATION.CNAME");
				String sname = rs.getString("CREDENTIAL_INFORMATION.CNAME");
				String cfname = rs.getString("CLASSIFICATION.CLASSIFICATION_NAME");
				String level = rs.getString("CREDENTIAL_INFORMATION.DIFFICULTY");
				resultList.add(new DTO(cname,sname,cfname,level));
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

	public static DTO profile(int key) {
		DTO result = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT\r\n" +
					"STUDENT.SNAME,\r\n" +
					"STUDENT.SKNAME,\r\n" +
					"STUDENT.SEMAIL,\r\n" +
					"STUDENT.DEPARTMENT_ID,\r\n" +
					"STUDENT.COURSE_ID,\r\n" +
					"DEPARTMENT.DEPARTMENT_ID,\r\n" +
					"DEPARTMENT.DEPARTMENT_NAME,\r\n" +
					"COURSE.COURSE_ID,\r\n" +
					"COURSE.COURSE_NAME,\r\n" +
					"STUDENT.SCHOOL_YEAR,\r\n" +
					"STUDENT.CLASS,\r\n" +
					"STUDENT.QUESTION\r\n" +
					"FROM\r\n" +
					"STUDENT,DEPARTMENT,COURSE\r\n" +
					"WHERE STUDENT.SID = ?\r\n" +
					"AND STUDENT.DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID\r\n" +
					"AND STUDENT.COURSE_ID = COURSE.COURSE_ID;\r\n";
			ps = con.prepareStatement(sql);
			ps.setInt(1, key);
			rs = ps.executeQuery();
			rs.next();
				String name = rs.getString("STUDENT.SNAME");
				String namek = rs.getString("STUDENT.SKNAME");
				String mail = rs.getString("STUDENT.SEMAIL");
				int did = rs.getInt("DEPARTMENT.DEPARTMENT_ID");
				String dname = rs.getString("DEPARTMENT.DEPARTMENT_NAME");
				int coid = rs.getInt("COURSE.COURSE_ID");
				String cname = rs.getString("COURSE.COURSE_NAME");
				int year = rs.getInt("STUDENT.SCHOOL_YEAR");
				int clasies = rs.getInt("STUDENT.CLASS");
				int question = rs.getInt("STUDENT.QUESTION");
				result = new DTO(name,namek,mail,did,dname,coid,cname,year,clasies,question);
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

	public static ArrayList<DTO> logSerach(String date) {
		ArrayList<DTO> results = new ArrayList<DTO>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT  DISTINCT\r\n" +
					"STUDENT.SID,\r\n" +
					"STUDENT.SNAME,\r\n" +
					"QUALIFICATION.CNAME,\r\n" +
					"QUALIFICATION.DIFFICULTY,\r\n" +
					"QUALIFICATION.EXAM_DATE,\r\n" +
					"QUALIFICATION.RESULT\r\n" +
					"FROM STUDENT,QUALIFICATION,TEACHER\r\n" +
					"WHERE QUALIFICATION.SID = STUDENT.SID\r\n" +
					"AND QUALIFICATION.UPDATE_DATE\r\n" +
					"BETWEEN TEACHER.LOGIN AND ?\r\n" +
					"ORDER BY EXAM_DATE DESC;";
			ps = con.prepareStatement(sql);
			ps.setString(1, date);
			rs = ps.executeQuery();
			while(rs.next()){
			int sid = rs.getInt("STUDENT.SID");
			String sname = rs.getString("STUDENT.SNAME");
			String cname = rs.getString("QUALIFICATION.CNAME");
			String d = rs.getString("QUALIFICATION.DIFFICULTY");
			String dates = rs.getString("QUALIFICATION.EXAM_DATE");
			String result = rs.getString("QUALIFICATION.RESULT");

			results.add(new  DTO(sid,sname,cname,d,dates,result));
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


}
