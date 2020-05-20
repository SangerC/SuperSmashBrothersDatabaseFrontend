package databaseobjects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectedItem extends JPanel{
	
	JLabel name;
	JLabel origin;
	JLabel originLabel;
	JLabel type;
	JLabel typeLabel;
	JButton delete;
	JButton update;

	public SelectedItem(String nameText, String origin, String type) {
		this.setBackground(Color.DARK_GRAY);
		this.setSize(400, 475);
		this.setLayout(null);
		
		name = new JLabel(nameText);
		this.add(name);
		this.name.setFont(new Font("Serif", Font.BOLD, 24));
		this.name.setForeground(Color.WHITE);
		
		originLabel = new JLabel("Origin:");
		this.add(originLabel);
		this.originLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.originLabel.setForeground(Color.WHITE);
		
		typeLabel = new JLabel("Type:");
		this.add(typeLabel);
		this.typeLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.typeLabel.setForeground(Color.WHITE);
				
		this.delete = new JButton("X");
		this.add(delete);
		
		this.update = new JButton("Edit");
		this.add(update);
		
		this.origin = new JLabel(origin);
		this.origin.setForeground(Color.WHITE);
		this.add(this.origin);
		this.type = new JLabel(type);
		this.type.setForeground(Color.WHITE);
		this.add(this.type);
		
		this.name.setBounds(130, 5, 150, 35);
		this.originLabel.setBounds(5, 200, 100, 200);
		this.origin.setBounds(100, 200, 100, 200);
		this.type.setBounds(100, 300, 150, 200);
		this.typeLabel.setBounds(5, 300, 150, 200);
		this.delete.setBounds(340, 440, 50, 25);
		this.update.setBounds(250, 440, 80, 25);
	}

	public JButton getDelete(){
		return delete;
	}
	
	public JButton getUpdate(){
		return update;
	}
	
	public String getNameText() {
		return name.getText();
	}
	
	public String getOriginText() {
		return origin.getText();
	}
	
	public String getTypeText() {
		return type.getText();
	}
	
}
