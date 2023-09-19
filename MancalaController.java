package oop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import oop.MancalaModel.MancalaSlot;
import oop.MancalaModel.Player;

public class MancalaController
{
	  
	MancalaModel model;
	MancalaView view;

	// Takes in the view and the model
	public MancalaController(MancalaModel model, MancalaView view) {
		this.model = model;
		this.view = view;

		// Get the view and add a listener for the hole
		view.addMancalaHoleListener(createMouseListeners());
		
	
		view.addUndoButton(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Pop from the stack
				model.undo();
			}
		});
		 
		 
        this.view.confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Set the starting slot size and style in the model
                int startingStones = Integer.parseInt((String) view.startingSlotSizeCombo.getSelectedItem());
                model.setStartingStones(startingStones);
                
                Style style = (Style) view.styleCombo.getSelectedItem();
                
         
                model.setStyle(style);
                
                // Close the dialog
                view.optionsDialog.dispose();
                
                
            }
        });
        
		
	}
	
	
	public void seedMancalaPit(MancalaSlot slot)
	{
		
		model.seedStones(slot);
	}
	
	public MouseListener[] createMouseListeners() {
		
		MouseListener[] listeners = new MouseListener[14];
		
		
		for(int i = 0; i < 14; i++) {
			
			MancalaSlot slot = MancalaSlot.getEnum(i);
			
			   listeners[i] = new MouseListener() {
		            @Override
		            public void mouseClicked(MouseEvent e)
					{
		                seedMancalaPit(slot);
		            }

		            @Override
		            public void mousePressed(MouseEvent e) {
		                // Implement mousePressed event handling here
		            }

		            @Override
		            public void mouseReleased(MouseEvent e) {
		                // Implement mouseReleased event handling here
		            }

		            @Override
		            public void mouseEntered(MouseEvent e) {
		                // Implement mouseEntered event handling here
		            }

		            @Override
		            public void mouseExited(MouseEvent e) {
		                // Implement mouseExited event handling here
		            }
		        };			
			
		}
		
		return listeners;
		
	}
	
}
