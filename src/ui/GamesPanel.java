package ui;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import databaseobjects.Game;
import services.GameService;

public class GamesPanel extends JPanel {
	
	GameService gameService;
	ArrayList<Game> games;

	public GamesPanel(GameService gameService) {
		this.gameService = gameService;
		
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 200);
		this.setLayout(null);
		
		
		
		
		
	}

	
	
	
}
