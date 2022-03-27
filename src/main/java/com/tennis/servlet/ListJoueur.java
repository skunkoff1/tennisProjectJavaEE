package com.tennis.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
import com.tennis.model.Joueur;

/**
 * Servlet implementation class ListJoueur
 */
@WebServlet("/listJoueur")
public class ListJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
	private ArrayList<Joueur> listeJoueur;
		

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
		session.setAttribute("currentPage", "joueur");
		session.removeAttribute("id");
		session.removeAttribute("name");
		session.removeAttribute("first");
		session.removeAttribute("sex");
		session.removeAttribute("mode");
		if(session.getAttribute("user") == null) {
			response.sendRedirect("/tennis/login");
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);	
		}
		
		
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
				listeJoueur = (ArrayList<Joueur>) joueurDao.chercher(search);
				if(listeJoueur.size()==0) {
					request.setAttribute("error", "aucun joueur trouvé");
				}
				request.setAttribute("liste", joueurDao.chercher(search));
				this.getServletContext().getRequestDispatcher("/WEB-INF/listjoueur.jsp").forward(request, response);
		}
		
		if(request.getParameter("action2")!=null) {
			HttpSession session = request.getSession();
			session.removeAttribute("isConnected");
			response.sendRedirect("/tennis/login");
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
