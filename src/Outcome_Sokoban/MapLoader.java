/*
 * @Description: java class file for a map loader
 * @Version: 4.0
 * @Author: Arvin Zhao
 * @Date: 2018-12-08 13:47:58
 * @Last Editors: Arvin Zhao
 * @LastEditTime: 2018-12-16 00:15:06
 */

package Outcome_Sokoban;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * Class MapLoader that acts as a map loader.
 */
public class MapLoader
{
	/*
	 * the basis for painting a 20 * 20 map;
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
	private int[][] map = new int[20][20];
	private int manX = 0; // X of the tiny man in the map
	private int manY = 0; // Y of the tiny man in the map
	private BufferedReader mapFileReader; // a reader for map files
	
	/**
	 * 1-parameter constructor MapLoader that generates the basis for painting a 20 * 20 map.
	 * @param level a specified level for getting a map file
	 */
	public MapLoader(int level)
	{
		String mapData = ""; // store map data read from a map file
		String eachLine = ""; // store data in each line of a map file
		
		try
		{
			File mapFile = new File(getClass().getResource("").getPath() + "/maps/" + level + ".map"); // load the map file of the specified level
			mapFileReader = new BufferedReader(new FileReader(mapFile));
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error! Something wrong during the process of loading a map file.\n(" + e.toString() + ")", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end try...catch
		
		try
		{
			while ((eachLine = mapFileReader.readLine()) != null)
				mapData += eachLine;
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Error! Something wrong during the process of reading a map file.\n(" + e.toString() + ")", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end try...catch
		
		byte[] mapDataToByte = mapData.getBytes(); // get map data as type byte
		int length = mapData.length(); // the length of map data
		int[] mapDataToInt = new int[length]; // store map data as type int
		int index = 0; // the index of map data of type int in the array mapDataToInt
		
		for (int counter = 0; counter < length; counter++)
			mapDataToInt[counter] = mapDataToByte[counter] - 48;
		
		for (int i = 0; i < 20; i++ )
			for ( int j = 0; j < 20; j++ )
		    {
				map[i][j] = mapDataToInt[index++]; // a line of a map file is a row of a map
				
				// 5 represents the starting position of the tiny man
		        if (map[i][j] == 5)
		        {
					manX = j;
					manY = i;
		        } // end if
		    } // end for
	} // end constructor MapLoader
	
	/**
	 * Get the map of a specified level.
	 * @return the basis for painting a 20 * 20 map of the specified level
	 */
	public int[][] getMap()
	{
		return map;
	} // end method getMap
	
	/**
	 * Get X of the tiny man in the map.
	 * @return X of the tiny man in the map
	 */
	public int getManX()
	{
		return manX;
	} // end method getManX
	
	/**
	 * Get Y of the tiny man in the map.
	 * @return Y of the tiny man in the map
	 */
	public int getManY()
	{
		return manY;
	} // end method getManY
} // end class MapLoader