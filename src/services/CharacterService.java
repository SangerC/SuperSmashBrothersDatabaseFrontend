package services;

import java.util.ArrayList;

import databaseobjects.GameCharacter;

public class CharacterService {

	DatabaseConnection dbConnection;

	public CharacterService(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ArrayList<GameCharacter> getCharacters() {
		ArrayList<GameCharacter> g = new ArrayList<>();
		for(int i=0; i<100; i++) {
			g.add(new GameCharacter("Mario "+i, null));
		}
		return g;
	}

}
