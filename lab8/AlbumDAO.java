package lab8;

import java.util.*;

public interface AlbumDAO{
	public Album create(String name, int artistId, int releaseYear);
	public List<Album> findByArtist(int artistId);
}