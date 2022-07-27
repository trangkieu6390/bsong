package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Contact;
import model.bean.User;
import util.DBConnectionUtil;

public class UserDao {
	private Connection conn;
	private PreparedStatement pmt;
	private ResultSet rs;
	private DBConnectionUtil dbConnectionUtil;
	
	public UserDao() {
		dbConnectionUtil = new DBConnectionUtil();
	}

	@SuppressWarnings("static-access")
	public ArrayList<User> getItems(int index_page) {
		ArrayList<User> listItems = new ArrayList<User>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM users limit ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, (index_page - 1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listItems.add(user);
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
		String sql = "SELECT COUNT(*) FROM users";
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
	public User countUser() {
		User users = new User();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM users ";
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			if(rs.next()) {
				users = new User(rs.getInt("COUNT(*)")); 
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
		
		return users;
	}

	@SuppressWarnings("static-access")
	public ArrayList<User> getSearchUser(String search, int index_page) {
		ArrayList<User> listItems = new ArrayList<User>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM users WHERE username like ? LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));
				listItems.add(user);
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

	public int countPageSearchUser(String search) {
		int total = 0;
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM users WHERE username like ?";
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
	public User countUserBySearchUser(String search) {
		User user = new User();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM users WHERE username like ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			rs = pmt.executeQuery();
			if(rs.next()) {
				user = new User(rs.getInt("COUNT(*)")); 
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
		
		return user;
	}

	public User getUserById(int id) {
		User getItemById = new User();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM users WHERE id = ?";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			rs = pmt.executeQuery();
			if(rs.next()) {
				getItemById = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("fullname"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getItemById;
	}

	public void UpdateItemById(String name, String password, String fullname, int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "UPDATE users SET username = ?, password = ?, fullname = ? WHERE id = ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name);
			pmt.setString(2, password);
			pmt.setString(3, fullname);
			
			pmt.setInt(4, id);
			pmt.executeUpdate();
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
	}

	public void delUserById(int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "DELETE FROM users WHERE id= ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			pmt.executeUpdate();
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
	}

	public void insertItem(String name, String password, String fullname) {
		conn = dbConnectionUtil.getConnection();
		String query = "INSERT INTO users(username,password,fullname) VALUES(?,?,?)";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name);
			pmt.setString(2, password);
			pmt.setString(3, fullname);
			pmt.executeUpdate();
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
	}
}
