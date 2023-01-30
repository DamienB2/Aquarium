import java.awt.EventQueue;
import javax.swing.JFrame;

public class MainAqua extends JFrame {

    public MainAqua() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Aquarium());
            
        setResizable(false);
        pack();
        
        setTitle("Aquarium");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new MainAqua();
            ex.setVisible(true);
        });
    }
}

