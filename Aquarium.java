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
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

	public class Aquarium extends JPanel implements ActionListener {

    private final Color BACKGROUND_COLOR = new Color(173, 216, 230);

    private final int AQUARIUM_WIDTH = 1200;
    private final int AQUARIUM_HEIGHT = 700;
    private final int ALL_FISH = 25;
    private final int RAND_POS = 59;
    private final int DELAY = 25;
 
   
    private Timer timer;
    private List<Fish> Fishes = new ArrayList<Fish>();
    

    
    public Aquarium() {
        
        initAqua();
        
    }
    

    private void initAqua() {

    	addKeyListener(new TAdapter());
        setBackground(BACKGROUND_COLOR);
        setFocusable(true);

        setPreferredSize(new Dimension(AQUARIUM_WIDTH, AQUARIUM_HEIGHT));
        Fishes.add(new OrangeFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH));
        Fishes.add(new OrangeFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH));
        Fishes.add(new RedFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH));
        
        
        initGame();
        
    }

   
    private void initGame(){
          
        timer = new Timer(DELAY, this);
        timer.start();
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    
    private void doDrawing(Graphics g) {
    	for(Fish fish: Fishes) {															//Look for all fish in the list fishes
    		g.drawImage(fish.getFishType(), fish.getFish_x(), fish.getFish_y(), this);		//Each fish is redraw one by one 
    		
    	}
              
        Toolkit.getDefaultToolkit().sync(); 
    }
        

    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	for(Fish fish: Fishes) {								//Look for all fish in the fishes list
    		fish.checkFish(AQUARIUM_WIDTH, AQUARIUM_HEIGHT);	//Check the position of a specific fish in the list and do all the direction job
    		
    	}
    	
    	repaint();
        
    }

    
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
        	//GARDER UN TIMER POUR TOUT LES POISSONS OU METTRE UN TIMER PAR POISSON
            int key = e.getKeyCode();
            
            if(Fishes.size() <= ALL_FISH) {
            	
            	if ((key == KeyEvent.VK_LEFT)) {
            		Fishes.add(new OrangeFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH));
            	}
            	
            	if ((key == KeyEvent.VK_RIGHT)) {
            		Fishes.add(new RedFish(AQUARIUM_HEIGHT, AQUARIUM_WIDTH));
            	}
            	
            	if ((key == KeyEvent.VK_UP)) {
            		timer.stop();
            	}
            	
            	if ((key == KeyEvent.VK_DOWN)) {
            		timer.start();
            	}
        	}
        }
    }
}