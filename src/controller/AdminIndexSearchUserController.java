package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.User;
import model.dao.CategoryDAO;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexSearchController
 */
public class AdminIndexSearchUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UserDao userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexSearchUserController() {
        super();
        userDao = new UserDao();
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
		User CountUser = null;
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		ArrayList<User> getItems = null;
		if(search != null && !search.equals("")) {
			getItems = userDao.getSearchUser(search, index_page);
			request.setAttribute("search", search);
			number_page = userDao.countPageSearchUser(search);
			CountUser = userDao.countUserBySearchUser(search);
		} else {
			getItems = userDao.getItems(index_page);
			number_page = userDao.countPage();
			CountUser = userDao.countUser();
			
		}
		request.setAttribute("countUser", CountUser);
		request.setAttribute("number_page", number_page);
		request.setAttribute("getItems", getItems);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);
	}

}
