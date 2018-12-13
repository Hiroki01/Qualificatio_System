package studentServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masteDAO.StudentDAO;

/**
 * Servlet implementation class ProfileUpdate
 */
@WebServlet("/ProfileUpdate")
public class ProfileUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProfileUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest re, HttpServletResponse response) throws ServletException, IOException {
		re.setCharacterEncoding("UTF-8");
		String view = null;
		HttpSession s = re.getSession();
		int id;
		int department;
		int course;

		try {
			//ログイン時に保持した学籍番号を取得
			id = (int) s.getAttribute("id");
			//入力された情報を取得する
			String nameA = re.getParameter("nameA");
			String nameB = re.getParameter("nameB");
			String namekA = re.getParameter("namekB");
			String namekB = re.getParameter("namekA");
			String name = nameA + " " + nameB;
			String namek = namekA + " " + namekB;
			String email = re.getParameter("email");
			int subject = Integer.parseInt(re.getParameter("subject"));
			String depart = re.getParameter("department");
			//学科情報が空なら、自身の情報を取得する
			if (depart == null) {
				department = (int) s.getAttribute("depart");
				course = (int) s.getAttribute("course");
			} else {
				department = Integer.parseInt(re.getParameter("department"));
				course = Integer.parseInt(re.getParameter("course"));
			}
			int school_year = Integer.parseInt(re.getParameter("school_year"));
			int set_in = Integer.parseInt(re.getParameter("class"));
			int question = Integer.parseInt(re.getParameter("question"));
			String answer = re.getParameter("answer");
			//変更前パスワード
			String pass = re.getParameter("pass");
			//パスワードを変更する際に入力されたもの
			String pass1 = re.getParameter("pass1");
			//変更時に入力されたパスワーﾄﾞに間違いがないかの判定用
			String pass2 = re.getParameter("pass2");
			//パスワードを変更するかしないかの判定
			if (pass1 == null || pass1.length() == 0) {
				StudentDAO.update(id, name, namek, email, subject, department, course, school_year,
						set_in, question, answer, pass);
				//変更時、間違いがないかの判定
			} else if (pass1 == pass2 || pass1.equals(pass2)) {
				StudentDAO.update(id, name, namek, email, subject, department, course, school_year,
						set_in, question, answer, pass);
			}
			//変更が完了した場合
			s.setAttribute("status", "完了");
			view = "/Profile";
		} catch (NumberFormatException e) {
			view = "/Profile";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/Profile";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
			;
		} catch (Exception e) {
			view = "/Profile";
			s.setAttribute("status", "Exception");
			e.getStackTrace();
			System.out.println(e);
		} finally {
			RequestDispatcher dispatcher = re.getRequestDispatcher(view);
			dispatcher.forward(re, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
