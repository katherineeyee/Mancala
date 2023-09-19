package oop;
import javax.swing.*;

import oop.MancalaModel.MancalaSlot;

import java.awt.*;
import java.awt.event.MouseListener;

public class Board extends JPanel {

    // Array of Holes
    private Hole[] holes;
    
    
    JPanel holesPanel;

    public Board(int[] board)
    {
        // 2 x 6 holes, the remaining is for the goals
        holes = new Hole[14];
        setLayout(new BorderLayout());

        holesPanel = new JPanel(new GridLayout(2, 6));

        // Loop to make the holes, starting from Player B
        for (int i = 13; i > 6; i--)
        {
            Hole hole = new Hole(board[i]);
            holes[i] = hole;

            // Add holes
            if (i != 13)
            {
                holesPanel.add(hole);
            }

            // Add the goal
            else
            {
            	holes[i] = new Goal(board[i]);
            	add(holes[i], BorderLayout.WEST);
            }
            
        }

        // Holes for Player A
        for (int i = 0; i < 7; i++)
        {

            Hole hole = new Hole(board[i]);
            holes[i] = hole;

            // Add holes
            if (i != 6)
            {
                holesPanel.add(hole);
            }

            // Add the goals
            else
            {
            	holes[i] = new Goal(board[i]);
            	add(holes[i], BorderLayout.EAST);
            }
        }

        JPanel boardPanel = new JPanel(new BorderLayout());
        
        //boardPanel.setBackground(Color.BLACK);
        
        boardPanel.add(holesPanel, BorderLayout.CENTER);

        add(boardPanel, BorderLayout.CENTER);
    }

    // Repaint the holes from the updated model
    public void paintHoles(int[] js)
    {
        for (int i = 0; i < 14; i++)
        {
            holes[i].updateStones(js[i]);
            holes[i].repaint();
        }
    }

    public void addMancalaHoleListeners(MouseListener[] listeners) {
        for (int i = 0; i < 14; i++)
        {
            if (i == 6 | i == 13)
                continue;
            else {
                holes[i].addMouseListener(listeners[i]);
            }
        }
    }

}