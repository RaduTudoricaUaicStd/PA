package lab8;

import java.util.*;
import java.sql.*;

/*
	tabele noi:
		pentru a pastra date despre un chart:
			create table charts(
				id integer not null generated always as identity (start with 1, increment by 1),
				name varchar(100) not null,
				release_timestamp integer not null,
				PRIMARY KEY(id)
			);
		pentru a pastra date despre albumele dintr-un chart:
			create table rankings(
				id integer not null generated always as identity (start with 1, increment by 1),
				album_id integer not null,
				chart_id integer not null,
				rank integer not null,
				PRIMARY KEY(id)
			);

*/

class ChartController implements ChartDAO{
	private Database db = null;

	ChartController(){
		db = Database.getDB();
	}

	public Chart create(String name, int release_timestamp){
		try{
			db.startQuery("INSERT INTO charts(name, release_timestamp) VALUES(?, ?)").set(1, name).set(2, release_timestamp).runUpdate();
			ResultSet r = db.runQuery("SELECT MAX(id) AS id FROM charts");
			r.next();
			return new Chart(r.getInt("id"), release_timestamp, name);
		}catch(Exception e){
			return null;
		}
	}

	public List<Album> getAlbums(int chartId){
		try{
			ResultSet r = db.startQuery("SELECT albums.id, albums.name, albums.release_year, albums.artist_id FROM ranks JOIN albums ON albums.id = ranks.album_id WHERE chart_id = ? ORDER BY rank ASC").set(1, chartId).runQuery();
			List<Album> l = new ArrayList();
			while(r.next()){
				l.add(new Album(r.getInt("albums.id"), r.getInt("albums.artist_id"), r.getInt("albums.release_year"), r.getString("albums.name")));
			}
			return l;
		}catch(Exception e){
			return null;
		}
	}

	public boolean addAlbum(Album newAlbum, int chartId, int place){
		try{
			ResultSet r = db.startQuery("SELECT COUNT(*) as nr_inregistrari FROM ranks WHERE chart_id = ? AND album_id = ?").set(1, newAlbum.getId()).set(2, chartId).runQuery();
			r.next();
			if(r.getInt("nr_inregistrari") > 0){
				return false;
			}
			db.startQuery("INSERT INTO ranks(chart_id, album_id, rank) VALUES(?, ?, ?)").set(1, chartId).set(2, newAlbum.getId()).set(3, place).runUpdate();
			return true;
		}catch(Exception e){
			return false;
		}
	}
}