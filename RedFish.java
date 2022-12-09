import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

public class RedFish extends Fish{
	
	private int collision_size = 5;
	private Fish nearest_fish;
	
	

	public RedFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH);
	}
	
	
	public Image getFishImage() {
		
		 ImageIcon refi = new ImageIcon("src\\red_fish.PNG");
	     Image red_fish = refi.getImage();
	     
	     return red_fish;
		}
	
	
	public void checkFish(int AQUARIUM_WIDTH, int AQUARIUM_HEIGHT, List<Fish> Fishes) {
		 int this_position = this.getFish_x() + this.getFish_y();
		 int nearest_fish_position = 0;
		 int current_fish_position = 0;
		 String new_direction = "";
		 
		 
		if(		(this.getFish_x() >= 0) 										//Check if fish is in the aquarium border
            && 	(this.getFish_x() < AQUARIUM_WIDTH - this.getFISH_SIZE()) 
            && 	(this.getFish_y() >= 0)
            &&	(this.getFish_y() < AQUARIUM_HEIGHT - this.getFISH_SIZE()))
	            {
					
			//Pour calculer la distance, j'aurais dû utiliser le théorème de pythagore ?
					for(Fish fish: Fishes) {																							//Look for every fish of the game
						
						if(fish.getClass() != this.getClass()){																			//If my fish is not a red fish
							if(nearest_fish == null) {																					//define the first nearest fish
								
								nearest_fish = fish;
								nearest_fish_position = nearest_fish.getFish_x() + nearest_fish.getFish_y();							//Stock the "distance" in nearest_fish_position
								
							}else{																										//else look for the nearest fish
								
								current_fish_position = fish.getFish_x() + fish.getFish_y();											//set the current fish "distance" in a int
								
								if(Math.abs(this_position - current_fish_position) < Math.abs(this_position - nearest_fish_position)) {	//Check if the new nearest fish is closer than the last one
									
									nearest_fish = fish;
									nearest_fish_position = nearest_fish.getFish_x() + nearest_fish.getFish_y();
									
									
								}
							}
						}
						
												
						if((Math.abs(this.getFish_x() - nearest_fish.getFish_x()) <= collision_size) 
						&& (Math.abs(this.getFish_y() - nearest_fish.getFish_y()) <= collision_size)) {
							nearest_fish.setWas_eaten(true);
						}
						
					}
					
					if(this.getFish_y() > nearest_fish.getFish_y()) {
						new_direction += "N";
					}
					else if (this.getFish_y() < nearest_fish.getFish_y()) {
						new_direction += "S";
					}
					

					if(this.getFish_x() > nearest_fish.getFish_x()) {
						new_direction += "O";
					}
					else if (this.getFish_x() < nearest_fish.getFish_x()) {
						new_direction += "E";
					}
					
					
					this.setDirection(new_direction);
	            	moveFish(this.getDirection());
	            	
	            }
	            
	            else 																//Else change his direction
	            {
	            	changeDirection(AQUARIUM_WIDTH, AQUARIUM_HEIGHT);
	            } 
	}
	
}
