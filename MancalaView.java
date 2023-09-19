package oop;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import oop.MancalaModel.MancalaSlot;
import oop.MancalaModel.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
// This IS a JFrame
public class MancalaView extends JFrame
{
    private MancalaModel model;
    private JButton undo;

    private JLabel status;
    
    public JButton confirmButton;
    
    JComboBox<String> startingSlotSizeCombo;
    JComboBox<Style> styleCombo;
    JDialog optionsDialog;
    
    Board board;

    public MancalaView(MancalaModel model)
    {
        this.model = new MancalaModel();

        setSize(2000, 800);
        //setLayout(new BorderLayout());
        
        optionsDialog = new JDialog(this, "Options", false);
        optionsDialog.setLayout(new GridLayout(0, 2));
        
        String[] startingSlotSizes = {"3", "4"};
        startingSlotSizeCombo = new JComboBox<>(startingSlotSizes);
        JLabel startingSlotSizeLabel = new JLabel("Starting Slot Size:");
        optionsDialog.add(startingSlotSizeLabel);
        optionsDialog.add(startingSlotSizeCombo);

        // Add a combo box for choosing the style
        Style[] styles = {new UbuntuStyle(), new BlackAndWhiteStyle(), new RedStyle(), };
        styleCombo = new JComboBox<>(styles);
        JLabel styleLabel = new JLabel("Style:");
        optionsDialog.add(styleLabel);
        optionsDialog.add(styleCombo);
        
        confirmButton = new JButton("Confirm");
       
        
        optionsDialog.add(confirmButton);

        
        optionsDialog.pack();
        optionsDialog.setLocationRelativeTo(this);
        optionsDialog.setVisible(true);
   
        
        this.board = new Board(this.model.getBoard());

        add(board, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();

        undo = new JButton("Undo");

        bottomPanel.add(undo);

        status = new JLabel(this.model.getCurrPlayer());
        bottomPanel.add(status);

        add(bottomPanel, BorderLayout.SOUTH);

		model.addSlotChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e)
			{
            	System.out.println(Arrays.toString(model.getBoard()));

				// Update the current status
				getStatus().setText(model.getCurrPlayer());
				getStatus().repaint();

				// Get the stones from the updated model
                board.paintHoles(model.getBoard());
                board.repaint();
            }
        });
		
		model.addStyleChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				
				model.style.applyStyle(board);
			}
		});


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        

        setVisible(true);
        
    }
    


    public void addUndoButton(ActionListener listener)
    {
    	undo.addActionListener(listener);
    }

    
    public void addMancalaHoleListener(MouseListener[] listeners) {
    	
    	this.board.addMancalaHoleListeners(listeners);
    }

    public JLabel getStatus()
    {
        return status;
    }

}



