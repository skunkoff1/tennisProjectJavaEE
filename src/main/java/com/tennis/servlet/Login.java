package com.tennis.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tennis.dao.DaoFactory;
import com.tennis.dao.UserDaoImpl;
import com.tennis.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }
    

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userDao = new UserDaoImpl(DaoFactory.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("txtLogin");
		String password = request.getParameter("txtPassword");
		
		User user = userDao.isValidLogin(login, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user.getLogin());
			session.setAttribute("currentPage", "joueur");
			response.sendRedirect("/tennis/listJoueur");
		}
		else {
			request.setAttribute("message", "mauvais identifiants de connection");
			doGet(request, response);
		}
		
		
	}


}
