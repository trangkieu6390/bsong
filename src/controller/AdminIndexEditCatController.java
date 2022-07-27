package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.dao.CategoryDAO;

/**
 * Servlet implementation class AdminIndexEditCatController
 */
public class AdminIndexEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexEditCatController() {
    	
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
		Category getCatById = categoryDAO.getItemById(id);
		if(getCatById != null) {
			request.setAttribute("getCatById", getCatById);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp");
			rd.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		int id = Integer.parseInt(request.getParameter("id"));
	
//		
//		response.sendRedirect(request.getContextPath()+"/admin/cats?msg=2");
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int id = 0;
		String name = request.getParameter("name");
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?err=1");
			return;
		}
		Category item = new Category(id, name);
		if(categoryDAO.UpdateItemById(item) > 0) {
			response.sendRedirect(request.getContextPath()+"/admin/cats?msg=2");
			return;
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/cat/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
