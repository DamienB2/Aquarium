import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

//- BIND TOUCHES 6 7 8
//- PROBLEME RESET AQUARIUM


	public class Aquarium extends JPanel implements ActionListener {

	private static int finish_time;
		
	private final Color BACKGROUND_COLOR = new Color(173, 216, 230);

    private final int MAX_FISH = 25;
    private final int FISH_SIZE = 50;
    private final int MAX_DECORATION = 5;
    private final int DELAY = 200;
    
    private int AQUARIUM_WIDTH;
    private int AQUARIUM_HEIGHT;
    private int nb_each_fish = 2;
    private int nb_bonuses = 2;
    private int nb_decoration = 0;
    private int pill_stop_duration = 10000;
    private int insect_effect_duration_1 = 5000;
    private int insect_effect_duration_2 = 10000;
    private int insect_effect_duration_3 = 15000;
    
    private double fertility;
    
    private String aquarium_temp = "Medium";
    private String aquarium_target = "none";
 
    private Timer timer;
    
    private String[] types = {"PurpleFish", "RedFish", "BlueFish", "OrangeFish"};
    private List<String> Types_list = Arrays.asList(types);
    private List<Fish> Fishes = new ArrayList<Fish>();
    private List<Decoration> Deco = new ArrayList<Decoration>();
    private List<Bonus> Bonus = new ArrayList<Bonus>();

    public Aquarium() {
        
        initAqua();

    }
    

    private void initAqua() {
    				
    	initComponents();										//create bonuses. nb_bonuses mean how many we create of them  
    }
    
    private void initComponents() {
    	
    	AQUARIUM_WIDTH = 1200;				//declaring variable here so when we reset the aquarium it resize it correc
    	AQUARIUM_HEIGHT = 700;
    	
    	addKeyListener(new TAdapter());
        setBackgroundColor(aquarium_temp);
        setFocusable(true);

        setPreferredSize(new Dimension(AQUARIUM_WIDTH, AQUARIUM_HEIGHT));
        
        AQUARIUM_HEIGHT = AQUARIUM_HEIGHT - FISH_SIZE;						//resizing AQUARIUM_HEIGHT so we don't need to do it everytimes when we use it
        AQUARIUM_WIDTH = AQUARIUM_WIDTH - FISH_SIZE;
        
        createDecoration();												//create the decoration, not finished yet
        
        for(String fish: Types_list) {										
        	createFish(fish, nb_each_fish);									//create fish from the Types_list so we can decide wich type of fish we want in the aquarium and how many
        }

        createBonus(nb_bonuses);
        
        timer = new Timer(DELAY, this);
    	timer.start();
		
	}
    
    private void createBonus(int number_bonuses) {
		
    	for (int i = 0; i < number_bonuses; i++) {
    		addPill();									//addPill and addInsect in the for so we get multiple of them
			addInsect();										
		}
    	
    	addBomb();										//under the for so we only have 1 bomb
		
	}


	private void addBomb() {
		Bonus.add(new Bomb(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
	}


	private void addInsect() {
		Bonus.add(new Insect(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));		
	}


	private void addPill() {
		Bonus.add(new Pill(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
	}


	private void createFish(String name, int number) { //Get a type of fish and a quantity and create fishes.
    	
    	for (int i = 0; i < number; i++) {
    		
    		if(name == "OrangeFish") {
    			Fishes.add(new OrangeFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
    		}
    		else if(name == "PurpleFish") {
    			Fishes.add(new PurpleFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT), nb_decoration));
    		}	
    		else if(name == "BlueFish") {
    			Fishes.add(new BlueFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
    		}					
    		else if(name == "RedFish") {
    			Fishes.add(new RedFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
    		}	
    	}		
    }
    

	private void setBackgroundColor(String aquarium_temp) {		//change the back color of the aquarium when the temperature is changed
		
    	if(aquarium_temp == "Cold") {
    		setBackground(Color.cyan);
    	}
    	else if (aquarium_temp == "Medium") {
    		setBackground(BACKGROUND_COLOR);
    	}
    	else if (aquarium_temp == "Hot") {
    		setBackground(Color.red);
    	}
		
	}


	private void createDecoration() {			//create a random number of decoration and add them to the aquarium
    	
    	nb_decoration = randomGenerator(MAX_DECORATION);
    	
    	for (int i = 0; i < nb_decoration; i++) {
			Deco.add(new Decoration(AQUARIUM_HEIGHT, AQUARIUM_WIDTH, randomGenerator(AQUARIUM_WIDTH), randomGenerator(AQUARIUM_HEIGHT)));
		}
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    
    private void doDrawing(Graphics g) {
    	for(Fish fish: Fishes) {																				//Look for all fish in the list fishes
    		g.drawImage(getImage(fish.getFishLink()), fish.getFish_x(), fish.getFish_y(), this);				//Each fish is redraw one by one 
    	}
    	
    	for(Decoration decoration: Deco) {																		//Draw decoration in the aquarium
    		g.drawImage(getImage(decoration.getDecorationLink()), decoration.getPos_x(), decoration.getPos_y(), this);
    	}
        
    	for(Bonus bonuses: Bonus) {																				//Draw decoration in the aquarium
    		g.drawImage(getImage(bonuses.getBonusLink()), bonuses.getPos_x(), bonuses.getPos_y(), this);
    	}
    	
    	
        Toolkit.getDefaultToolkit().sync();
        
    }
    
    public Image getImage(String link) {			//return the image of a selected fish
    	
    	ImageIcon iico = new ImageIcon(link);
    	Image image = iico.getImage();
    	
    	return image;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	int time = (int) System.currentTimeMillis();							//set the time from 1970 in millisecond    	
    	
    	for(int i=0; i < Fishes.size()-1; i++) {
    		
    		Fishes.get(i).setTarget_mode(aquarium_target);
    		
    		String name_of_baby = Fishes.get(i).checkBaby(Fishes); 				//Call checkBaby to see if we can create a baby, if yes get a type of fish
    		reproduction(name_of_baby);
    		
    		
    		Fishes.get(i).checkTemp(aquarium_temp);								//Check the temp of the aquarium to know if the red fish need to go slower or faster
    		
    		
    		String bonus_name = Fishes.get(i).checkBonus(Bonus);				//Check if a pill has been hit. if yes return the the name of the bonus (Pill or Insect) 
    		stopFishes(bonus_name, time, Fishes.get(i));						//take the current time, the name of the bonus and the actual fish
    		
    		
    		removeEatenBonus();													//call removeEatenBonus and check if a bonus has been eaten. 
    		
    		
    		if(Fishes.get(i).getCant_move() == false)
    			Fishes.get(i).move(Fishes, Bonus);									//Check the position of a specific fish in the list and do all the direction

    	}
    	
    	
    	setBackgroundColor(aquarium_temp);
    	repaint();
    }

    
	


	private void reproduction(String name_of_baby) {
		if(name_of_baby != null) {
			
			fertility = ((double) (MAX_FISH - Fishes.size()) / MAX_FISH) * 100;			//Fertility is the probability to have a baby when 2 fishes collide
			int number = randomGenerator(100);									//Generate a random number to compare it with the fertility
					
			if(Fishes.size() < MAX_FISH) 										//Check if when we add a fish we are still in the MAX_FISH  
				if(number <= fertility) {
					createFish(name_of_baby, 3);								//We call createFish that create a fish from a type and a number. here we create 3 fish of the same type
				}
				else {
					createFish(name_of_baby, 2);
				}
			
		}	
	}


	private void stopFishes(String bonus_name, int time, Fish fish) {
		
		
		if(bonus_name != null) {								
			
			if(bonus_name == "Pill") {														//if the bonus name is pill
				
				String stop_fish = fish.getClass().getName();
				
				finish_time = time + pill_stop_duration;									//set the finish blocking time 
				
				stopAllFish(stop_fish);
			}
			
			if(bonus_name == "Insect") {													//if the bonus name is Insect
				
				int rand = randomGenerator(3);												//create a random number to generate a random duration for the effect
				
				switch(rand) {
					case 0:
						finish_time = time + insect_effect_duration_1;
						activateEffect(fish);
						break;
					
					case 1:
						finish_time = time + insect_effect_duration_2;
						activateEffect(fish);
						break;
					
					case 2:
						finish_time = time + insect_effect_duration_3;
						activateEffect(fish);
						break;
				}
				
			}
			
			if(bonus_name == "Bomb") {
				
					detonateBomb(fish);
			}
		} 
		
		else{										//every actionPerformed check if the finish time is reached
			
			if(time > finish_time) {
				
				activateAllFish();
				resetEffect(fish);
			}
		}
		
	}

	
	private void detonateBomb(Fish fish) {		//detonate the bomb and create a new one
				Fishes.remove(fish);
				addBomb();
	}
		

	private void removeEatenBonus() {		//remove all bonuses which has been eaten
		
		for (int i = 0; i < Bonus.size(); i++) {
			
			if(Bonus.get(i).getIs_eaten() == true)
				Bonus.remove(i);
		}
		
	}
	
	
	private void resetEffect(Fish fish) {
		
		fish.setFISH_SPEED(fish.getNORMAL_FISH_SPEED());							//reset the fish speed to normal
	}
	
	
	private void activateEffect(Fish fish) {
		
		if(fish.getClass().getName() != "RedFish" && aquarium_temp != "Hot")		//don't activate the effect if it is a red fish during the hot temperature so it can't go faster than 2 times the normal speed
			fish.setFISH_SPEED(fish.getFISH_SPEED() + 5);
	}
	
	
	private void stopAllFish(String stop_fish) {
		
		for(int j = 0; j < Fishes.size(); j++) {				
			
			if(Fishes.get(j).getClass().getName() != stop_fish) {			//Block all the fishes exept the category of the fish whose had eat the pill
				
				Fishes.get(j).setCant_move(true);
			}
		}
		
	}
	
	
	private void activateAllFish() {
		
		for (int i = 0; i < Fishes.size(); i++) {		
			
			Fishes.get(i).setCant_move(false);				//Set all the Cant_move variable of all fish to false
		}
	}
	
    
    public int randomGenerator(int max_value) { 			//randomize a number between 0 and the max value
    	
    	int random = (int) (Math.random()*max_value);
    	
		return random;
    	
    }
    
    
    public void resetAquarium() {		//reset every lists and create a new aquarium
    	timer.stop();
    	aquarium_target = "none";
    	Fishes.clear();
    	Deco.clear();
    	Bonus.clear();
    	initAqua();
    	
    }
    
    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
      
            int key = e.getKeyCode();
            
            
            	    
            	if ((key == KeyEvent.VK_0)) {		//reset the aquarium
            		resetAquarium();
            	}
            	
            	if ((key == KeyEvent.VK_1)) {		//set the temperature to cold
            		aquarium_temp = "Cold";
            	}
            	
            	if ((key == KeyEvent.VK_2)) {		//set the temperature to Medium
            		aquarium_temp = "Medium";            	
            	}
            	
            	if ((key == KeyEvent.VK_3)) {		//set the temperature to Hot
            		aquarium_temp = "Hot";            	
            	}
            	
            	if ((key == KeyEvent.VK_4)) {		//add an insect to the aquarium
            		addInsect();
            	}
            	
            	if ((key == KeyEvent.VK_5)) {		//add a pill to the aquarium
            		addPill();
            	}
            	
            	if ((key == KeyEvent.VK_6)) {		//set the aquarium to Insect mode
            		aquarium_target = "Insect";
            	}
            	
            	if ((key == KeyEvent.VK_7)) {		//set the aquarium to Pill mode
            		aquarium_target = "Pill";
            	}
            	
            	if ((key == KeyEvent.VK_8)) {		//set the aquarium to Reproduction mode
            		aquarium_target = "Reproduction";
            	}
            	
            	if ((key == KeyEvent.VK_9)) {
            		createFish(Types_list.get((int) (Math.random()*Types_list.size())), 1);			//Create a new random fish
            	}
            	
            	if ((key == KeyEvent.VK_R)) {		//stop all fish exept the RedFish
            		activateAllFish();
            		stopAllFish("RedFish");
            	}
            	
            	if ((key == KeyEvent.VK_B)) {		//stop all fish exept the BleuFish
            		activateAllFish();	
            		stopAllFish("BlueFish");
            	}
            	
            	if ((key == KeyEvent.VK_M)) {		//stop all fish exept the PurpleFish
            		activateAllFish();
            		stopAllFish("PurpleFish");
            	}
            	
            	if ((key == KeyEvent.VK_O)) {		//stop all fish exept the OrangeFish
            		activateAllFish();
            		stopAllFish("OrangeFish");
            	}
            	
        }
    }
	
}
