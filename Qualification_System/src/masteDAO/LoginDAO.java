
package masteDAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import masteDTO.LoginDTO;

public class LoginDAO {

	public static LoginDTO login(int id, String pass) {
		LoginDTO result = null;
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "students", "seito");
			String sql = "SELECT * FROM Student WHERE SID = ? AND PASSWORD = ?;";
			ps = co.prepareStatement(sql);
			ps.setInt(1, id);
			String value = pass;
			String key = "";
			try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-1");
	            byte[] results = digest.digest(value.getBytes());
	            key = String.format("%040x", new BigInteger(1, results));
	        } catch (Exception e){
	            e.printStackTrace();
	        }
			ps.setString(2, key);
			rs = ps.executeQuery();
			rs.next();
			int id1 = rs.getInt("SID");
			String pw = rs.getString("PASSWORD");
			result = new LoginDTO(id1, pw);

		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			System.out.println("存在しないユーザです。");
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
				if (co != null) {
					co.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static LoginDTO logins(int id, String pass) {
		LoginDTO result = null;
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification2?useSSL=false", "teacher", "sensei");
			String sql = "SELECT TID,PASSWORD,LOGIN,ADMIN FROM Teacher WHERE TID = ? AND PASSWORD = ?;";
			ps = co.prepareStatement(sql);
			ps.setInt(1, id);
			String value = pass;
			String key = "";
			try {
	            MessageDigest digest = MessageDigest.getInstance("SHA-1");
	            byte[] results = digest.digest(value.getBytes());
	            key = String.format("%040x", new BigInteger(1, results));
	        } catch (Exception e){
	            e.printStackTrace();
	        }
			ps.setString(2, key);
			rs = ps.executeQuery();
			rs.next();
			int tid = rs.getInt("TID");
			String pw = rs.getString("PASSWORD");
			String time = rs.getString("LOGIN");
			int admin = rs.getInt("ADMIN");
			result = new LoginDTO(tid, pw,time,admin);

		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			System.out.println("存在しないユーザです。");
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
				if (co != null) {
					co.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}

	public static LoginDTO admin(int id, String pass) {
		LoginDTO result = null;
		Connection co = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			co = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qualification?useSSL=false", "got", "pass");
			String sql = "SELECT * FROM Admin WHERE id = ? AND pass = ?;";
			ps = co.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			rs.next();
			int id1 = rs.getInt("id");
			String pw = rs.getString("pass");
			result = new LoginDTO(id1, pw);

		} catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			System.out.println("存在しないユーザです。");
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
				if (co != null) {
					co.close();
				}
			} catch (SQLException e) {
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
		return result;
	}
}
