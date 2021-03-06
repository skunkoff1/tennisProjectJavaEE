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
import com.tennis.dao.TournoiDao;
import com.tennis.dao.TournoiDaoImpl;

/**
 * Servlet implementation class ListTournoi
 */
@WebServlet("/listTournoi")
public class ListTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiDao tournoiDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListTournoi() {
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
		request.setAttribute("listeTournoi", tournoiDao.lister());
		HttpSession session = request.getSession();
		session.setAttribute("currentPage", "tournoi");
		session.removeAttribute("idTournoi");
		session.removeAttribute("anneeTournoi");
		session.removeAttribute("nomTournoi");
		session.removeAttribute("typeTournoi");
		session.removeAttribute("modeTournoi");
		if(session.getAttribute("user") == null) {
			response.sendRedirect("/tennis/login");
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
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
		String annee = request.getParameter("annee");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String mode = request.getParameter("mode");

		HttpSession session = request.getSession();
		
		if(request.getParameter("action1")!=null) {
			String search = request.getParameter("txtsearch");	
			request.setAttribute("listeTournoi", tournoiDao.chercher(search));
			this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
		}
		
		if(request.getParameter("action2")!=null) {
			session.removeAttribute("isConnected");
			response.sendRedirect("/tennis/login");
		}
		
		if(mode != null) {
			if(mode.equals("remove")) {
				tournoiDao.supprimer(id);
				response.sendRedirect("/tennis/listTournoi");
//				return;
			}
		}
		
		if(idString!=null && name!=null) {
			session.setAttribute("idTournoi", idString);
			session.setAttribute("anneeTournoi", annee);
			session.setAttribute("nomTournoi", name);
			session.setAttribute("typeTournoi", type);
			session.setAttribute("modeTournoi", mode);
			response.sendRedirect("/tennis/ajoutertournoi");
//				this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterjoueur.jsp").forward(request, response);
		}
		
	}

}
