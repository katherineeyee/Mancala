package oop;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

public class Hole extends JComponent
{
	
	int stones;
    private static final int CIRCLE_SIZE = 20;
    

    public Hole(int stones)
    {
    	this.stones = stones;
    }
    
    public void updateStones(int stones)
    {
    	this.stones = stones;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        // The hole
        Ellipse2D.Double hole = new Ellipse2D.Double(0, 0, 150, 150);
        g2.draw(hole);
        
        
        int stonesAdded = 0;

        // Incrementing position of the stones, it shifts
        int x = 40;
        int y = 50;
                        
        for(int i = 0; i < stones && stonesAdded < stones; i++)
        {
        	for(int j = 0; j < 5 && stonesAdded < stones; j++)
            {
        		 //g2.setColor(Color.GRAY);
                 g2.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                 //g2.setColor(Color.WHITE);
                 g2.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
                 x += CIRCLE_SIZE;
             	stonesAdded++;
        	}
        	
        	x = 40;
        	y += CIRCLE_SIZE;
        }


    }


}

