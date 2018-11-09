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
import masteDTO.StudentDTO;

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
		StudentDTO result = null;
		HttpSession s = re.getSession();
		int id;

		try {
			id = (int) s.getAttribute("id");
			String name = re.getParameter("name");
			String namek = re.getParameter("namek");
			String email = re.getParameter("email");
			int department =Integer.parseInt(re.getParameter("foo"));
			System.out.println(re.getParameter("bar"));
			int course = Integer.parseInt(re.getParameter("bar"));
			int school_year = Integer.parseInt(re.getParameter("school_year"));
			int set_in = Integer.parseInt(re.getParameter("class"));
			int question = Integer.parseInt(re.getParameter("question"));
			String answer = re.getParameter("answer");
			String pass = re.getParameter("pass");
			String pass1 = re.getParameter("pass1");
			String pass2 = re.getParameter("pass2");
			if (pass1 == null || pass1.length() == 0) {
				result = StudentDAO.update(id,name, namek,email, department, course, school_year,
						set_in,question, answer,pass);
				//No password reset
			} else if (pass1 == pass2 || pass1.equals(pass2)) {
				result = StudentDAO.update(id,name, namek, email, department, course, school_year,
						set_in,question,answer, pass,pass2);
			}
			re.setAttribute("pro", result);
			view = "/Profile";
		} catch (NumberFormatException e) {
			view = "/WEB-INF/student/smenu.jsp";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/smenu.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);;
		} catch (Exception e) {
			view = "/WEB-INF/student/smenu.jsp";
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
