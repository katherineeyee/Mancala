package oop;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Goal extends Hole
{
	
	int stones;
    private static final int CIRCLE_SIZE = 20;


    public Goal(int stones)
    {
    	super(stones);
    	this.stones = stones;
    }
    
    public void updateStones(int stones) {
    	this.stones = stones;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        
        Dimension size = getSize();
        
        setPreferredSize(new Dimension(100, 100));
        
        Rectangle2D.Double goal = new Rectangle2D.Double(0, 0, 98, 800);
        g2.draw(goal);
        
        
        int stonesAdded = 0;
        
        int x = 0;
        int y = 0;
        
        System.out.println(stones);
                        
        for(int i = 0; i < stones && stonesAdded < stones; i++) {
        	
            g2.fillOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
            g2.drawOval(x, y, CIRCLE_SIZE, CIRCLE_SIZE);
         	stonesAdded++;
        	
        	y += CIRCLE_SIZE;
        }

    }


}

