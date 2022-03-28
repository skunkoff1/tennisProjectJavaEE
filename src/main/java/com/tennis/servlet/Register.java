package com.tennis.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tennis.dao.DaoFactory;
import com.tennis.dao.EncryptDao;
import com.tennis.dao.UserDaoImpl;
import com.tennis.model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("redirect")!=null) {
			response.sendRedirect("/tennis/login");
			return;
		}
		String login = request.getParameter("txtLogin");
		String pseudo = request.getParameter("txtName");
		String password = request.getParameter("txtPassword");
		String password2 = request.getParameter("txtPassword2");
		
		if(!password.equals(password2)) {
			request.setAttribute("message", "Les mots de passe sont différents");
			doGet(request, response);
		} else {
			String hashedPassword = EncryptDao.sha(password);
			User user = new User(pseudo, login, hashedPassword);
			
			String message = userDao.registerUser(user);
			request.setAttribute("message", message);
			doGet(request, response);
		}
		
		
		
	}

}
