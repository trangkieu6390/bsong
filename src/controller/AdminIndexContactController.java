package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Contact;
import model.dao.ContactDao;

/**
 * Servlet implementation class AdminIndexContactController
 */
public class AdminIndexContactController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDao contactDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexContactController() {
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
	
		String index_page1 = request.getParameter("index_page");
		
		
		int index_page = 0; 
		if(index_page1 == null) {
			index_page1 = "1";
		}
		
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		
		int number_page = contactDao.countPage();
		request.setAttribute("number_page", number_page); 
		
		ArrayList<Contact> getItems = contactDao.getItems(index_page);
		request.setAttribute("getItems", getItems);
		Contact countContact = contactDao.countContact();
		request.setAttribute("countContact", countContact);
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/contact/index.jsp");
		rd.forward(request, response);
	}

}
