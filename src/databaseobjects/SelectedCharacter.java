package databaseobjects;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectedCharacter extends JPanel{
	
	JLabel name;

	public SelectedCharacter(String nameText) {
		name = new JLabel(nameText);
		this.setSize(300, 400);
		this.setBackground(Color.BLACK);
	}

}
