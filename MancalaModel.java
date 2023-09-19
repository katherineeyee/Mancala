package oop;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Stack;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


import oop.MancalaModel.MancalaSlot;
import oop.MancalaModel.Player;

public class MancalaModel 
{

    // Mancalla, 2 goal end, 6 x 2 circles, each can hold 4 marbles max
    // A1, A2, A3, A4, A5, A6, B1, B2, B3, B4, B5, B6 are the circles respectively

    // Enums for the Players, Pits
	
	public enum Player
	{
		PLAYERA,
		PLAYERB

	}
	
	public enum MancalaSlot
	{
        A1(0), A2(1), A3(2), A4(3), A5(4), A6(5), A7(6),
        B1(7), B2(8), B3(9), B4(10), B5(11), B6(12), B7(13);
        
        private final int index;
        
        MancalaSlot(int index) {
            this.index = index;
        }
        
        public int getIndex() {
            return index;
        }
        
        public static MancalaSlot getEnum(int index) {
        	
        	MancalaSlot slot = null;
        	
        	for (MancalaSlot s : MancalaSlot.values()) {
        	    if (s.getIndex() == index) {
        	        slot = s;
        	        break;
        	    }
        	}
        	
        	return slot;
        }
    }

	
	final static int BOARD_SIZE = 14;
    
    private int[] board;
    private ArrayList<ChangeListener> slotListeners;

	private ArrayList<ChangeListener> listeners;
	
	ChangeListener styleListener;
	ChangeListener styleSetListener;

    
    Player currPlayer;
    
    int PlayerAUndos;
    int PlayerBUndos;
    
    Stack<int[]> previousBoards;
    
    Style style;
    
    public MancalaModel() 
    { 
		
    	 this.slotListeners = new ArrayList<>();
    	 
    	 this.currPlayer = Player.PLAYERA;
    	
		 board = new int[BOARD_SIZE];
		 
		 board[MancalaSlot.A1.getIndex()] = 3;
		 board[MancalaSlot.A2.getIndex()] = 3;
		 board[MancalaSlot.A3.getIndex()] = 3;
		 board[MancalaSlot.A4.getIndex()] = 3;
		 board[MancalaSlot.A5.getIndex()] = 3;
		 board[MancalaSlot.A6.getIndex()] = 3;
		 
		 // This will be the score for Player A. I'm thinking 
		 //about just incrementing and then when it gets to 6 and then you +1, it will be 7 so it'll go to Player A
		 
		 board[MancalaSlot.A7.getIndex()] = 0;
		 
		 board[MancalaSlot.B1.getIndex()] = 3;
		 board[MancalaSlot.B2.getIndex()] = 3;
		 board[MancalaSlot.B3.getIndex()] = 3;
		 board[MancalaSlot.B4.getIndex()] = 3;
		 board[MancalaSlot.B5.getIndex()] = 3;
		 board[MancalaSlot.B6.getIndex()] = 3;
		 
			 // Player B score
		 board[MancalaSlot.B7.getIndex()] = 0;
		 
		 PlayerAUndos = 3;
		 PlayerBUndos = 3;
		 
		 previousBoards = new Stack<>();
		 
    }
    
    public void setStartingStones(int startingStones) {
		 
		board[MancalaSlot.A1.getIndex()] = startingStones;
		board[MancalaSlot.A2.getIndex()] = startingStones;
		board[MancalaSlot.A3.getIndex()] = startingStones;
		board[MancalaSlot.A4.getIndex()] = startingStones;
		board[MancalaSlot.A5.getIndex()] = startingStones;
		board[MancalaSlot.A6.getIndex()] = startingStones;
		 
		// This will be the score for Player A. I'm thinking 
		//about just incrementing and then when it gets to 6 and then you +1, it will be 7 so it'll go to Player A
		 
		board[MancalaSlot.A7.getIndex()] = 0;
		 
		board[MancalaSlot.B1.getIndex()] = startingStones;
		board[MancalaSlot.B2.getIndex()] = startingStones;
		board[MancalaSlot.B3.getIndex()] = startingStones;
		board[MancalaSlot.B4.getIndex()] = startingStones;
		board[MancalaSlot.B5.getIndex()] = startingStones;
		board[MancalaSlot.B6.getIndex()] = startingStones;
		 
			 // Player B score
		board[MancalaSlot.B7.getIndex()] = 0;
		 
		notifySlotListeners();

    }
    
    public int[] getBoard() {
    	return this.board;
    }
    
    public void setStyle(Style style) {
    	
    	this.style = style;
    	
    	notifyStyleChangeListener();
    }
    
    public boolean undo() {
    	
    	if(currPlayer == Player.PLAYERA) {
    		if(PlayerBUndos == 0) {
    			return false;
    		} else {
    			PlayerBUndos--;
    		}
    	}
    	
    	if(currPlayer == Player.PLAYERB) {
    		if(PlayerAUndos == 0)
    			return false;
    		else
    			PlayerAUndos--;
    	}
    	
    	if(previousBoards.size() < 1)
    		return false;
    	
    	this.board = previousBoards.pop();
    	
    	
    	swapPlayers();
    	
    	notifySlotListeners();
    	
    	return true;
    }

	public String getCurrPlayer()
	{
		return currPlayer.toString();
	}
    
    public void swapPlayers()
	{
    	
    	if(currPlayer == Player.PLAYERA)
    		currPlayer = Player.PLAYERB;
    	else 
    		currPlayer = Player.PLAYERA;
    	
    }

    
    
	
	// Returns ending slot
	public void seedStones(MancalaSlot slot) {
		
		previousBoards.push(this.getBoard().clone());

		Player player = currPlayer;
		
		if(player == Player.PLAYERB && slot.getIndex() < 7)
			return;
		else if(player == Player.PLAYERA && slot.getIndex() >= 7)
			return;
		
    	int index = slot.getIndex();
    	
    	int stones = board[index];
    	board[index] = 0;
    	
    	while(stones > 0) {
    		
    		index = (index + 1);
    		
    		if(index == BOARD_SIZE)
    			index = 0;
    			    		
    		if(index == MancalaSlot.B7.getIndex() && player == Player.PLAYERA ||
    		   index == MancalaSlot.A7.getIndex() && player == Player.PLAYERB) {
    			continue;
    		}
    	 
    		board[index]++;
    		stones--;
    	}
    	
    	
    	MancalaSlot mSlot = MancalaSlot.getEnum(index);
    	
    	handleEmptyLand(mSlot);
    	
    	notifySlotListeners();

    	handlePlayerSwap(mSlot);
	}
	
	
	public void handlePlayerSwap(MancalaSlot slot) {
		
		if(currPlayer == Player.PLAYERA && slot != MancalaSlot.A7)
		{
			this.currPlayer = Player.PLAYERB;
		}
		else if (currPlayer == Player.PLAYERB && slot != MancalaSlot.B7)
		{
			this.currPlayer = Player.PLAYERA;
		}

		notifySlotListeners();
	}
	
	public void handleEmptyLand(MancalaSlot slot) {
	    int index = slot.getIndex();
	    int stones = board[index];
	    Player player = currPlayer;
	    
	    if (stones == 1 && index != MancalaSlot.A7.getIndex() && index != MancalaSlot.B7.getIndex()) {
	    	
	    	System.out.println("dfasdfa");
	    	
	        if (player == Player.PLAYERA && index < 7 && board[BOARD_SIZE - index - 2] > 0) {
	        	
	            // move stone from opposite pit to player A's mancala
	            board[MancalaSlot.A7.getIndex()] += board[BOARD_SIZE - index - 2] + 1;
	            board[BOARD_SIZE - index - 2] = 0;
	            board[index] = 0;
	        } else if (player == Player.PLAYERB && index > 6 && board[BOARD_SIZE - index] > 0) {
	        	
	            // move stone from opposite pit to player B's mancala
	            board[MancalaSlot.B7.getIndex()] += board[BOARD_SIZE - index] + 1;
	            board[BOARD_SIZE - index] = 0;
	            board[index] = 0;
	        }
	    }
	}
	
	public void notifySlotListeners() {
		
		ChangeEvent event = new ChangeEvent(this);
		
    	for(ChangeListener l : slotListeners) {
    		l.stateChanged(event);
    	}
	}
	
	public void notifyStyleChangeListener() {
		
		ChangeEvent event = new ChangeEvent(this);
		
		styleListener.stateChanged(event);
		
	}
	
	
	public void addStyleSetListener(ChangeListener listener) {
		styleSetListener = listener;
	}
	
	public void addStyleChangeListener(ChangeListener listener) {
		styleListener = listener;
	}
	
	public void addSlotChangeListener(ChangeListener listener) {
		slotListeners.add(listener);
	}

}
