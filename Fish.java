import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class Fish { 
	private final int FISH_SIZE = 50;
	
    protected final int AQUARIUM_WIDTH;
    protected final int AQUARIUM_HEIGHT;
    protected final int NORMAL_FISH_SPEED = 10;
    
    protected Fish nearest_fish = null;
    protected Bonus nearest_bonus = null;

    protected int tolerance_zone = FISH_SIZE;
    
    protected List<String> Targetable_fish = new ArrayList<String>();
    
    private int FISH_SPEED;
    private int fish_x;
    private int fish_y;
    private int target_x;
    private int target_y;
    
    private double distance;
    
    private boolean is_inside = true;
    private boolean cant_move = false;
    
    private String target_mode;
    

    
    
    public Fish(int AQUARIUM_HEIGHT, int AQUARIUM_WIDTH, int random_x, int random_y) {
    	
    	this.setFISH_SPEED(NORMAL_FISH_SPEED);
    	this.AQUARIUM_WIDTH = AQUARIUM_WIDTH;
		this.AQUARIUM_HEIGHT = AQUARIUM_HEIGHT;
    	this.setFish_x(random_x);											//Place the fish in a random X position - FISH_SIZE so the fish don't appear outside the aquarium
    	this.setFish_y(random_y);											//Place the fish in a random Y position - FISH_SIZE so the fish don't appear outside the aquarium
    	this.setTarget_x(random_x);											//set the first target at the spawn position so the next one is random
		this.setTarget_y(random_y);											//same
		this.setTarget_mode("none");
    }

    public double getDistance() {
    	return distance;
    }
    
    public void setDistance(double distance) {
    	this.distance = distance;
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
	
	public void setFISH_SPEED(int FISH_SPEED) {
		this.FISH_SPEED = FISH_SPEED;
	}

	public int getFISH_SIZE() {
		return FISH_SIZE;
	}
	
	public String getFishLink() {
		return null ;
	}
	
	public int getTarget_y() {
		return target_y;
	}

	public int getTarget_x() {
		return target_x;
	}
	
	public void setTarget_y(int target_y) {
		this.target_y = target_y;
	}

	public void setTarget_x(int target_x) {
		this.target_x = target_x;
	}
	
	public void setIs_inside(boolean is_inside) {
		this.is_inside = is_inside;
	}
	
	public boolean getIs_inside() {
		return is_inside;
	}
	
	public boolean getCant_move() {
		return cant_move;
	}
	
	public void setCant_move(boolean cant_move) {
		this.cant_move = cant_move;
	}  
    
	public int getNORMAL_FISH_SPEED() {
		return NORMAL_FISH_SPEED;
	}
	
	public String getTarget_mode() {
    	return target_mode;
    }
    
    public void setTarget_mode(String target_mode) {
    	this.target_mode = target_mode;
    }
    
    public void checkTemp(String temp) {
    	
    }
	
    public void move(List<Fish> Fishes, List<Bonus> bonus) {		
    	
    	 ArrayList<Integer> x_moveOptions = new ArrayList<Integer>() ;
         ArrayList<Integer> y_moveOptions = new ArrayList<Integer>() ;
         ArrayList<Double> distances = new ArrayList<Double>() ;
         
         
         for (int i = -1 ; i <= 1 ; i+=1){			//check all the case around the fish to see if he can move to them or not
             for (int j = -1 ; j <=1 ; j+=1){
                 int test_pos_x = this.fish_x +i * FISH_SPEED;
                 int test_pos_y = this.fish_y +j * FISH_SPEED;
                 
                	 x_moveOptions.add(test_pos_x);
                     y_moveOptions.add(test_pos_y);
                                     
             }
         }
         
         for (int i=0; i < x_moveOptions.size() ; i++){			//look for the fastest way to go to the target
              this.setDistance(Distance(target_x, target_y, x_moveOptions.get(i), y_moveOptions.get(i)));
             distances.add(this.getDistance());
         } 
         
         double min = Collections.min(distances);
         int min_index = distances.indexOf(min);
         
         this.setFish_x(x_moveOptions.get(min_index));
         this.setFish_y(y_moveOptions.get(min_index));
         
    }
    
    
    protected void nearestTarget(List<Fish> Fishes, List<String> Targetable_fish) {
    	    	
    	nearest_fish = null;
		double current_fish_distance = 0;
		double nearest_fish_distance = 0;
		
    	for(int i = 0; i < Fishes.size()-1; i++) {																							//Look for every fish of the game
				
				Fish fish = Fishes.get(i);
				
				if(Targetable_fish.contains(fish.getClass().getName())){	
					
					if(this != fish) {
						
						if(nearest_fish == null) {																					//define the first nearest fish
							
							nearest_fish = fish;
							nearest_fish_distance = Distance(nearest_fish.getFish_x(), nearest_fish.getFish_y(), this.getFish_x(), this.getFish_y());						//Stock the "distance" in nearest_fish_position
							
							
						}else{																										//else look for the nearest fish
							
							current_fish_distance = Distance(fish.getFish_x(), fish.getFish_y(), this.getFish_x(), this.getFish_y());											//set the current fish "distance" in a int
							
							if(current_fish_distance <= nearest_fish_distance) {	//Check if the new nearest fish is closer than the last one
								
								nearest_fish = fish;
								nearest_fish_distance = current_fish_distance;	
								
							}
							
						}
						
					}
		
				}
    		}
    	
    	if(nearest_fish != null) {
			
			this.setTarget_x(nearest_fish.getFish_x());
			this.setTarget_y(nearest_fish.getFish_y());
		}
    	
	}
    
    
    protected void nearestBonus(List<Bonus> Bonus) {
    	nearest_bonus = null;
		double current_fish_distance = 0;
		double nearest_fish_distance = 0;
		
    	for(int i = 0; i < Bonus.size()-1; i++) {																							//Look for every fish of the game
				
				Bonus bonus = Bonus.get(i);
					
						if(nearest_bonus == null) {																					//define the first nearest fish
							
							nearest_bonus = bonus;
							nearest_fish_distance = Distance(nearest_bonus.getPos_x(), nearest_bonus.getPos_y(), this.getFish_x(), this.getFish_y());						//Stock the "distance" in nearest_fish_position
							
							
						}else{																										//else look for the nearest fish
							
							current_fish_distance = Distance(bonus.getPos_x(), bonus.getPos_y(), this.getFish_x(), this.getFish_y());											//set the current fish "distance" in a int
							
							if(current_fish_distance <= nearest_fish_distance) {	//Check if the new nearest fish is closer than the last one
								
								nearest_bonus = bonus;
								nearest_fish_distance = current_fish_distance;	
								
								}
							}	
						}
						
    	if(nearest_bonus != null) {
    		
    		this.setTarget_x(nearest_bonus.getPos_x());
    		this.setTarget_y(nearest_bonus.getPos_y());
    	}
	}
    	
    	
	

	

	protected double Distance(int pos_x0, int pos_y0, int pos_x1, int pos_y1){		//calcul a distance between 2 points by using pythagore
        int x_dist = pos_x1-pos_x0;
        int y_dist = pos_y1-pos_y0;
        return Math.sqrt(Math.pow(x_dist, 2)+Math.pow(y_dist, 2));
    }
       
    
    public boolean checkInside() { //check if the fish is inside the aquarium or not
    	
    	this.setIs_inside(true);
    	
    	if(this.fish_x < 0) {
    		this.setIs_inside(false);
    	}
    	
    	if(this.fish_x > this.AQUARIUM_WIDTH) {
    		this.setIs_inside(false);
    	}
    	
    	if(this.fish_y < 0) {
    		this.setIs_inside(false);
    	}
    	
    	if(this.fish_y > this.AQUARIUM_HEIGHT) {
    		this.setIs_inside(false);
    	}
    	
    	return this.getIs_inside();
    
    }
    
    
    public String checkBaby(List<Fish> Fishes) { // this class check if there is a collision between 2 same fish
    	
    	for(int i= 0; i < Fishes.size(); i++){ //every cycle check all fish coordinate 
    		
    		Fish fish = Fishes.get(i);
    		
    		if(fish.getClass() == this.getClass()) {
    			
    			if(this != fish) {
    			
    			double distance_between_same_fish = Distance(fish.getFish_x(), fish.getFish_y(), this.getFish_x(), this.getFish_y()); // check distance between 2 same fish
    			
    			if(distance_between_same_fish <= tolerance_zone) { //if the distance is shorter than the tolerance_zone remove both fish and return the type of those fish to recreate them 
    				Fishes.remove(this);
    				Fishes.remove(fish);
    				return this.getClass().getName();
    				}
    			}
    		}
    	}
		return null;
    }

	public String checkBonus(List<Bonus> bonus) {	//look in the bonus list to see if the fish is in collision with a bonus
		
		for (int i = 0; i < bonus.size(); i++) {
			
			double distance = Distance(bonus.get(i).getPos_x(), bonus.get(i).getPos_y(), this.getFish_x(), this.getFish_y());
			
			if(distance <= tolerance_zone) {	//if yes, return the name of the bonus to apply the effect
				
				bonus.get(i).setIs_eaten(true);
				return bonus.get(i).getClass().getName();
			}
		}
		return null;
	}

}



