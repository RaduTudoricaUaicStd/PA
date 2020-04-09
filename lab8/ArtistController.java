package lab8;

import java.sql.*;
import java.util.*;

public class ArtistController implements ArtistDAO{
	private Database db = null;

	ArtistController(){
		db = Database.getDB();
	}

	public Artist create(String name, String country){
		try{
			db.startQuery("INSERT INTO artists(name, country) VALUES(?, ?)").set(1, name).set(2, country).runUpdate();
			ResultSet r = db.runQuery("SELECT MAX(id) AS id FROM artists");
			r.next();
			return new Artist(r.getInt("id"), name, country);
		}catch(Exception e){
			return null;
		}
	}

	public List<Artist> findByName(String name){
		try{
			ResultSet r = db.startQuery("SELECT id, country FROM artists WHERE name = ?").set(1, name).runQuery();
			List<Artist> l = new ArrayList();
			while(r.next()){
				l.add(new Artist(r.getInt("id"), name, r.getString("country")));
			}
			return l;
		}catch(Exception e){
			return null;
		}
	}
}