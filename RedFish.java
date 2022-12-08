import java.awt.Image;

import javax.swing.ImageIcon;

public class RedFish extends Fish{

	public RedFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH);
	}
	
	
	public Image getFishType() {
		
		 ImageIcon refi = new ImageIcon("src\\red_fish.PNG");
	     Image red_fish = refi.getImage();
	     
	     return red_fish;
		}

}
