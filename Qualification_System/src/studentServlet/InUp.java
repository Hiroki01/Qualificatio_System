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

import masteDAO.CredentialDAO;
import masteDAO.QualificationDAO;
import masteDTO.CredentialDTO;
import masteDTO.QualificationDTO;

/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class InUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public InUp() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest re, HttpServletResponse response) throws ServletException, IOException {
		re.setCharacterEncoding("UTF-8");
		String view = null;
		HttpSession s = re.getSession();
		int sid = 0;

		try {
			sid = (int) s.getAttribute("id");
		ArrayList<QualificationDTO> one = QualificationDAO.serach(sid);
		re.setAttribute("QuaDATE", one);
		view = "/WEB-INF/student/registration.jsp";
		} catch (NumberFormatException e) {
			view = "/WEB-INF/student/registration.jsp";
			s.setAttribute("status", "Number");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/registration.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
		} catch (Exception e) {
			view = "/WEB-INF/student/registration.jsp";
			s.setAttribute("status", "Exception");
			e.getStackTrace();
			System.out.println(e);
		} finally {
			ArrayList<CredentialDTO> result = CredentialDAO.serachAll();
			re.setAttribute("QuaName", result);
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
