package lab8;

class Artist{
	private int id;
	private String name;
	private String country;

	Artist(int id, String name, String country){
		this.id = id;
		this.name = name;
		this.country = country;
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

	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}

	@Override
	public String toString(){
		return "Id: "+Integer.toString(id)+" Name: "+name+" Country: "+country;
	}
}