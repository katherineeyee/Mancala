package oop;

import java.awt.Color;

import javax.swing.JPanel;

public class RedStyle implements Style {

	public void applyStyle(Board board) {
		// TODO Auto-generated method stub
		
		board.setBackground(new Color(111,47,55));
		
		board.holesPanel.setBackground(new Color(30,1,5));
		board.holesPanel.setForeground(new Color(208, 61, 86));
	}
	
	public String toString() {
		return "Red Style";
		
	}

}
