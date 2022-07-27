package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDAO;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexController() {
        super();
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
		String index_page1 = request.getParameter("index_page");
		int index_page = 0; 
		if(index_page1 == null) {
			index_page1 = "1";
		}
		
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		
		int number_page = songDao.countPage();
		request.setAttribute("number_page", number_page); 
		
		Song countSong = songDao.countSong();
		request.setAttribute("countSong", countSong);
		
		ArrayList<Song> listSong = songDao.getItems(index_page);
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/public/index.jsp");
		rd.forward(request, response);	
		
		
	}

}
