package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.Song;
import model.bean.User;
import model.dao.CategoryDAO;
import model.dao.SongDAO;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexController
 */
public class AdminIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO categoryDAO;
	private SongDAO songDao;
	private UserDao userDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexController() {
        super();
        categoryDAO = new CategoryDAO();
        songDao = new SongDAO();
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
		Song countSong = songDao.countSong();
		request.setAttribute("countSong", countSong);
		
		Category countCats = categoryDAO.countCat();
		request.setAttribute("countCats", countCats);
		
		User countUser = userDao.countUser();
		request.setAttribute("countUser", countUser);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/index.jsp");
		rd.forward(request, response);
		
		//chạy lên xem e
		
		
	}

}
