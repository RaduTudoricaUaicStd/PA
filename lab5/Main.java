package lab5;

public class Main{
	public static void main(String[] args) {
		try{
			Catalog c = new Catalog("Java Resources", "/tmp/test_catalog.ser");
			Document d = new Document("java1", "Java Course 1",   "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
			d.addTag("type", "Slides");
			c.addDocument(d);
			c.save();
			Catalog e = Catalog.load(c.getSaveLocation());
			e.getDocById("java1").view();
		}catch(InvalidFileLocation e){
			System.out.println("Fisierul nu exista!");
		}
	}
}