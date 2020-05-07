package lab8;

import java.util.*;

public interface ChartDAO{
	public Chart create(String name, int release_timestamp);
	public List<Album> getAlbums(int chartId);
	public boolean addAlbum(Album newAlbum, int chartId, int place);
}