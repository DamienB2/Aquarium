public class Pill extends Bonus{

	public Pill(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		
	}
	
	public String getBonusLink() {
		
		String link = "src\\pill.PNG";
		
	    return link;
		}	

}
