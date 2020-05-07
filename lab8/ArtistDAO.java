package lab8;

import java.util.*;

public interface ArtistDAO{
	public Artist create(String name, String country);
	public List<Artist> findByName(String name);
}