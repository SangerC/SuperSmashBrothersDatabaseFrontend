package databaseobjects;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectedCharacter extends JPanel{
	
	public JLabel name;

	public SelectedCharacter(String nameText) {
		name = new JLabel(nameText);
	}

}
