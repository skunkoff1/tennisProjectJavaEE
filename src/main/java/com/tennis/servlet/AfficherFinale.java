package com.tennis.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tennis.dao.DaoFactory;
import com.tennis.dao.TournoiDao;
import com.tennis.dao.TournoiDaoImpl;
import com.tennis.model.Joueur;

/**
 * Servlet implementation class AfficherFinale
 */
@WebServlet("/final")
public class AfficherFinale extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AfficherFinale() {
        super();
    }
    
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		tournoiDao = new TournoiDaoImpl(DaoFactory.getInstance());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/tournoiFinal.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idString = request.getParameter("id");
		int id=0;
		
		if(idString!=null) {
			id = Integer.parseInt(idString);
		}
		
		
		if (request.getParameter("cancel") != null) {
			response.sendRedirect("/tennis/listTournoi");
		}
		
		String annee = request.getParameter("annee");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		
		session.setAttribute("anneeFinale", annee);
		session.setAttribute("nomFinale", name);
		session.setAttribute("typeFinale", type);
		
		List<Joueur> joueurs = tournoiDao.getplayers(id);			
		session.setAttribute("joueurs", joueurs);		
		
		response.sendRedirect("/tennis/final");
	}

}
