package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import databaseobjects.Game;

public class EditGamePanel extends JPanel {

	Game g;
	JTextField name;
	JLabel nameLabel;
	JTextField dateMade;
	JLabel dateLabel;
	JTextField consoles;
	JLabel consoleLabel;
	JButton confirm;
	JButton discard;
	
	public EditGamePanel(Game g){
		this.g=g;
		this.name = new JTextField(g.getNameText());
		this.nameLabel = new JLabel("Game Name:");
		this.dateMade = new JTextField(g.getDateText());
		this.dateLabel = new JLabel("Date:");
		this.consoles = new JTextField(g.getConsolesText());
		this.consoleLabel = new JLabel("Consoles:");
		this.name.setForeground(Color.WHITE);
		this.name.setBackground(Color.DARK_GRAY);
		this.name.setOpaque(true);
		this.dateMade.setForeground(Color.WHITE);
		this.dateMade.setBackground(Color.DARK_GRAY);
		this.dateMade.setOpaque(true);
		this.consoles.setForeground(Color.WHITE);
		this.consoles.setBackground(Color.DARK_GRAY);
		this.consoles.setOpaque(true);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setLayout(null);
		this.confirm = new JButton("Confirm");
		this.discard = new JButton("Discard");
		this.setSize(200, 125);
		this.draw();
	}
	
	public void draw() {
		this.add(confirm);
		this.confirm.setBounds(113, 105, 80, 15);
		
		this.add(discard);
		this.discard.setBounds(113, 88, 80, 15);
		
		this.add(nameLabel);
		this.nameLabel.setBounds(5,5,190,20);
		
		this.add(name);
		this.name.setBounds(5,22,190,20);
		
		this.add(consoleLabel);
		this.consoleLabel.setBounds(5,42,190,20);
		
		this.add(consoles);
		this.consoles.setBounds(5,62,190,20);
		
		this.add(dateLabel);
		this.dateLabel.setBounds(5,82,190,20);
		
		this.add(dateMade);
		this.dateMade.setBounds(5,102,105,20);
	}	
	
	public JButton getDiscard() {
		return this.discard;
	}
	
	public JButton getConfirm() {
		return this.confirm;
	}
	
	public String getNameText() {
		return this.name.getText();
	}
	
	public String getConsolesText() {
		return this.consoles.getText();
	}
	
	public String getDateText() {
		return this.dateMade.getText();
	}
	
}
