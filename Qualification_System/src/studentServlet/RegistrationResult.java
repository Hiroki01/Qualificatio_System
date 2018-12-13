package studentServlet;

import java.io.IOException;

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
			//学籍番号
			sid = (int) s.getAttribute("id");

			//入力された情報を取得
			String name = re.getParameter("QuaName");
			String date = re.getParameter("date");
			String result = "未受験";

			//登録に必要な情報を取得する
			CredentialDTO results = CredentialDAO.search(name);
			StudentDTO seito = StudentDAO.search(sid);

			//登録する
			QualificationDAO.Regeistration(results.getId(), results.getName(), results.getLevel(), sid,
					seito.getSubject(), seito.getDepartment(), seito.getCourse(), seito.getSchool_year(),
					seito.getSet_in(), date, result);

			view = "/Registration";
			s.setAttribute("status", "完了");
		} catch (NumberFormatException e) {
			view = "/Registration";
			s.setAttribute("status", "Number");
			e.getStackTrace();
			System.out.println(e);
		} catch (NullPointerException e) {
			view = "/Registration";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
		} catch (Exception e) {
			view = "/Registration";
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
