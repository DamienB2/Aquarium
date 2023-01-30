
public class Insect extends Bonus{

	public Insect(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
	}
	
	
	public String getBonusLink() {
		
		String link = "src\\insect.PNG";
		
	    return link;
		}	

}
