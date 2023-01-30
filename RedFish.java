import java.util.Arrays;
import java.util.List;

public class RedFish extends Fish{
	
	private int cold_speed = 5;
	private int hot_speed = 20;

	
	
	

	public RedFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		Targetable_fish.add("PurpleFish");
		Targetable_fish.add("BlueFish");
		Targetable_fish.add("OrangeFish");
	}
	
	
	public String getFishLink() {
		
		String link = "src\\red_fish.PNG";
		
	    return link;
		}
	
	
	public void move(List<Fish> Fishes, List<Bonus> Bonus) {
		
		if(this.getTarget_mode() == "none") {	//if the target mode is none, move like a normal red fish
			super.move(Fishes, Bonus);
			checkTargetHit(Fishes);
			super.nearestTarget(Fishes, Targetable_fish);
			
		}
		else if(this.getTarget_mode() == "Reproduction") {		//if the target mode is in reproduction mode remove all the targetable fish and add himself so he chase redfish
    		Targetable_fish.clear();
    		Targetable_fish.add(this.getClass().getName());
    		super.move(Fishes, Bonus);
			super.nearestTarget(Fishes, Targetable_fish);
    		
    	}
		else if (this.getTarget_mode() == "Pill" || this.getTarget_mode() == "Insect") {	//if target mode is in pill or insect mode us nearestbonus instead of nearest fish
			super.move(Fishes, Bonus);
			super.nearestBonus(Bonus);
			
		}
				
	}
	
	
	private void checkTargetHit(List<Fish> Fishes) {	//check if the redfish is in collision with his target so he can eat it
			
	    	if(this.getDistance() <= tolerance_zone) {
	    		Fishes.remove(nearest_fish); 

	    	}
		}
		
	
	public void checkTemp(String temp) {	//change the speed of the redfish if the temperature is cold or hot
    	
    	if(temp == "Cold") {
    		this.setFISH_SPEED(cold_speed);
    	}
    	else if(temp == "Hot") {
    		this.setFISH_SPEED(hot_speed);
    	}
    	else {
    		this.setFISH_SPEED(NORMAL_FISH_SPEED);
    		
    	}
    }
}
