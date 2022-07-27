package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexCatController() {
        super();
        categoryDAO = new CategoryDAO();
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
		
		int number_page = categoryDAO.countPage();
		request.setAttribute("number_page", number_page);                                                                              
		
		ArrayList<Category> listCat = categoryDAO.getItems(index_page);
		request.setAttribute("listCat", listCat);
		
		Category countCats = categoryDAO.countCat();
		request.setAttribute("countCats", countCats);
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
		rd.forward(request, response);
	}

}
