package studentServlet;

import java.io.IOException;
import java.util.ArrayList;

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
import masteDTO.StudentDTO;
import masteDTO.SubjectDTO;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
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
		//ログイン時に保持したIDを代入する用
		int id;

		try {
			//ログイン時に入力された学籍番号を取得する
			id = (int) s.getAttribute("id");
			//学籍番号から自身の情報を取得する
			StudentDTO result = StudentDAO.profile(id);
			//JSPに表示するために保持
			re.setAttribute("pro", result);

			//変更用の情報を取得する
			ArrayList<SubjectDTO> subject = SubjectDAO.selectAll();
			re.setAttribute("subject", subject);

			ArrayList<DepartmentDTO> department = DepartmentDAO.selectAll();
			re.setAttribute("department", department);

			ArrayList<CourseDTO> course = CourseDAO.searchAll();
			re.setAttribute("course", course);

			//後々使うので
			s.setAttribute("depart", result.getDepartment());
			s.setAttribute("course", result.getCourse());

			//プロフィール画面を表示する
			view = "/WEB-INF/student/profile.jsp";
		} catch (NumberFormatException e) {
			view = "/WEB-INF/student/profile.jsp";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/profile.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);;
		} catch (Exception e) {
			view = "/WEB-INF/student/profile.jsp";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
