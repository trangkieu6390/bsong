package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;

/**
 * Servlet implementation class AdminIndexDelCatController
 */
public class AdminIndexDelCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexDelCatController() {
        super();
        categoryDAO = new CategoryDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		
		if(categoryDAO.delCatById(id) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?msg=1");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cats?err=2");
			rd.forward(request, response);
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		int id = Integer.parseInt(request.getParameter("id"));
//		categoryDAO.delCatById(id);
//		response.sendRedirect(request.getContextPath()+"/admin/cats?msg=1");
		
	}

}
