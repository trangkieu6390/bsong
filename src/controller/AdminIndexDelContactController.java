package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ContactDao;

/**
 * Servlet implementation class AdminIndexDelContactController
 */
public class AdminIndexDelContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexDelContactController() {
        super();
        contactDao = new ContactDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		contactDao.delContactById(id);
		response.sendRedirect(request.getContextPath()+"/admin/contact?msg=1");
		
	}

}
