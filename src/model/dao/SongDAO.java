package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.bean.Song;
import util.DBConnectionUtil;

public class SongDAO {

	private Connection conn;
	private PreparedStatement pmt;
	private ResultSet rs;
	private DBConnectionUtil dbConnectionUtil;
	
	public SongDAO() {
		dbConnectionUtil = new DBConnectionUtil();
	}

	public int countPage() {
		int total = 0;
		int total1 = 0;
		
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM songs";
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

	public Song countSong() {
		Song song = new Song();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM songs ";
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			if(rs.next()) {
				song = new Song(rs.getInt("COUNT(*)")); 
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
		
		return song;
	}

	public ArrayList<Song> getItems(int index_page) {
		ArrayList<Song> listItems = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT categories.name, songs.id, songs.name as nameh,songs.preview_text,songs.detail_text,songs.date_create,songs.cat_id, songs.picture, songs.counter FROM songs INNER JOIN categories ON categories.id = songs.cat_id GROUP by id LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, (index_page - 1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				Song song = new Song(rs.getInt("id"), rs.getString("nameh"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getString("categories.name"),rs.getInt("counter"));
				listItems.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listItems;
	}

	public ArrayList<Song> getSearchSong(String search, int index_page) {
		ArrayList<Song> listItems = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT categories.name, songs.id, songs.name as nameh, songs.picture, songs.counter FROM songs INNER JOIN categories ON categories.id = songs.cat_id WHERE songs.name LIKE ? GROUP by songs.id LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("nameh"),rs.getString("picture"),rs.getInt("counter"),rs.getString("categories.name"));
				listItems.add(song);
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
	public ArrayList<Song> getSearchSong1(String search, int index_page) {
		ArrayList<Song> listItems = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM songs WHERE name LIKE ? LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getInt("counter"));
				listItems.add(song);
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

	public int countPageSearchSong(String search) {
		int total = 0;
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM songs WHERE name like ?";
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

	public Song countSearchSong(String search) {
		Song song = new Song();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT COUNT(*) FROM songs WHERE name like ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, "%"+search+"%");
			rs = pmt.executeQuery();
			if(rs.next()) {
				song = new Song(rs.getInt("COUNT(*)")); 
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
		
		return song;
	}

	public void delSongById(int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "DELETE songs.* FROM songs WHERE songs.id= ?";
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

	public void insertSong(String name, String category, String imageFileName, String preview, String detail) {

		conn = dbConnectionUtil.getConnection();
		String sql = "INSERT INTO songs(name, cat_id, picture, preview_text, detail_text)values(?,?,?,?,?)";
		try {
			pmt = conn.prepareStatement(sql);
			pmt.setString(1,name);
			pmt.setString(2,category);
			pmt.setString(3,imageFileName);
			pmt.setString(4,preview);
			pmt.setString(5,detail);
			
			pmt.executeUpdate();
			
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
	}

	@SuppressWarnings("static-access")
	public Song getSongById(int id) {
		Song getItemById = new Song();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT categories.name, songs.id, songs.name as nameh,songs.preview_text,songs.detail_text,songs.date_create,songs.cat_id, songs.picture, songs.counter FROM songs INNER JOIN categories ON categories.id = songs.cat_id WHERE songs.id = ?";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			rs = pmt.executeQuery();
			if(rs.next()) {
				
				getItemById = new Song(rs.getInt("id"), rs.getString("nameh"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getString("categories.name"),rs.getInt("counter"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getItemById;
	}

	public void UpdateItemById(String name, String category, String preview, String detail, String fileName, int id) {
		conn = dbConnectionUtil.getConnection();
		String query = "UPDATE songs SET name = ?, cat_id = ?, preview_text = ?, detail_text = ?, picture = ? WHERE id = ?";
		try {
			pmt = conn.prepareStatement(query);
			pmt.setString(1, name);
			pmt.setString(2, category);
			pmt.setString(3, preview);
			pmt.setString(4, detail);
			pmt.setString(5, fileName);
			pmt.setInt(6, id);
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
	public ArrayList<Song> getItemsNew() {
		ArrayList<Song> listItems = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM songs ORDER BY date_create DESC LIMIT 0,5";
		
		try {
			pmt = conn.prepareStatement(query);
			rs = pmt.executeQuery();
			while(rs.next()) {
				
				Song song = new Song(rs.getInt("id"), rs.getString("name"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getInt("counter"));
				listItems.add(song);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listItems;
	}
	public int countPage(int id) {
		int total = 0;
		conn = dbConnectionUtil.getConnection();
		String sql = "SELECT COUNT(*) FROM songs WHERE cat_id = ?";
		try {
			pmt = conn.prepareStatement(sql);
			pmt.setInt(1, id);
			rs = pmt.executeQuery();
			while(rs.next()) {
				int total1 = rs.getInt(1);
				total = total1/3;
				if(total%3!=0) {
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

	public ArrayList<Song> getItemsByID(int id, int index_page) {
		ArrayList<Song> listItems = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String query = "SELECT * FROM songs WHERE cat_id = ? LIMIT ?,3";
		
		try {
			pmt = conn.prepareStatement(query);
			pmt.setInt(1, id);
			pmt.setInt(2, (index_page-1)*3);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getInt("counter"));
				listItems.add(song);
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

	public ArrayList<Song> getListSongLq(int id, int cat_id) {
		ArrayList<Song> listSong = new ArrayList<Song>();
		conn = dbConnectionUtil.getConnection();
		String sql = " SELECT * FROM songs WHERE cat_id = ? and id not in(?)";
		try {
			pmt = conn.prepareStatement(sql);
			pmt.setInt(1, cat_id);
			pmt.setInt(2, id);
			rs = pmt.executeQuery();
			while(rs.next()) {
				Song song = new Song(rs.getInt("id"), rs.getString("name"),rs.getString("preview_text"),rs.getString("detail_text"),rs.getDate("date_create"),rs.getInt("cat_id"),rs.getString("picture"),rs.getInt("counter"));
				listSong.add(song);
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
		return listSong;
	}

	public Song updateView(int id) {
		Song objSong = null;
		conn = dbConnectionUtil.getConnection();
		String sql = " UPDATE songs SET counter = counter + 1  WHERE id = ?";
		try {
			
			pmt = conn.prepareStatement(sql);
			pmt.setInt(1, id);
			pmt.executeUpdate();
			
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
		
		return objSong;
		
	}


	
}
