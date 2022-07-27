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
 * Servlet implementation class AdminIndexSearchController
 */
public class AdminIndexSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private CategoryDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexSearchController() {
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

		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		request.setAttribute("search", search);
		
		String index_page1 = request.getParameter("index_page");
		
		int index_page = 0; 
		if(index_page1 == null) {
			index_page1 = "1";
		}
		int number_page = 0;
		Category countCats = null;
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		ArrayList<Category> listCat = null;
		if(search != null && !search.equals("")) {
			listCat = categoryDAO.getSearchCats(search, index_page);
			request.setAttribute("search", search);
			number_page = categoryDAO.countPageSearch(search);
			countCats = categoryDAO.countCatBySearch(search);
		} else {
			listCat = categoryDAO.getItems(index_page);
			number_page = categoryDAO.countPage();
			countCats = categoryDAO.countCat();
			
		}
		request.setAttribute("countCats", countCats);
		request.setAttribute("number_page", number_page);
		request.setAttribute("listCat", listCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/index.jsp");
		rd.forward(request, response);
	}

}
