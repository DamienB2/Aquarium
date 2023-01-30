import java.util.List;

public class PurpleFish extends Fish {

	public PurpleFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y, int nb_deco) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		this.setFISH_SPEED(this.getFISH_SPEED() + nb_deco);						//get the number of decoration in the aquarium and set the speed of the purple fish 
		Targetable_fish.add("RedFish");
	}																			//The speed is the default + nb of decoration
	
	public String getFishLink() {
		
		String link = "src\\purple_fish.PNG";
		
	    return link;
		}
	
	public void move(List<Fish> Fishes, List<Bonus> Bonus) {
		
		if(this.getTarget_mode() == "none") {	//if target mode is none use normal function
			super.move(Fishes, Bonus);
			newTarget(Fishes);
			
		}
		else if(this.getTarget_mode() == "Reproduction") { //if target mode is reproduction, remove all fish from Targetable_fish and add himself
    		Targetable_fish.clear();
    		Targetable_fish.add("PurpleFish");
    		super.move(Fishes, Bonus);
			super.nearestTarget(Fishes, Targetable_fish);
    	}
		
		else if (this.getTarget_mode() == "Pill" || this.getTarget_mode() == "Insect") { //if target mode is pill or insect, he track insect
			super.move(Fishes, Bonus);
			super.nearestBonus(Bonus);
		}
		
	}
	
	public void newTarget(List<Fish> Fishes) {
				
		super.nearestTarget(Fishes, Targetable_fish);
		
		if(this.getTarget_mode() == "none") {
			
			if(super.checkInside()) {							//check if our fish is inside the aquarium
				
				if(nearest_fish != null) {						
					
					int escapeTarget_x;
					int escapeTarget_y;
					int distanceX = this.getFish_x() - this.nearest_fish.getFish_x();
					int distanceY = this.getFish_y() - this.nearest_fish.getFish_y();
					
					if(distanceX == 0 ) {			//If we don't check that we can get a issue with the next lines because we divide by 0
						distanceX += 1;
					}
					if(distanceY == 0 ) {			//same
						distanceY += 1;
					}
					
					
					if(distanceX > 0) {							
						escapeTarget_x = AQUARIUM_WIDTH;
					}
					else {
						escapeTarget_x = 0;
					}
					
					escapeTarget_y = this.getFish_y() + (escapeTarget_x - this.getFish_x()) * (distanceY / distanceX);
					
					
					if(escapeTarget_y > AQUARIUM_HEIGHT) {
						escapeTarget_y = AQUARIUM_HEIGHT;	
					}
					else {
						escapeTarget_y = 0;
					}
					
					escapeTarget_x = this.getFish_x() + (escapeTarget_y - this.getFish_y()) * (distanceX / distanceY);
					
					
					
					this.setTarget_x(escapeTarget_x);
					this.setTarget_y(escapeTarget_y);
				}
		}
		}
	}	
}

