/*
 * @Description: java class file for the game area
 * @Version: 4.0
 * @Author: Arvin Zhao
 * @Date: 2018-12-08 13:42:53
 * @Last Editors: Arvin Zhao
 * @LastEditTime: 2018-12-16 00:15:47
 */

package Outcome_Sokoban;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Implementing interface KeyListener, this class contains methods for controlling the game area.
 */
public class GameArea extends JPanel implements KeyListener
{
	private static final long serialVersionUID = 1L; // default serial version ID for the serialisable class GameArea to try to avoid any compatibility issue
	private int level = 1; // the current level with an initial value of 1
	private int finalLevel = 5; // the final level
	private int step = 0; // the step of the tiny man with an initial value of 0
	/*
	 * the basis of a map which shows the tiny man in real time;
	 * 0 represents the ground outside;
	 * 1 represents the wall;
	 * 2 represents the ground inside;
	 * 3 represents a crate;
	 * 4 represents a diamond;
	 * 5 represents the front of the tiny man;
	 * 6 represents the left of the tiny man;
	 * 7 represents the right of the tiny man;
	 * 8 represents the back of the tiny man;
	 * 9 represents a crate on a diamond
	 */
	private int[][] mapData;
	/*
	 * he basis of an original map
	 * 0 represents the ground outside;
	 * 1 represents the wall;
	 * 2 represents the ground inside;
	 * 3 represents a crate;
	 * 4 represents a diamond;
	 * 5 represents the front of the tiny man;
	 * 6 represents the left of the tiny man;
	 * 7 represents the right of the tiny man;
	 * 8 represents the back of the tiny man;
	 * 9 represents a crate on a diamond
	 */
	private int[][] tempMapData;
	private int manX; // X of the tiny man in the map
	private int manY; // Y of the tiny man in the map
	private Image[] mapElements; // store relevant pictures of the map elements
	private MapLoader map; // instance a MapLoader object for generating a map which shows the tiny man in real time
	private MapLoader tempMap; // instance a MapLoader object for generating an original map
	private Stack<Integer> undoingRecord = new Stack<Integer>(); // a LIFO stack of objects for undoing records
	
	/**
	 * Non-parameter constructor that initialises the game area.
	 */
	public GameArea()
	{	
		setVisible(true);
		setBounds(10, 10, 600, 600);
		setBackground(Color.WHITE);
		addKeyListener(this);
		
		mapElements = new Image[10];
		
		for (int counter = 0; counter < 10; counter++)
			mapElements[counter] = Toolkit.getDefaultToolkit().getImage(getClass().getResource("").getPath() + "/img/" + counter + ".png");
	} // end constructor GameArea
	
	// clear undoing records
	private void clearUndoingRecord()
	{
		undoingRecord.removeAllElements();
		step = 0;
	} // end method clearUndoingRecord
	
	// do specified actions if the current level is passed
	private void actionForLevelPassed()
	{
		// call the specified method to check if the current level is passed
		if (isPassed())
		{
			if (level == finalLevel)
			{
				JOptionPane.showMessageDialog(this, "Congratulations! The final level passed!\nGame over!", "Sokoban - Zhao Jichen", JOptionPane.PLAIN_MESSAGE);
				
				if (JOptionPane.showConfirmDialog(this, "Are you sure to stop playing Sokoban?", "Sokoban - Zhao Jichen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
					System.exit(0); // completely exit the program
			}
			else
			{
				if (JOptionPane.showConfirmDialog(this, "Congratulations! Level " + level + " passed!\nGo to next level?", "Sokoban - Zhao Jichen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				{
					level++;
					loadGameArea(); // call the specified method to load the game area
				} // end if
			} // end if...else
		} // end if
	} // end method actionForLevelPassed
	
	// restore a diamond or the ground inside after the tiny man moves
	private void restoreDiamondOrGroundInside()
	{
		if (tempMapData[manY][manX] == 4 || tempMapData[manY][manX] == 9 )
			mapData[manY][manX] = 4;
		else
			mapData[manY][manX] = 2;
	} // end method restoreDiamondOrGroundInside
	
	// restore a crate on a diamond or just a crate after the tiny man moves
	private void restoreCrate()
	{
		if (tempMapData[manY][manX] == 4 || tempMapData[manY][manX] == 9 )
			mapData[manY][manX] = 9;
		else
			mapData[manY][manX] = 3;
	} // end method restoreCrate
	
	/**
	 * Get the current level.
	 * @return the current level
	 */
	public int getLevel()
	{
		return level;
	} // end method getLevel
	
	/**
	 * Set the current level.
	 * @param level the current level
	 */
	public void setLevel(int level)
	{
		this.level = level;
	} // end method setLevel
	
	/**
	 * Get the final level.
	 * @return the final level
	 */
	public int getFinalLevel()
	{
		return finalLevel;
	} // end method getFinalLevel
	
	/**
	 * It is this method which can be called by method repaint that is invoked by Swing to draw components.
	 */
	public void paint(Graphics g)
	{
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
			    g.drawImage(mapElements[mapData[j][i]], i * 30, j * 30, this);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Level " + level, 250, 40); // display the level info
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.drawString("Step " + step, 280, 80); // display the step info
	} // end method paint
	
	/**
	 * Load the game area.
	 */
	public void loadGameArea()
	{
		map = new MapLoader(level);
		tempMap = new MapLoader(level);
		mapData = map.getMap(); // call the specified method in class MapLoader to get the map of a specified level
		manX = map.getManX(); // call the specified method in class MapLoader to get X of the tiny man in the map
		manY = map.getManY(); // call the specified method in class MapLoader to get Y of the tiny man in the map
		tempMapData = tempMap.getMap(); // call the specified method in class MapLoader to get the map of a specified level
		repaint();
		clearUndoingRecord(); // call the specified method to clear undoing records
	} // end method loadGameArea
	
	/**
	 * It is the method which must be implemented to respond to the user press on a specified key that is in the interface KeyListener.
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			moveUp(); // call the specified method to try to move up a step
			actionForLevelPassed(); // call the specified method to do specified actions if the current level is passed
		} // end if
		
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			moveDown(); // call the specified method to try to move down a step
			actionForLevelPassed(); // call the specified method to do specified actions if the current level is passed
		} // end if
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			moveLeft(); // call the specified method to try to move left a step
			actionForLevelPassed(); // call the specified method to do specified actions if the current level is passed
		} // end if
		
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			moveRight(); // call the specified method to try to move right a step
			actionForLevelPassed(); // call the specified method to do specified actions if the current level is passed
		} // end if
	} // end method keyPressed
	
	@Override
	public void keyTyped(KeyEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface KeyListener
	} // end method keyTyped
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface KeyListener
	} // end method keyReleased
	
	/**
	 * Check if the undoing record is empty.
	 * @return the boolean value true if the undoing object is empty, or false if it is full
	 */
	public boolean isUndoingRecordEmpty()
	{
		return undoingRecord.isEmpty();
	} // end method isUndoingRecordEmpty
	
	/**
	 * Undo a step represented by ID.
	 * @return ID which represents a kind of step
	 */
	public int undo()
	{
		return undoingRecord.pop();
	} // end method undo
	
	/**
	 * Try to move up a step.
	 */
	public void moveUp()
	{
		// the element above the tiny man before moving
		switch (mapData[manY - 1][manX])
		{
		case 1:
			mapData[manY][manX] = 8;
			repaint();
			break;
			
		case 2:
		case 4:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			mapData[manY - 1][manX] = 8;
			repaint();
			manY--;
			step++;
			undoingRecord.push(10);
			break;
			
		case 3:
		case 9:
			// the element above the element above the tiny man before moving
			if (mapData[manY - 2][manX] == 4)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY - 1][manX] = 8;
				mapData[manY - 2][manX] = 9;
				repaint();
				manY--;
				step++;
				undoingRecord.push(11);
			}
			else if (mapData[manY - 2][manX] == 2)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY - 1][manX] = 8;
				mapData[manY - 2][manX] = 3;
				repaint();
				manY--;
				step++;
				undoingRecord.push(11);
			}
			else
			{
				mapData[manY][manX] = 8;
				repaint();
			} // end nested if...else
			break;
		
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of trying to move up a step.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
	} // end method moveUp
	
	/**
	 * Undo a move-up step.
	 * @param id ID which represents a move-up step
	 */
	public void undoUp(int id)
	{
		switch (id)
		{
		case 10:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			break;
			
		case 11:
			restoreCrate(); // call the specified method to restore a crate on a diamond or just a crate after the tiny man moves
			// the element above the tiny man before moving
			if (tempMapData[manY - 1][manX] == 4 || tempMapData[manY - 1][manX] == 9)
				mapData[manY - 1][manX] = 4;
			else
				mapData[manY - 1][manX] = 2;
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of undo moving up.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
		
		mapData[manY + 1][manX] = 8; // the element below the tiny man
		repaint();
		manY++;
		step--;
	} // end method undoUp
	
	/**
	 * Try to move down a step.
	 */
	public void moveDown()
	{
		// the element below the tiny man before moving
		switch (mapData[manY + 1][manX])
		{
		case 1:
			mapData[manY][manX] = 5;
			repaint();
			break;
			
		case 2:
		case 4:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			mapData[manY + 1][manX] = 5;
			repaint();
			manY++;
			step++;
			undoingRecord.push(20);
			break;
			
		case 3:
		case 9:
			// the element below the element below the tiny man before moving
			if (mapData[manY + 2][manX] == 4)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY + 1][manX] = 5;
				mapData[manY + 2][manX] = 9;
				repaint();
				manY++;
				step++;
				undoingRecord.push(21);
			}
			else if (mapData[manY + 2][manX] == 2)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY + 1][manX] = 5;
				mapData[manY + 2][manX] = 3;
				repaint();
				manY++;
				step++;
				undoingRecord.push(21);
			}
			else
			{
				mapData[manY][manX] = 5;
				repaint();
			} // end nested if...else
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of trying to move down a step.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
	} // end method moveDown
	
	/**
	 * Undo a move-down step.
	 * @param id ID which represents a move-down step
	 */
	public void undoDown(int id)
	{
		switch (id)
		{
		case 20:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			break;
			
		case 21:
			restoreCrate(); // call the specified method to restore a crate on a diamond or just a crate after the tiny man moves
			// the element below the tiny man before moving
			if (tempMapData[manY + 1][manX] == 4 || tempMapData[manY + 1][manX] == 9)
				mapData[manY + 1][manX] = 4;
			else
				mapData[manY + 1][manX] = 2;
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of undo moving down.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
		
		mapData[manY - 1][manX] = 5;  // the element above the tiny man
		repaint();
		manY--;
		step--;
	} // end method undoDown
	
	/**
	 * Try to move left a step.
	 */
	public void moveLeft()
	{
		// the element on the left side of the tiny man before moving
		switch (mapData[manY][manX - 1])
		{
		case 1:
			mapData[manY][manX] = 6;
			repaint();
			break;
			
		case 2:
		case 4:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			mapData[manY][manX - 1] = 6;	
			repaint();
			manX--;
			step++;
			undoingRecord.push(30);
			break;
			
		case 3:
		case 9:
			// the element on the left side of the element on the left side of the tiny man before moving
			if (mapData[manY][manX - 2] == 4)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY][manX - 1] = 6;
				mapData[manY][manX - 2] = 9;
				repaint();
				manX--;
				step++;
				undoingRecord.push(31);
			}
			else if (mapData[manY][manX - 2] == 2)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY][manX - 1] = 6;
				mapData[manY][manX - 2] = 3;
				repaint();
				manX--;
				step++;
				undoingRecord.push(31);
			}
			else
			{
				mapData[manY][manX] = 6;
				repaint();
			} // end nested if...else
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of trying to move left a step.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
	} // end method moveLeft
	
	/**
	 * Undo a move-left step.
	 * @param id ID which represents a move-left step
	 */
	public void undoLeft(int id)
	{
		switch (id)
		{
		case 30:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			break;
			
		case 31:
			restoreCrate(); // call the specified method to restore a crate on a diamond or just a crate after the tiny man moves
			// the element on the left side of the tiny man before moving
			if (tempMapData[manY][manX - 1] == 4 || tempMapData[manY][manX - 1] == 9)
				mapData[manY][manX - 1] = 4;
			else
				mapData[manY][manX - 1] = 2;
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of undo moving left.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
		
		mapData[manY][manX + 1] = 6; // the element on the right side of the tiny man
		repaint();
		manX++;
		step--;
	} // end method undoLeft
	
	/**
	 * Try to move right a step.
	 */
	public void moveRight()
	{
		// the element on the right side of the tiny man before moving
		switch (mapData[manY][manX + 1])
		{
		case 1:
			mapData[manY][manX] = 7;
			repaint();
			break;
			
		case 2:
		case 4:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			mapData[manY][manX + 1] = 7;			
			repaint();
			manX++;
			step++;
			undoingRecord.push(40);
			break;
			
		case 3:
		case 9:
			// the element on the right side of the element on the right side of the tiny man before moving
			if (mapData[manY][manX + 2] == 4)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY][manX + 1] = 7;
				mapData[manY][manX + 2] = 9;
				repaint();
				manX++;
				step++;
				undoingRecord.push(41);
			}
			else if (mapData[manY][manX + 2] == 2)
			{
				restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
				mapData[manY][manX + 1] = 7;
				mapData[manY][manX + 2] = 3;
				repaint();
				manX++;
				step++;
				undoingRecord.push(41);
			}
			else
			{
				mapData[manY][manX] = 7;
				repaint();
			} // end nested if...else
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of trying to move right a step.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
	} // end method moveRight
	
	/**
	 * Undo a move-right step.
	 * @param id ID which represents a move-right step
	 */
	public void undoRight(int id)
	{
		switch (id)
		{
		case 40:
			restoreDiamondOrGroundInside(); // call the specified method to restore a diamond or the ground inside after the tiny man moves
			break;
			
		case 41:
			restoreCrate(); // call the specified method to restore a crate on a diamond or just a crate after the tiny man moves
			// the element on the right side of the tiny man before moving
			if (tempMapData[manY][manX + 1] == 4 || tempMapData[manY][manX + 1] == 9)
				mapData[manY][manX + 1] = 4;
			else
				mapData[manY][manX + 1] = 2;
			break;
			
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of undo moving right.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
		
		mapData[manY][manX - 1] = 7; // the element on the left side of the tiny man
		repaint();
		manX--;
		step--;
	} // end method undoRight
	
	/**
	 * Check if the current level is passed.
	 * @return the boolean value true if the current level is passed, or false if it is not passed
	 */
	public boolean isPassed()
	{
		boolean isPassed = false;
		
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++)
			{
				if (tempMapData[i][j] == 4 || tempMapData[i][j] == 9)
					if (mapData[i][j] == 9)
						isPassed = true;
					else
					{
						isPassed = false;
						return isPassed;
					} // end if...else
			} // end for
		
		return isPassed;
	} // end method isPassed
} // end class GameArea