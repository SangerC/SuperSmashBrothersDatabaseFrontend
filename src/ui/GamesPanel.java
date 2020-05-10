package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import databaseobjects.Game;
import services.GameService;

public class GamesPanel extends JPanel {
	
	GameService gameService;
	AddGamePanel addGamePanel;
	ArrayList<Game> games;
	JButton leftArrow;
	JButton rightArrow;
	int leftIndex;

	public GamesPanel(GameService gameService) {
		this.gameService = gameService;
		
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 200);
		this.setLayout(null);
		this.leftArrow = new JButton("<");
		this.rightArrow = new JButton(">");
		this.leftArrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(leftIndex>0) {
					leftIndex--;
					draw(true);
				}
			}
		});
		this.rightArrow.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(leftIndex<games.size()-2) {
					leftIndex++;
					draw(true);
				}
			}
		});
		
		this.addGamePanel = new AddGamePanel();
		this.addGamePanel.confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(gameService.addGame(addGamePanel.getNameText(), addGamePanel.getDateText(), addGamePanel.getConsolesText())){
					depopulate();
					addGamePanel.clear();
					setConnected();
				}
			}
		});
		
	}
	
	public void draw(boolean connected) {
		this.removeAll();
		if(games!=null) {
			if(games.size()>4) {
				this.add(leftArrow);
				this.leftArrow.setBounds(20, 93, 45, 15);
				this.add(rightArrow);
				this.rightArrow.setBounds(1215, 93, 45, 15);
			}
			int horizontalPosition = 75;
			for(int i = leftIndex; i<leftIndex+4&&i<games.size(); i++) {
				this.add(games.get(i));
				this.games.get(i).setLocation(horizontalPosition, 15);
				horizontalPosition+=230;
			}
			this.add(this.addGamePanel);
			this.addGamePanel.setLocation(horizontalPosition, 15);
		}
		else if(connected){
			this.add(this.addGamePanel);
			this.addGamePanel.setLocation(75, 15);
		}
	}

	public void populate() {
		this.games = gameService.getGames();
		this.leftIndex=0;
	}

	public void depopulate() {
		this.games = null;
	}
	
	public void setDisconnected() {
		this.depopulate();
		addGamePanel.clear();
		this.draw(false);
		this.revalidate();
		this.repaint();
	}
	
	public void setConnected() {
		this.populate();
		this.draw(true);
		this.revalidate();
		this.repaint();
	}
	
}
