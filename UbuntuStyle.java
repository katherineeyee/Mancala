package oop;

import java.awt.Color;

public class UbuntuStyle implements Style {

	@Override
	public void applyStyle(Board board) {
		
		board.setBackground(new Color(44, 0, 30));
		board.setForeground(new Color(233, 84, 32));
		
		board.holesPanel.setBackground(new Color(119, 41, 83));
		board.holesPanel.setForeground(new Color(233, 84, 32));
	}

	public String toString() {
		return "Ubuntu Style";
	}
}
