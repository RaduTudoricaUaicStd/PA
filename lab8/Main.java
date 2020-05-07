package lab8;

import java.sql.*;
import java.util.*;

public class Main{
	public static void main(String[] args) {
		
		Database db = Database.getDB();
		try{
			db.startQuery("DELETE FROM artists").runUpdate();
			db.startQuery("DELETE FROM albums").runUpdate();
			db.startQuery("DELETE FROM charts").runUpdate();
			db.startQuery("DELETE FROM ranks").runUpdate();
		}catch(Exception e){
			System.out.println("Nu au putut fi sterse datele din baza de date!");
		}

		Random rnd = new Random();
		AlbumController albumc = new AlbumController();
		ArtistController artistc = new ArtistController();
		ChartController chartc = new ChartController();

		List<Album> albums = new ArrayList();

		for(int i = 0; i<10; i++){
			Artist art = artistc.create("Artist "+Integer.toString(i), "NW");
			for(int j = 0; j<rnd.nextInt(6); j++){
				albums.add(albumc.create("Album "+i+"."+j, art.getId(), 2000+rnd.nextInt(20)));
			}
		}

		for(int i = 0; i<rnd.nextInt(4)+1; i++){
			Collections.shuffle(albums);
			Chart c = chartc.create("top 10."+i, 101101);
			for(int j = 0; j<10; j++){
				chartc.addAlbum(albums.get(j), c.getId(), j+1);
			}
		}

		for(Artist a : artistc.getArtistsInCharts()){
			System.out.println(a);
		}

	}
}