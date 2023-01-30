import java.util.List;
public class OrangeFish extends Fish{
	
	private int save_rand = -1;
	
	public OrangeFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, random_x, random_y);
		
	}
	
	
	
	public String getFishLink() {
		
		String link = "src\\orange_fish.PNG";
		
	    return link;
		}
	
	
	public void move(List<Fish> Fishes, List<Bonus> Bonus) {
		
		if(this.getTarget_mode() == "none") { //if the target mode is none, move like a normal orange fish
			super.move(Fishes, Bonus);
			checkTargetHit(Fishes);
		}
		else if(this.getTarget_mode() == "Reproduction") {			//if the target mode is in reproduction mode remove all the targetable fish and add himself so he chase orangefish
				Targetable_fish.clear();
				Targetable_fish.add(this.getClass().getName());
				super.move(Fishes, Bonus);
				super.nearestTarget(Fishes, Targetable_fish);
			
		}
		else if (this.getTarget_mode() == "Pill" || this.getTarget_mode() == "Insect" ) {	//if target mode is in pill or insect mode us nearestbonus instead of nearest fish
			super.move(Fishes, Bonus);
			super.nearestBonus(Bonus);
		}
		
	}
	
	
	private void checkTargetHit(List<Fish> Fishes) {	//check if the orange fish hit the target, if yes set a new target on another wall
    	if(this.getDistance() <= tolerance_zone) {
    		newTarget(Fishes);
    	}
	}
	
	
	private void newTarget(List<Fish> Fishes) {
		
		int rand = (int) (Math.random()*4);
		
		if(save_rand == -1 || save_rand != rand) { //save_rand = -1 because we need to generate 4 positions the first time
												   //After that save_rand take the last rand number so the fish can't go on the same wall twice
			switch(rand) {
			case 0:
				this.setTarget_x((int) (Math.random()*AQUARIUM_WIDTH));
				this.setTarget_y(0);
				break;
				
			case 1:
				this.setTarget_x(AQUARIUM_WIDTH);
				this.setTarget_y((int) (Math.random()*AQUARIUM_HEIGHT));
				break;
				
			case 2:
				this.setTarget_x((int) (Math.random()*AQUARIUM_WIDTH));
				this.setTarget_y(AQUARIUM_HEIGHT);
				break;
				
			case 3:
				this.setTarget_x(0);
				this.setTarget_y((int) (Math.random()*AQUARIUM_HEIGHT));
				break;				
			}
			
			save_rand = rand;
		}	
	}
}
