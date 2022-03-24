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
import com.tennis.dao.MatchDao;
import com.tennis.dao.MatchDaoImpl;

/**
 * Servlet implementation class ListMatch
 */
@WebServlet("/listmatch")
public class ListMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MatchDao matchDao;
	private String mode;
	private String search;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListMatch() {
        super();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DaoFactory daofactory = DaoFactory.getInstance();
		matchDao = new MatchDaoImpl(daofactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		mode = request.getParameter("mode");
		search = request.getParameter("search");
		if(mode == null) {
			mode ="all";
		}
		if(search == null) {
			search = "";
		}		
		
		request.setAttribute("search",search);
		request.setAttribute("listeMatch", matchDao.lister(mode, search));
		HttpSession session = request.getSession();
		session.setAttribute("currentPage", "match");
		if(session.getAttribute("user") == null) {
			response.sendRedirect("/tennis/login");
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/listmatch.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		search = request.getParameter("txtsearch");
		if(mode == null) {
			mode ="all";
		}
		if(search == null || search =="initialisethesearch") {
			search = "";
		}
		
		if(request.getParameter("action2")!=null) {
			HttpSession session = request.getSession();
			session.removeAttribute("isConnected");
			response.sendRedirect("/tennis/login");
		} else {
			request.setAttribute("search",search);
			request.setAttribute("listeMatch", matchDao.lister(mode, search));
			this.getServletContext().getRequestDispatcher("/WEB-INF/listmatch.jsp").forward(request, response);
		}
		
		
	}

}
