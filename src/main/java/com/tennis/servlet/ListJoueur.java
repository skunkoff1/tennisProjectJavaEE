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
import com.tennis.dao.JoueurDao;
import com.tennis.dao.JoueurDaoImpl;
import com.tennis.dao.TournoiDao;

/**
 * Servlet implementation class ListJoueur
 */
@WebServlet("/listJoueur")
public class ListJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
	private TournoiDao tournoiDao;
		

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ListJoueur() {
        super();
    }
    
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		DaoFactory daofactory = DaoFactory.getInstance();
		joueurDao = new JoueurDaoImpl(daofactory);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("liste", joueurDao.lister());
		HttpSession session = request.getSession();
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.removeAttribute("first");
		session.removeAttribute("sex");
		session.removeAttribute("mode");
		this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idString = request.getParameter("id");
		int id=0;
		if(idString!=null) {
			id = Integer.parseInt(idString);
		}
		String name = request.getParameter("name");
		String first = request.getParameter("first");
		String sex = request.getParameter("sex");
		String mode = request.getParameter("mode");
		
		if(request.getParameter("action1")!=null) {
				String search = request.getParameter("txtsearch");	
				request.setAttribute("liste", joueurDao.chercher(search));
				this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
		}
		
		if(mode != null) {
			if(mode.equals("remove")) {
				joueurDao.supprimer(id);
				response.sendRedirect("/tennis/listJoueur");
			}
		}
		if(idString!=null && name!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("id", idString);
			session.setAttribute("name", name);
			session.setAttribute("first", first);
			session.setAttribute("sex", sex);
			session.setAttribute("mode", mode);
			response.sendRedirect("/tennis/ajouterjoueur");
//				this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
		
		
				
	}
}
