import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Fish { 
    
    private final int FISH_SIZE;
    private int FISH_SPEED;
    private int fish_x;
    private int fish_y;
    private String direction;
    private String[] way = {"N", "NE", "E", "SE", "S", "SO", "O", "NO"};

    public Fish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH) {
    	
    	this.FISH_SPEED = 2;												//The number of pixels the fish makes at each action performed
    	this.FISH_SIZE = 50;												//Size of the fish in pixels
    	// Parfois mon poissons appairait en dehors de la zone de jeu et reviens. Pourquoi ???
    	this.setFish_x((int) (Math.random()*AQUARIUM_WIDTH-FISH_SIZE));		//Place the fish in a random X position - FISH_SIZE so the fish don't appear outside the aquarium
    	this.setFish_y((int) (Math.random()*AQUARIUM_HEIGHT-FISH_SIZE));	//Place the fish in a random Y position - FISH_SIZE so the fish don't appear outside the aquarium
    	this.setDirection(way[(int) (Math.random()*way.length)]);			//Set the direction of the fish randomly
    }

	public int getFish_y() {
		return fish_y;
	}

	public int getFish_x() {
		return fish_x;
	}
	
	public void setFish_y(int fish_y) {
		this.fish_y = fish_y;
	}

	public void setFish_x(int fish_x) {
		this.fish_x = fish_x;
	}

	public int getFISH_SPEED() {
		return FISH_SPEED;
	}

	public int getFISH_SIZE() {
		return FISH_SIZE;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public Image getFishType() {
		return null ;
	}

    
    public void checkFish(int AQUARIUM_WIDTH, int AQUARIUM_HEIGHT) {
    	if(		(this.getFish_x() >= 0) 
            && 	(this.getFish_x() < AQUARIUM_WIDTH - this.getFISH_SIZE()) 
            && 	(this.getFish_y() >= 0)
            &&	(this.getFish_y() < AQUARIUM_HEIGHT - this.getFISH_SIZE()))
            {
            	moveFish(this.getDirection());
            	
            }
            //sinon ici
            else 
            {
            	changeDirection(AQUARIUM_WIDTH, AQUARIUM_HEIGHT);
            } 
    }
    
    
    public void moveFish(String direction) {
    	
    	if(direction == "N") {
        	this.setFish_y(this.getFish_y() - this.getFISH_SPEED());
        }
        else if(direction == "NE"){
        	this.setFish_y(this.getFish_y() - this.getFISH_SPEED());
        	this.setFish_x(this.getFish_x() + this.getFISH_SPEED());
        }
        else if(direction == "E"){
        	this.setFish_x(this.getFish_x() + this.getFISH_SPEED());
        }
        else if(direction == "SE"){
        	this.setFish_y(this.getFish_y() + this.getFISH_SPEED());
        	this.setFish_x(this.getFish_x() + this.getFISH_SPEED());
        }
        else if(direction == "S"){
        	this.setFish_y(this.getFish_y() + this.getFISH_SPEED());
        }
        else if(direction == "SO"){
        	this.setFish_y(this.getFish_y() + this.getFISH_SPEED());
        	this.setFish_x(this.getFish_x() - this.getFISH_SPEED());
        }
        else if(direction == "O"){
        	this.setFish_x(this.getFish_x() - this.getFISH_SPEED());
        }
        else if(direction == "NO"){
        	this.setFish_y(this.getFish_y() - this.getFISH_SPEED());
        	this.setFish_x(this.getFish_x() - this.getFISH_SPEED());
        }
    }
    
    
    public void changeDirection(int AQUARIUM_WIDTH, int AQUARIUM_HEIGHT) {
    	int posX = this.getFish_x();
    	int posY = this.getFish_y();
    	String finalDirection;
    	
    	List<String> directions = new ArrayList<String>();
    	directions.add("N");
    	directions.add("NE");
    	directions.add("E");
    	directions.add("SE");
    	directions.add("S");
    	directions.add("SO");
    	directions.add("O");
    	directions.add("NO");
    	
    	
    	//regarde ou se trouve le poisson et change la direction en fonction de ça
    	if(posX >= AQUARIUM_WIDTH - this.getFISH_SIZE()) {
    		directions.remove("E");
    		directions.remove("NE");
    		directions.remove("SE");
    	}
    	
    	else if(posX < 0){
    		directions.remove("O");
    		directions.remove("NO");
    		directions.remove("SO");
    	}
    	
    	
    	if(posY >= AQUARIUM_HEIGHT - this.getFISH_SIZE()){
    		directions.remove("S");
    		directions.remove("SE");
    		directions.remove("SO");
    	}
    	
    	else if(posY < 0){
    		directions.remove("N");
    		directions.remove("NE");
    		directions.remove("NO");
    	}
    	
    	finalDirection = directions.get((int) (Math.random()*directions.size()));
    	
    	this.setDirection(finalDirection);
    	System.out.println(directions);
    	System.out.println(this.getDirection());
    	moveFish(this.getDirection());
    }

	

    
    
}



