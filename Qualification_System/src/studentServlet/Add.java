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
import masteDAO.SubjectDAO;
import masteDTO.CourseDTO;
import masteDTO.DepartmentDTO;
import masteDTO.SubjectDTO;

/**
 * Servlet implementation class Add
 */
@WebServlet("/Add")
public class Add extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Add() {
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
			//登録時必要情報を各テーブルから抽出
			ArrayList<SubjectDTO> subject = SubjectDAO.selectAll();
			re.setAttribute("subject", subject);

			ArrayList<DepartmentDTO> department = DepartmentDAO.selectAll();
			re.setAttribute("department", department);

			ArrayList<CourseDTO> course = CourseDAO.searchAll();
			re.setAttribute("course", course);
			//新規登録画面を表示する
			view = "/WEB-INF/student/add.jsp";
		} catch (NullPointerException e) {
			view = "/WEB-INF/student/add.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
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
