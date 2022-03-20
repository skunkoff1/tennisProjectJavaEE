package com.tennis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tennis.dao.DaoFactory;
import com.tennis.dao.TournoiDao;
import com.tennis.dao.TournoiDaoImpl;
import com.tennis.model.Tournoi;

/**
 * Servlet implementation class AjouterTournoi
 */
@WebServlet("/ajoutertournoi")
public class AjouterTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterTournoi() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutertournoi.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String idString = (String)session.getAttribute("idTournoi");
		int id = 0; 
		if(idString != null) {
			id = Integer.parseInt(idString);
		}		
		String anneeString = request.getParameter("anneeTournoi");
		int annee = 0;
		if(anneeString != null) {
			annee = Integer.parseInt(anneeString);
		}
		String name = request.getParameter("nomTournoi");
		String type = request.getParameter("typeTournoi");	
		
		if(request.getParameter("add") != null) {
			Tournoi j = new Tournoi(annee, name, type);
			tournoiDao = new TournoiDaoImpl(DaoFactory.getInstance());
			tournoiDao.ajouter(j);
			response.sendRedirect("/tennis/listTournoi");
		} else if (request.getParameter("cancel") != null) {
			response.sendRedirect("/tennis/listTournoi");
		} else if(request.getParameter("confirm") != null) {
			Tournoi j = new Tournoi(id,annee, name, type);
			tournoiDao = new TournoiDaoImpl(DaoFactory.getInstance());
			tournoiDao.modifier(j);
			response.sendRedirect("/tennis/listTournoi");
		}
	}

}
