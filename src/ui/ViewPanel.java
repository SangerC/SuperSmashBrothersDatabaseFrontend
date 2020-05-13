package ui;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public abstract class ViewPanel extends JPanel {
	
	JLabel game;
	JLabel activeViewName;

	public ViewPanel() {
		this.setBackground(Color.darkGray);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setSize(1280, 520);
		this.setLayout(null);
		
		this.game = new JLabel();
		this.game.setForeground(Color.WHITE);
		this.game.setFont(this.game.getFont().deriveFont(24.0f));
		this.activeViewName = new JLabel();
		this.activeViewName.setForeground(Color.WHITE);
		this.activeViewName.setFont(this.game.getFont().deriveFont(24.0f));
		this.game.setBounds(10,-20,500, 80);
		this.activeViewName.setBounds(590,-20,300, 80);
	}
	
	public void setConnected(String currentGame) {
		this.game.setText(currentGame);
		this.add(game);
		this.add(activeViewName);
		this.revalidate();
		this.repaint();
	}
	
	public void setDisconnected() {
		this.removeAll();
		this.revalidate();
		this.repaint();
	}
	
	
}
