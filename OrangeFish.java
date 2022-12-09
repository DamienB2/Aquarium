import java.awt.Image;

import javax.swing.ImageIcon;

public class OrangeFish extends Fish{
	
	public OrangeFish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH) {
		super(AQUARIUM_HEIGHT, AQUARIUM_WIDTH);
	}
	
	
	
	public Image getFishImage() {
		
		 ImageIcon orfi = new ImageIcon("src\\orange_fish.PNG");
	     Image orange_fish = orfi.getImage();
	     
	     return orange_fish;
		}
	

}
