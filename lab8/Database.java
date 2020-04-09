package lab8;

import java.sql.*; 

public class Database{
	private static Database db = null;
	private Connection conn = null;

	private Database() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MusicAlbums","dba","sql");

	}

	static Database getDB(){
		if(db != null){
			return db;
		}
		try{
			db = new Database();
			return db;
		}catch(Exception e){
			return null;
		}
	}

	ResultSet runQuery(String query){
		try{
			return conn.createStatement().executeQuery(query);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	void close(){
		try{
			conn.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}

	int runUpdate(String update){
		try{
			return conn.createStatement().executeUpdate(update);
		}catch(Exception e){
			System.out.println(e);
			return -1;
		}	
	}

	boolean commit(){
		try{
			conn.commit();
			return true;
		}catch(Exception e){
			return false;
		}
	}

	PreparedStatementCustom startQuery(String query) throws Exception{
		return new PreparedStatementCustom(conn.prepareStatement(query));
	}

}