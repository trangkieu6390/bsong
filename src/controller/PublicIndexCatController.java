package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.CategoryDAO;
import model.dao.SongDAO;

/**
 * Servlet implementation class PublicIndexCatController
 */
public class PublicIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	private SongDAO songDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexCatController() {
        super();
        categoryDAO = new CategoryDAO();
        songDao = new SongDAO();
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

		int id = Integer.parseInt(request.getParameter("cat_id"));
		request.setAttribute("cat_id", id);
		String index_page1 = request.getParameter("index_page");
		int index_page = 0; 
		if(index_page1 == null) {
			index_page1 = "1";
		}
		
		
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		ArrayList<Song> listSong = songDao.getItemsByID(id,index_page);
		request.setAttribute("listSong", listSong);
		int number_page = songDao.countPage(id);
		request.setAttribute("number_page", number_page);
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/cat.jsp");
		rd.forward(request, response);	
	}

}
