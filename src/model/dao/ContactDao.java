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

public class ContactDao {
	
	private Connection conn;
	private PreparedStatement pmt;
	private ResultSet rs;
	private DBConnectionUtil dbConnectionUtil;
	
	public ContactDao() {
		dbConnectionUtil = new DBConnectionUtil();
	}

	public int countPage() {
		int total = 0;
		int total1 = 0;
		
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM contacts";
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

	public ArrayList<Contact> getItems(int index_page) {
		ArrayList<Contact> listItems = new ArrayList<Contact>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM contacts limit ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, (index_page - 1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listItems.add(contact);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listItems;
	}

	public Contact countContact() {
		Contact contact = new Contact();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM contacts ";
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			if(rs.next()) {
				contact = new Contact(rs.getInt("COUNT(*)")); 
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
		
		return contact;
	}

	public ArrayList<Contact> getSearchContact(String search, int index_page) {
		ArrayList<Contact> listItems = new ArrayList<Contact>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM contacts WHERE name like ? LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Contact contact = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
				listItems.add(contact);
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

	public int countPageSearchContact(String search) {
		int total = 0;
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM contacts WHERE name like ?";
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

	public Contact countContactBySearchContact(String search) {
		Contact contact = new Contact();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM contacts WHERE name like ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			rs = pmt.executeQuery();
			if(rs.next()) {
				contact = new Contact(rs.getInt("COUNT(*)")); 
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
		
		return contact;
	}

	public void delContactById(int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "DELETE FROM contacts WHERE id= ?";
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

	public Contact getContactById(int id) {
		Contact getItemById = new Contact();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM contacts WHERE id = ?";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			rs = pmt.executeQuery();
			if(rs.next()) {
				
				getItemById = new Contact(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("website"), rs.getString("message"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getItemById;
		}

	public void UpdateItemById(String name, String email, String website, String message, int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "UPDATE contacts SET name = ?, email = ?, website = ?, message = ? WHERE id = ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name);
			pmt.setString(2, email);
			pmt.setString(3, website);
			pmt.setString(4, message);
			pmt.setInt(5, id);
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

	public void insertItem(String name, String email, String website, String message) {
		conn = dbConnectionUtil.getConnection();
		String query = "INSERT INTO contacts(name,email,website,message) VALUES(?,?,?,?)";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name);
			pmt.setString(2, email);
			pmt.setString(3, website);
			pmt.setString(4, message);
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
