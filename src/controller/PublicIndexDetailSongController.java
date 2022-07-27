package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Song;
import model.dao.SongDAO;

/**
 * Servlet implementation class PublicIndexDetailSongController
 */
public class PublicIndexDetailSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PublicIndexDetailSongController() {
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

		int id = Integer.parseInt(request.getParameter("id"));
		int cat_id = Integer.parseInt(request.getParameter("cat_id"));
		Song getSongtById = songDao.getSongById(id);
		request.setAttribute("getSongtById", getSongtById);
		songDao.updateView(id);
		
		ArrayList<Song> listSongLq = songDao.getListSongLq(id, cat_id);
		request.setAttribute("listSongLq", listSongLq);
		
		RequestDispatcher rd = request.getRequestDispatcher("/public/detail.jsp");
		rd.forward(request, response);
	}

}
