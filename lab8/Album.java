package lab8;

class Album{
	private int id;
	private int artist_id;
	private int release_year;
	private String name;

	Album(int id, int artist_id, int release_year, String name){
		this.id = id;
		this.artist_id = artist_id;
		this.release_year = release_year;
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public int getArtistId(){
		return artist_id;
	}

	public int getReleaseYear(){
		return release_year;
	}

	public void setReleaseYear(int release_year){
		this.release_year = release_year;
	}

	@Override
	public String toString(){
		return "Id: "+Integer.toString(id)+" ArtistId: "+Integer.toString(artist_id)+" ReleaseYear: "+Integer.toString(release_year)+" Name: "+name;
	}
}