import java.util.Arrays;
import java.util.List;

public class BlueFish extends Fish{
	
	
	private double natural_blue_speed = this.getFISH_SPEED() * 1.5; 
	
	
	
	public BlueFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		this.setFISH_SPEED((int) natural_blue_speed);
		Targetable_fish.add("PurpleFish");
		Targetable_fish.add("BlueFish");
	}
	
	
	public String getFishLink() {
		
		String link = "src\\blue_fish.PNG";
		
	    return link;
		}
	
	
	public void move(List<Fish> Fishes, List<Bonus> Bonus) {
		
		if(this.getTarget_mode() == "none") {	//if the target mode is none, move like a blue fish
			super.move(Fishes, Bonus);
			super.nearestTarget(Fishes, Targetable_fish);
		}
		else if(this.getTarget_mode() == "Reproduction") {	//if the target mode is in reproduction mode remove all the targetable fish and add himself so he chase bluefish
    		Targetable_fish.clear();
    		Targetable_fish.add(this.getClass().getName());
    		super.move(Fishes, Bonus);
			super.nearestTarget(Fishes, Targetable_fish);
    	}
		else if (this.getTarget_mode() == "Pill" || this.getTarget_mode() == "Insect") {//if target mode is in pill or insect mode us nearestbonus instead of nearest fish
			super.move(Fishes, Bonus);
			super.nearestBonus(Bonus);
		}
		
	}

}
