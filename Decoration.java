import java.awt.Image;

import javax.swing.ImageIcon;

public class Decoration {
	
	private int pos_x; 
	private int pos_y;
	private String image;
	private final String[] image_direction = {"V", "H"};
	
	public Decoration(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
		this.setPos_x(random_x);		
		this.setPos_y(random_y);
		this.setImage(image_direction[(int) (Math.random()*image_direction.length)]);
		
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public int getPos_x() {
		return pos_x;
	}
	
	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getImage() {
		return image;
	}
	
	
	public String getDecorationLink() {
		
		String link;
		
		if(this.getImage() == "H") {
			link = "src\\decoration_horizontal.PNG";				
		}
		else {
			link = "src\\decoration_vertical.PNG";
		}
	     
	     
	     return link;
		}
	

}
