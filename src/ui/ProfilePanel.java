package ui;

import services.ProfileService;

public class ProfilePanel extends ViewPanel {
	
	ProfileService profileService; 
 	String username;

	public ProfilePanel(ProfileService profileService) {
		super();
		this.remove(game);
		activeViewName.setText("Profile");
		this.profileService = profileService;
	}
	
	public void setConnected(String username) {
		this.add(activeViewName);
		this.username = username;
		this.revalidate();
		this.repaint();
	}
	
	@Override
	public void setDisconnected() {
		this.remove(activeViewName);
		this.revalidate();
		this.repaint();
	}
	
}
