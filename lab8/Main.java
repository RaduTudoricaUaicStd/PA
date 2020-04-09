package lab8;

import java.sql.*; 


public class Main{
	public static void main(String[] args) {
		AlbumController albumc = new AlbumController();
		ArtistController artistc = new ArtistController();
		artistc.create("a1", "c1");
		artistc.create("a1", "c2");
		Artist a1 = artistc.create("a1", "c3");
		System.out.println(artistc.findByName("a1"));
		albumc.create("al1", a1.getId(), 2000);
		albumc.create("al2", a1.getId(), 2000);
		albumc.create("al3", a1.getId(), 2000);
		System.out.println(albumc.findByArtist(a1.getId()));
	}
}