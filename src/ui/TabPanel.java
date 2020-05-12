package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class TabPanel extends JPanel{

	JButton characters;
	JButton stages;
	JButton items;
	JButton profile;
	
	public TabPanel() {
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 30);
		this.setLayout(null);
		
		this.characters = new JButton("Characters");
		this.stages = new JButton("Stages");
		this.items = new JButton("Items");
		this.profile = new JButton("Profile");
		this.characters.setBounds(440, 5, 100, 20);
		this.stages.setBounds(590, 5, 100, 20);
		this.items.setBounds(740, 5, 100, 20);
		this.profile.setBounds(1170, 5, 80, 20);
	}
	
	public void setConnected(){
		this.add(profile);
		this.add(items);
		this.add(stages);
		this.add(characters);
		this.revalidate();
		this.repaint();
	}
	
	public void setDisconnected() {
		this.remove(profile);
		this.remove(items);
		this.remove(stages);
		this.remove(characters);
		this.revalidate();
		this.repaint();
	}
	
	public JButton getItems() {
		return items;
	}
	
	public JButton getStages() {
		return stages;
	}
	
	public JButton getCharacters() {
		return characters;
	}
	
	public JButton getProfile() {
		return profile;
	}
	
}
