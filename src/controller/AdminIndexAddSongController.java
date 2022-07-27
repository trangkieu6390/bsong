package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.bean.Category;
import model.dao.SongDAO;

/**
 * Servlet implementation class AdminIndexAddSongController
 */
@MultipartConfig()
public class AdminIndexAddSongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SongDAO songDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminIndexAddSongController() {
        super();
        songDAO = new SongDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher rd = request.getRequestDispatcher("/admin/song/add.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String preview = request.getParameter("preview");
		String detail = request.getParameter("detail");

		final Part part = request.getPart("picture");
		String fileName = getName(part);
		fileName = rename(fileName);
		String filePath = "D://JAVA//bsong2//WebContent//templates//admin//assets//img";
		System.out.println(filePath);
		
		File dirPath = new File(filePath);
		if(!dirPath.exists()) {
			dirPath.mkdir();
		}
		filePath = filePath + File.separator + fileName;
		part.write(filePath);
	
			
		
		songDAO.insertSong(name,category,fileName,preview,detail);
		
		response.sendRedirect(request.getContextPath()+"/admin/song?msg=3");
	}
	public static String getName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
	public static String rename(String fileName) {
		String[] arrImg = fileName.split("\\.");
		String duoiFileImg = arrImg[arrImg.length-1];
		String nameFile = "";
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYYhhmmss");
		for(int i = 0; i < arrImg.length - 1; i++) {
			if(i == 0) {
				nameFile = arrImg[i];
			}else {
				nameFile += "-"+arrImg[i];
			}
		}
		nameFile = nameFile + "_"+sdf.format(date)+"."+duoiFileImg;
		return nameFile;
	}
}
