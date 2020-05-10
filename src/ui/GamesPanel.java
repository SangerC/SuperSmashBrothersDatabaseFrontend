package ui;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import databaseobjects.Game;
import services.GameService;

public class GamesPanel extends JPanel {
	
	GameService gameService;
	ArrayList<Game> games;
	JComponent leftArrow;
	JComponent rightArrow;
	int leftIndex;

	public GamesPanel(GameService gameService) {
		this.gameService = gameService;
		
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 200);
		this.setLayout(null);
		
		
		
		
		
	}
	
	public void populate() {
		this.games = gameService.getGames();
		this.leftIndex=0;
	}

	public void draw() {
		this.removeAll();
		if(games!=null) {
			if(games.size()>5) {
				//draw left right arrows
			}
			int horizontalPosition = 100;
			for(int i = leftIndex; i<leftIndex+5; i++) {
				this.add(games.get(i));
				this.games.get(i).setBounds(horizontalPosition, 0, 100, 100);
				horizontalPosition+=200;
			}
		}
		else {

		}
	}
	
	
	
	public void depopulate() {
		this.games = null;
	}

	
	
	
}
