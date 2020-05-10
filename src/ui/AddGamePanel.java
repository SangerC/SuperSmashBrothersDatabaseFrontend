package ui;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddGamePanel extends JPanel {

	JTextField name;
	JLabel nameLabel;
	JTextField dateMade;
	JLabel dateLabel;
	JTextField consoles;
	JLabel consoleLabel;
	JButton confirm;
	
	public AddGamePanel(){
		this.name = new JTextField();
		this.nameLabel = new JLabel("Game Name:");
		this.dateMade = new JTextField();
		this.dateLabel = new JLabel("Date:");
		this.consoles = new JTextField();
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
		this.confirm = new JButton("Add");
		this.setSize(200, 125);
		this.draw();
	}
	
	public void draw() {
		this.add(confirm);
		this.confirm.setBounds(135, 102, 60, 20);
		
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
		this.dateMade.setBounds(5,102,120,20);
	}	
	
	public void clear() {
		name.setText("");
		dateMade.setText("");
		consoles.setText("");
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
