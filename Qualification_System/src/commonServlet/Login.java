package commonServlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import masteDAO.LoginDAO;
import masteDAO.QualificationDAO;
import masteDAO.TeacherDAO;
import masteDTO.LoginDTO;
import masteDTO.QualificationDTO;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest re, HttpServletResponse response) throws ServletException, IOException {
		doPost(re, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest re, HttpServletResponse response)
			throws ServletException, IOException {
		//文字コード宣言
		re.setCharacterEncoding("UTF-8");
		//呼び出し先リンク
		String view = null;
		//セッション宣言
		HttpSession s = re.getSession();
		//ログイン判定用のDTO
		LoginDTO result = null;
		//受験情報テーブルからの操作
		ArrayList<QualificationDTO> log = null;

		try {
			//入力されたIDを取得
			int id = Integer.parseInt(re.getParameter("id"));
			//入力されたPASSを取得
			String pass = re.getParameter("pass");
			//生徒の判定
			if (id > 4100000) {
				//入力情報が正しいか判定する
				result = LoginDAO.login(id, pass);
				if (result != null) {
					view = "/WEB-INF/student/smenu.jsp";
				} else {
					view = "/WEB-INF/view/top.jsp";
				}
				//教師の判定
			} else if (id < 1000000) {
				//入力情報が正しいか判定する
				result = LoginDAO.logins(id, pass);
				if (result != null) {
					view = "/WEB-INF/teacher/tmenu.jsp";
					s.setAttribute("admin", result.getAdmin());
					//teahcer
				} else {
					view = "/WEB-INF/view/top.jsp";
				}
				//最終ログイン時間から現ログインまでの捜査情報を取得する
				log = QualificationDAO.logSearch(result.getId());
				//最終ログインを現時点にする
				TeacherDAO.log(id, pass);
			}
			//情報をセッション保存
			s.setAttribute("log", log);
			s.setAttribute("id", result.getId());
			s.setAttribute("pass", result.getPass());
			//IDに数値以外が入力された場合の例外処理
		} catch (NumberFormatException e) {
			view = "/WEB-INF/view/top.jsp";
			s.setAttribute("status", "No");
			e.getStackTrace();
			System.out.println(e);
			//入力情報が正しくない場合の例外処理
		} catch (NullPointerException e) {
			view = "/WEB-INF/view/top.jsp";
			s.setAttribute("status", "nai");
			e.getStackTrace();
			System.out.println(e);
			//例外処理
		} catch (Exception e) {
			view = "/WEB-INF/view/top.jsp";
			s.setAttribute("status", "Exception");
			e.getStackTrace();
			System.out.println(e);
			//ページ遷移の宣言（必ず実行される宣言領域）
		} finally {
			RequestDispatcher dispatcher = re.getRequestDispatcher(view);
			dispatcher.forward(re, response);
		}
	}

}
