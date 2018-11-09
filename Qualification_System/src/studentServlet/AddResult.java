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
import masteDTO.CourseDTO;
import masteDTO.DepartmentDTO;
import masteDTO.StudentDTO;

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
		re.setCharacterEncoding("UTF-8");
		String view = null;
		HttpSession s = re.getSession();

		try {
			int id = Integer.parseInt(re.getParameter("id"));
			String a = re.getParameter("nameA");
			String b = re.getParameter("nameB");
			String name = a + b;
			String c = re.getParameter("namekA");
			String d = re.getParameter("namekB");
			String namek = c + d;
			String email = re.getParameter("email");
			String department = re.getParameter("department");
			DepartmentDTO depart = DepartmentDAO.search(department);

			String course = re.getParameter("course");
			CourseDTO cou = CourseDAO.search(depart.getId(),course);

			int school_year = Integer.parseInt(re.getParameter("school_year"));
			int set_in = Integer.parseInt(re.getParameter("set_in"));
			int  question = Integer.parseInt(re.getParameter("question"));
			String answer = re.getParameter("answer");
			String pass = re.getParameter("pass");

			StudentDTO result = StudentDAO.Insert(id, name, namek, email, depart.getId(), cou.getCourse_id(), school_year,
					set_in,question , answer,pass);
			re.setAttribute("add", result);
			view = "/WEB-INF/student/addResult.jsp";
		} catch (NumberFormatException e) {
			view = "/WEB-INF/student/add.jsp";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/add.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
			;
		} catch (Exception e) {
			view = "/WEB-INF/student/add.jsp";
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
