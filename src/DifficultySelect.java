import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

// Menu which prompts the user for a difficulty to play the game on (maze size)
public class DifficultySelect implements ActionListener{
	JButton easy, medium, hard;  // on permettra à l'utilisateur de choisir le niveau de difficulté
	JFrame difficultyFrame;
	JPanel difficultyPanel;
	// 0 for easy, 1 for medium, 2 for hard
	
	// Create a menu with 3 options
        public static void main(String [] args)
	{
		new DifficultySelect();
	}

	public DifficultySelect()
	{
		difficultyFrame = new JFrame("Select Difficulty");
		difficultyFrame.setSize(400, 400); // size de l'interface home 
		difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		difficultyPanel = new JPanel(new GridLayout(3,1));
	// -------- choisir le niveau de diffivulté ------	
		easy = new JButton("Easy 21x21");
		medium = new JButton("Medium 41x41");
		hard = new JButton("Hard 61x61");
		
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
		
		difficultyPanel.add(easy);
		difficultyPanel.add(medium);
		difficultyPanel.add(hard);
                
		
		difficultyFrame.add(difficultyPanel);
		
		difficultyFrame.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
            // appeller l'interface du game selon la sélection de l'utilisateur 
		if (e.getSource().equals(easy))
		{
			new interface_maze(21,21); // maze avec width 21
                        
		}
		else if (e.getSource().equals(medium))
		{
			new interfaceMoyenne(41,41);// maze avec width 41
		}
		else if (e.getSource().equals(hard))
		{
			new interface61(61,61); // maze avec width 61
		}
		
	}
       
    
        

}
