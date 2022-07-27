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
 * Servlet implementation class AdminIndexSearchSongController
 */
public class AdminIndexSearchSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexSearchSongController() {
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
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		
		String index_page1 = request.getParameter("index_page");
		
		int index_page = 0; 
		if(index_page1 == null) {
			index_page1 = "1";
		}
		int number_page = 0;
		Song songCount = null;
		index_page = Integer.parseInt(index_page1);
		request.setAttribute("index_page", index_page);
		ArrayList<Song> listSong = null;
		if(search != null && !search.equals("")) {
			listSong = songDao.getSearchSong(search, index_page);
			request.setAttribute("search", search);
			number_page = songDao.countPageSearchSong(search);
			songCount = songDao.countSearchSong(search);
		} else {
			listSong = songDao.getItems(index_page);
			number_page = songDao.countPage();
			songCount = songDao.countSong();
			
		}
		request.setAttribute("countSong", songCount);
		request.setAttribute("number_page", number_page);
		request.setAttribute("listSong", listSong);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/index.jsp");
		rd.forward(request, response);
	}

}
