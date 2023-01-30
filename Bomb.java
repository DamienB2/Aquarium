
public class Bomb extends Bonus{

	public Bomb(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		
	}
	
	public String getBonusLink() {
		
		String link = "src\\bomb.PNG";
		
	    return link;
		}

}
