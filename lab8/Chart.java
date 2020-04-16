package lab8;


class Chart{
	private int id;
	private int release_timestamp;
	private String name;

	Chart(int id, int release_timestamp, String name){
		this.id = id;
		this.release_timestamp = release_timestamp;
		this.name = name;
	}

	public int getId(){
		return id;
	}

	public int getReleaseTimestap(){
		return release_timestamp;
	}

	public void setReleaseTimestamp(int release_timestamp){
		this.release_timestamp = release_timestamp;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return "Id: "+Integer.toString(id)+" Name: "+name+" Release timestamp: "+Integer.toString(release_timestamp);
	}
}