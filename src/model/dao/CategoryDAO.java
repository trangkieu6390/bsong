package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Category;
import util.DBConnectionUtil;

public class CategoryDAO {
	private Connection conn;
	private PreparedStatement pmt;
	private ResultSet rs;
	private DBConnectionUtil dbConnectionUtil;
	
	public CategoryDAO() {
		dbConnectionUtil = new DBConnectionUtil();
	}

	@SuppressWarnings("static-access")
	public ArrayList<Category> getItems(int index_page) {

		ArrayList<Category> listItems = new ArrayList<Category>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM categories LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, (index_page - 1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				Category category = new Category(rs.getInt("id"), rs.getString("name"));
				listItems.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listItems;
	}
	public ArrayList<Category> getItems() {

		ArrayList<Category> listItems = new ArrayList<Category>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM categories";
		
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				Category category = new Category(rs.getInt("id"), rs.getString("name"));
				listItems.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listItems;
	}

	
	@SuppressWarnings("static-access")
	public int countPage() {
		int total = 0;
		int total1 = 0;
		
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM categories";
		try {
			pmt = conn.prepareStatement(sql);
			
			rs = pmt.executeQuery();
			while(rs.next()) {
				total1 = rs.getInt(1);
				
				total = total1/3;
				if(total1%3!=0) {
					total++;
				}
				
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return total;
	}
	
		
	@SuppressWarnings("static-access")
	public Category countCat() {
		Category category = new Category();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM categories ";
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			if(rs.next()) {
				category = new Category(rs.getInt("COUNT(*)")); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return category;
	}
	
	public Category countCatBySearch(String search) {
		Category category = new Category();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM categories WHERE name like ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			rs = pmt.executeQuery();
			if(rs.next()) {
				category = new Category(rs.getInt("COUNT(*)")); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return category;
	}

	@SuppressWarnings("static-access")
	public ArrayList<Category> getSearchCats(String search,int index_page) {

		ArrayList<Category> listItems = new ArrayList<Category>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM categories WHERE name like ? LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Category category = new Category(rs.getInt("id"), rs.getString("name"));
				listItems.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listItems;
	}

	@SuppressWarnings("static-access")
	public int countPageSearch(String search) {
		int total = 0;
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM categories WHERE name like ?";
		try {
			pmt = conn.prepareStatement(sql);
			pmt.setString(1, "%"+search+"%");
			
			rs = pmt.executeQuery();
			while(rs.next()) {
				int total1 = rs.getInt(1);
				total = total1/3;
				if(total1%3!=0) {
					total++;
				}
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return total;
	}

	@SuppressWarnings("static-access")
	public int delCatById(int id) {
		int result = 0;
		conn = dbConnectionUtil.getConnection();
		String query = "DELETE FROM categories WHERE id= ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			result = pmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
		
	}

	@SuppressWarnings("static-access")
	public Category getItemById(int id) {
		Category getItemById = null;
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM categories WHERE id = ?";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			rs = pmt.executeQuery();
			if(rs.next()) {
				
				getItemById = new Category(id, rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return getItemById;
	}

	@SuppressWarnings("static-access")
	public int UpdateItemById(Category item ) {
		int result = 0;
		conn = dbConnectionUtil.getConnection();
		String query = "UPDATE categories SET name = ? WHERE id = ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, item.getName());
			pmt.setInt(2, item.getId());
			result = pmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public int insertItem(Category name) {
		int resurt = 0;
		conn = dbConnectionUtil.getConnection();
		String query = "INSERT INTO categories(name) VALUES(?)";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name.getName());
			resurt = pmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return resurt;
	}

	
	

}
