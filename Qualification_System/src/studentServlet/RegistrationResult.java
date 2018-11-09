package studentServlet;

import java.io.IOException;
import java.time.LocalDateTime;
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
import masteDAO.StudentDAO;
import masteDTO.CredentialDTO;
import masteDTO.QualificationDTO;
import masteDTO.StudentDTO;

/**
 * Servlet implementation class RegistrationResult
 */
@WebServlet("/RegistrationResult")
public class RegistrationResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationResult() {
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
		int sid = 0;

		try {
			sid = (int) s.getAttribute("id");

			String name = re.getParameter("QuaName");
			String year = re.getParameter("year");
			String month = re.getParameter("month");
			String day = re.getParameter("day");
			String date = year + "/" + month + "/" + day;
			String result = "未受験";

			CredentialDTO results = CredentialDAO.search(name);
			StudentDTO seito = StudentDAO.search(sid);

			LocalDateTime dates = LocalDateTime.now();

			QualificationDAO.Regeistration(results.getId(), results.getName(), results.getLevel(), sid,
					seito.getDepartment(), seito.getCourse(), seito.getSchool_year(), seito.getSet_in(), String.valueOf(dates), String.valueOf(dates),
					String.valueOf(date), result);

			view = "/WEB-INF/student/registration.jsp";
			s.setAttribute("status", "完了");
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
			ArrayList<QualificationDTO> one = QualificationDAO.serach(sid);
			re.setAttribute("QuaDATE", one);
			ArrayList<CredentialDTO> result = CredentialDAO.serachAll();
			re.setAttribute("QuaName", result);
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
