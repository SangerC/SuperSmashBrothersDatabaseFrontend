package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import databaseobjects.SelectedCharacter;
import services.CharacterService;
import services.MoveService;

public class CompareWindow{

	HashMap<SelectedCharacter, String> selectedCharacters;
	JFrame f;
	JPanel p;
	int index;
	JButton left;
	JButton right;

	public CompareWindow(MoveService moveService, String characterName, CharacterService characterService) {
		this.f = new JFrame("Comapare: "+characterName);
		f.setSize(1280,600);
		f.setLayout(null);
		f.setBackground(Color.DARK_GRAY);
		index = 0;
		selectedCharacters = characterService.getCompare(moveService, characterName);
		left = new JButton("<");
		right = new JButton(">");
		left.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(index>0)index--;
				draw();
			}
			
		});
		right.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(index<selectedCharacters.size()-3)index++;
				draw();
			}
			
		});
		
	}
	
	public void draw(){
		f.setVisible(true);
		p = new JPanel();
		p.setSize(1280,600);
		p.setLayout(null);
		p.setLocation(0,0);
		f.add(p);
		p.add(left);
		p.add(right);
		left.setBounds(3,500, 60, 20);
		right.setBounds(1200, 500, 60, 20);
		
		int hPos = 15;
		Set<SelectedCharacter> keys = selectedCharacters.keySet();
		Object[] characters = keys.toArray();
		
		for(int i = index; i<4+index&&i<selectedCharacters.size(); i++) {
			p.add((Component) characters[i]);
			((Component) characters[i]).setLocation(hPos, 10);
			JLabel l = new JLabel(selectedCharacters.get(characters[i]));
			p.add(l);
			l.setBounds(hPos+150, 490, 300, 30);
			hPos+=410;
		}
		p.revalidate();
		p.repaint();
		
		
	}
	
}
