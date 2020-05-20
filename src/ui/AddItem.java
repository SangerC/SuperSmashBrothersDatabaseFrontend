package ui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import databaseobjects.SelectedItem;
import databaseobjects.SelectedStage;

public class AddItem extends JPanel{
	String game;
	JButton addButton;
	JButton discard;
	JLabel nameLabel;
	JLabel originLabel;
	JLabel typeLabel;
	JTextField name;
	JTextArea origin;
	JTextField type;


	public AddItem(String game) {
		addLabels(game);
	}
	
	public AddItem(String game, SelectedItem item) {
		addLabels(game);
		
		this.name.setText(item.getNameText());
		this.origin.setText(item.getOriginText());
		this.type.setText(item.getTypeText());
		
		this.revalidate();
		this.repaint();
	}
	
	
	public void addLabels(String game) {
		this.setSize(400, 475);
		this.setLayout(null);
		this.setBackground(Color.DARK_GRAY);
		this.game = game;
		this.addButton = new JButton("Add");
		this.add(this.addButton);
		this.nameLabel = new JLabel("Name:");
		this.discard = new JButton("Discard");
		this.add(discard);
		this.add(nameLabel);
		this.nameLabel.setForeground(Color.WHITE);
		this.originLabel = new JLabel("Origin:");
		this.add(originLabel);
		this.originLabel.setForeground(Color.WHITE);
		this.typeLabel = new JLabel("Type:");
		this.add(typeLabel);
		this.typeLabel.setForeground(Color.WHITE);
		this.nameLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.originLabel.setFont(new Font("Serif", Font.BOLD, 24));
		this.typeLabel.setFont(new Font("Serif", Font.BOLD, 24));
		
		this.name = new JTextField();
		this.add(name);
		this.origin = new JTextArea();
		this.add(origin);
		this.type = new JTextField();
		this.add(type);
		
		this.addButton.setBounds(300, 450, 75, 20);
		this.discard.setBounds(175, 450, 100, 20);
		this.nameLabel.setBounds(10, 20, 150, 40);
		this.name.setBounds(100, 20, 150, 40);
		this.originLabel.setBounds(10, 100, 150, 40);
		this.origin.setBounds(100, 100, 150, 100);
		this.typeLabel.setBounds(10, 250, 150, 40);
		this.type.setBounds(100, 250, 150, 40);;
	}
	
	
}

