package lab8;

import java.sql.*;
import java.util.*;

public class AlbumController implements AlbumDAO{
	private Database db = null;

	AlbumController(){
		db = Database.getDB();
	}

	public Album create(String name, int artistId, int releaseYear){
		try{
			db.startQuery("INSERT INTO albums(name, artist_id, release_year) VALUES(?, ?, ?)").set(1, name).set(2, artistId).set(3, releaseYear).runUpdate();
			ResultSet r = db.runQuery("SELECT MAX(id) AS id FROM albums");
			r.next();
			return new Album(r.getInt("id"), artistId, releaseYear, name);
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

	public List<Album> findByArtist(int artistId){
		try{
			ResultSet r = db.startQuery("SELECT id, release_year, name FROM albums WHERE artist_id = ?").set(1, artistId).runQuery();
			List<Album> l = new ArrayList();
			while(r.next()){
				l.add(new Album(r.getInt("id"), artistId, r.getInt("release_year"), r.getString("name")));
			}
			return l;
		}catch(Exception e){
			return null;
		}
	}
}