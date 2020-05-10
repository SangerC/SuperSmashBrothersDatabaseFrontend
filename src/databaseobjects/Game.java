package databaseobjects;

import java.awt.Image;
import java.sql.Date;

public class Game {

	String name;
	Date dateMade;
	String consoles;
	Image image;
	
	public Game(String name, Date dateMade, String consoles, Image image){
		this.name = name;
		this.dateMade = dateMade;
		this.consoles = consoles;
		this.image = image;
	}
	public Game(String name, Date dateMade, String consoles){
		this.name = name;
		this.dateMade = dateMade;
		this.consoles = consoles;
	}
	
	
	
}
