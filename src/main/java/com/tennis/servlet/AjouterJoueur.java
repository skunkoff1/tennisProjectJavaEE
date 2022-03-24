package com.tennis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tennis.dao.DaoFactory;
import com.tennis.dao.JoueurDao;
import com.tennis.dao.JoueurDaoImpl;
import com.tennis.model.Joueur;

/**
 * Servlet implementation class AjouterJoueur
 */
@WebServlet("/ajouterjoueur")
public class AjouterJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JoueurDao joueurDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterJoueur() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") == null) {
			response.sendRedirect("/tennis/login");
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idString = (String)session.getAttribute("id");
		int id = 0; 
		if(idString != null) {
			id = Integer.parseInt(idString);
		}		
		String name = request.getParameter("playerName");
		String firstName = request.getParameter("playerFirstName");
		String sex = request.getParameter("playerSex");	
		
		if(request.getParameter("add") != null) {
			Joueur j = new Joueur(name, firstName, sex);
			joueurDao = new JoueurDaoImpl(DaoFactory.getInstance());
			joueurDao.ajouter(j);
			response.sendRedirect("/tennis/listJoueur");
		} else if (request.getParameter("cancel") != null) {
			response.sendRedirect("/tennis/listJoueur");
		} else if(request.getParameter("confirm") != null) {
			Joueur j = new Joueur(id,name, firstName, sex);
			joueurDao = new JoueurDaoImpl(DaoFactory.getInstance());
			joueurDao.modifier(j);
			response.sendRedirect("/tennis/listJoueur");
		}
		
		
		
	}

}
