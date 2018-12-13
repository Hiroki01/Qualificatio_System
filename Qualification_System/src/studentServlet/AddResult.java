package studentServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masteDAO.CourseDAO;
import masteDAO.DepartmentDAO;
import masteDAO.StudentDAO;
import masteDAO.SubjectDAO;
import masteDTO.CourseDTO;
import masteDTO.DepartmentDTO;
import masteDTO.SubjectDTO;

/**
 * Servlet implementation class AddResult
 */
@WebServlet("/AddResult")
public class AddResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest re, HttpServletResponse response) throws ServletException, IOException {
		//文字コード宣言
		re.setCharacterEncoding("UTF-8");
		//リンク先
		String view = null;
		//セッション宣言
		HttpSession s = re.getSession();

		try {
			//入力情報取得
			//学籍番号
			int id = Integer.parseInt(re.getParameter("id"));
			//姓名
			String a = re.getParameter("nameA");
			String b = re.getParameter("nameB");
			String name = a + " " + b;
			//カタカナ
			String c = re.getParameter("namekA");
			String d = re.getParameter("namekB");
			String namek = c + " " + d;
			//メールアドレス
			String email = re.getParameter("email");
			//主要学科名
			String subject = re.getParameter("subject");
			SubjectDTO sub = SubjectDAO.search(subject);
			//学科名
			String department = re.getParameter("department");
			DepartmentDTO depart = DepartmentDAO.search(department);
			//コース名
			String course = re.getParameter("course");
			CourseDTO cou = CourseDAO.search(depart.getId(),course);
			//学年
			int school_year = Integer.parseInt(re.getParameter("school_year"));
			//クラス
			int set_in = Integer.parseInt(re.getParameter("set_in"));
			//質問の種類
			int  question = Integer.parseInt(re.getParameter("question"));
			//質問の答え
			String answer = re.getParameter("answer");
			//パスワード
			String pass = re.getParameter("pass");

			//追加
			StudentDAO.Insert(id, name, namek, email,sub.getId(), depart.getId(), cou.getCourse_id(), school_year,
					set_in,question , answer,pass);
			//完了
			s.setAttribute("status", "完了");
			view = "/Add";
		} catch (NumberFormatException e) {
			view = "/Add";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/Add";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
			;
		} catch (Exception e) {
			view = "/Add";
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
