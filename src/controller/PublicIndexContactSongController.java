package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDao;

/**
 * Servlet implementation class PublicIndexContactSongController
 */
public class PublicIndexContactSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexContactSongController() {
        super();
        contactDao = new ContactDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		RequestDispatcher rd = request.getRequestDispatcher("/public/contact.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String website = request.getParameter("website");
		String message = request.getParameter("message");
		contactDao.insertItem(name,email,website,message);
		response.sendRedirect(request.getContextPath()+"/public/lien-he?msg=1");
		
	}

}
