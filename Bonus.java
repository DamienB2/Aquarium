import java.util.List;

public class Bonus {
	
	private int pos_x;
	private int pos_y;
	private boolean is_eaten = false;
	
	
	public Bonus(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		this.setPos_x(random_x);		
		this.setPos_y(random_y);
		
	}


	public int getPos_x() {
		return pos_x;
	}


	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}


	public int getPos_y() {
		return pos_y;
	}


	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}
	
	
	public String getBonusLink() {
		return null;
	}
	
	public void checkBonusHit() {
		
	}


	public boolean getIs_eaten() {
		return is_eaten;
	}


	public void setIs_eaten(boolean is_eaten) {
		this.is_eaten = is_eaten;
	}
	
	
	public void removeEaten(List<Bonus> bonuses) {
		
				bonuses.remove(this);
	}
	
}
