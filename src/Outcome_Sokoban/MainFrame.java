/*
 * @Description: java class file for the main frame
 * @Version: 4.0
 * @Author: Arvin Zhao
 * @Date: 2018-12-08 13:33:29
 * @Last Editors: Arvin Zhao
 * @LastEditTime: 2018-12-16 19:44:51
 */

package Outcome_Sokoban;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Implementing interfaces WindowListener, ActionListener, and ItemListener, this class contains component controllers for the main frame.
 */
public class MainFrame extends JFrame implements WindowListener, ActionListener, ItemListener
{
	private static final long serialVersionUID = 1L; // default serial version ID for the serialisable class MainFrame to try to avoid any compatibility issue
	private GameArea game; // instance a GameArea object for displaying the game area
	private BgmManager bgm; // instance a BgmManager object for managing BGM
	
	// instance some menu items for the menu bar as well as some buttons, a label, and a combo box for the option area
	private JMenuItem menuItemUndo = new JMenuItem("Undo (Z)");
	private JMenuItem menuItemReplay = new JMenuItem("Replay");
	private JMenuItem menuItemLastLevel = new JMenuItem("Last Level");
	private JMenuItem menuItemNextLevel = new JMenuItem("Next Level");
	private JMenuItem menuItemSelectLevel = new JMenuItem("Select Level...");
	private JCheckBoxMenuItem checkBoxMenuItemPlayBgm = new JCheckBoxMenuItem("Play BGM");
	private JMenuItem menuItemBlueDanube = new JMenuItem("Blue Danube");
	private JMenuItem menuItemCourage = new JMenuItem("Courage");
	private JMenuItem menuItemLuster = new JMenuItem("Luster");
	private JMenuItem menuItemTenYears = new JMenuItem("Ten Years");
	private JMenuItem menuItemTitanic = new JMenuItem("Titanic");
	private JMenuItem menuItemExit = new JMenuItem("Exit");
	private JMenuItem menuItemHelpContents = new JMenuItem("Help Contents");
	private JMenuItem menuItemAboutSokoban = new JMenuItem("About Sokoban");
	private JButton buttonUndo, buttonReplay, buttonLastLevel, buttonNextLevel, buttonSelectLevel, buttonFirstLevel, buttonFinalLevel, buttonBgmOnOrOff;
	private JLabel labelSelectBgm;
	private JComboBox<String> comboBoxSelectBgm = new JComboBox<String>();
    
	/**
	 * Non-parameter constructor MainFrame that initialises components on the main frame.
	 */
	public MainFrame()
	{
		// get the logo image
		ImageIcon logo = new ImageIcon(getClass().getResource("").getPath() + "/img/logo.jpg");
		Image logoImage = logo.getImage();
		
		setIconImage(logoImage); // set the logo
		setTitle("Sokoban - Zhao Jichen"); // set the title of the main form
		setVisible(true); // make the main form visible
		setSize(765, 680); // set the size of the main form
		setResizable(false); // the user cannot resize the main form
		setLocationRelativeTo(null); // display the main form in the centre of the screen
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); // execute the method windowClosing when the user initiates a "close" on the main form
		addWindowListener(this);
		
		// create a container for the components on the main form
		Container containerMainFrame = getContentPane();
		containerMainFrame.setLayout(null); // use the absolute layout for the main form
		containerMainFrame.setBackground(Color.GRAY);
		
		// the menu Options, its menu items, and relevant listeners
		JMenu menuOptions = new JMenu("Options");
		menuOptions.setMnemonic(KeyEvent.VK_O); // the user can press Alt + O to expand the menu Options
		menuOptions.add(menuItemUndo);
		menuOptions.add(menuItemReplay);
		menuOptions.addSeparator();
		menuOptions.add(menuItemLastLevel);
		menuOptions.add(menuItemNextLevel);
		menuOptions.add(menuItemSelectLevel);
		menuOptions.addSeparator();
		menuOptions.add(checkBoxMenuItemPlayBgm);
		JMenu subMenuSelectBgm = new JMenu("Select BGM");
		menuOptions.add(subMenuSelectBgm);
		subMenuSelectBgm.add(menuItemBlueDanube);
		subMenuSelectBgm.add(menuItemCourage);
		subMenuSelectBgm.add(menuItemLuster);
		subMenuSelectBgm.add(menuItemTenYears);
		subMenuSelectBgm.add(menuItemTitanic);
		menuOptions.addSeparator();
		menuOptions.add(menuItemExit);
		menuItemUndo.setMnemonic(KeyEvent.VK_Z); // the user can press Z or Alt + Z to undo a step when the menu Options is expanded
		menuItemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK)); // the user can press Ctrl + Z to undo a step as long as the main frame is activated
		menuItemUndo.addActionListener(this);
		menuItemReplay.addActionListener(this);
		menuItemLastLevel.addActionListener(this);
		menuItemNextLevel.addActionListener(this);
		menuItemSelectLevel.addActionListener(this);
		checkBoxMenuItemPlayBgm.setSelected(true);
		checkBoxMenuItemPlayBgm.addActionListener(this);
		menuItemBlueDanube.setEnabled(false);
		menuItemBlueDanube.addActionListener(this);
		menuItemCourage.addActionListener(this);
		menuItemLuster.addActionListener(this);
		menuItemTenYears.addActionListener(this);
		menuItemTitanic.addActionListener(this);
		menuItemExit.addActionListener(this);
		
		// the menu Help, its menu item, and a relevant listener
		JMenu menuHelp = new JMenu("Help");
		menuHelp.setMnemonic(KeyEvent.VK_H); // the user can press Alt + H to expand the menu Help
		menuHelp.add(menuItemHelpContents);
		menuHelp.addSeparator();
		menuHelp.add(menuItemAboutSokoban);
		menuItemHelpContents.addActionListener(this);
		menuItemAboutSokoban.addActionListener(this);
		
		// the menu bar on the main frame including the menus Options and Help
		JMenuBar menuBarMainFrame = new JMenuBar();
		setJMenuBar(menuBarMainFrame);
		menuBarMainFrame.add(menuOptions);
		menuBarMainFrame.add(menuHelp);
		
		// the buttons and relevant listeners for the option area
		buttonUndo = new JButton("Undo");
		buttonReplay = new JButton("Replay");
        buttonLastLevel = new JButton("Last level");
        buttonNextLevel = new JButton("Next level");
        buttonSelectLevel = new JButton("Select level...");
        buttonFirstLevel = new JButton("Level 1");
        buttonFinalLevel = new JButton("Final level");
        buttonBgmOnOrOff = new JButton("BGM ON");
        containerMainFrame.add(buttonUndo);
        containerMainFrame.add(buttonReplay);
        containerMainFrame.add(buttonLastLevel);
        containerMainFrame.add(buttonNextLevel);
        containerMainFrame.add(buttonSelectLevel);
        containerMainFrame.add(buttonFirstLevel);
        containerMainFrame.add(buttonFinalLevel);
        containerMainFrame.add(buttonBgmOnOrOff);
		buttonUndo.setBounds(620, 50, 120, 30);
		buttonUndo.setToolTipText("You can also press Ctrl + Z to undo a step.");
		buttonUndo.addActionListener(this);
		buttonReplay.setBounds(620, 100, 120, 30);
		buttonReplay.addActionListener(this);
		buttonLastLevel.setBounds(620, 150, 120, 30);
		buttonLastLevel.addActionListener(this);
		buttonNextLevel.setBounds(620, 200, 120, 30);
		buttonNextLevel.addActionListener(this);
		buttonSelectLevel.setBounds(620, 250, 120, 30);
		buttonSelectLevel.addActionListener(this);
		buttonFirstLevel.setBounds(620, 300, 120, 30);
		buttonFirstLevel.setToolTipText("Go to Level 1.");
		buttonFirstLevel.addActionListener(this);
		buttonFinalLevel.setBounds(620, 350, 120, 30);
		buttonFinalLevel.setToolTipText("Go to the final level.");
		buttonFinalLevel.addActionListener(this);
		buttonBgmOnOrOff.setBounds(620, 400, 120, 30);
		buttonBgmOnOrOff.setToolTipText("Click on me to stop BGM.");
		buttonBgmOnOrOff.addActionListener(this);
		
		// the label in the option area
		labelSelectBgm = new JLabel("Select BGM:", SwingConstants.CENTER);
		containerMainFrame.add(labelSelectBgm);
		labelSelectBgm.setBounds(620, 450, 70, 20);
		labelSelectBgm.setForeground(Color.WHITE);
		
		// the combo box, its items, and a relevant listener for the option area
		containerMainFrame.add(comboBoxSelectBgm);
		comboBoxSelectBgm.setBounds(620, 470, 120, 20);
		comboBoxSelectBgm.addItem("Blue Danube");
		comboBoxSelectBgm.addItem("Courage");
		comboBoxSelectBgm.addItem("Luster");
		comboBoxSelectBgm.addItem("Ten Years");
		comboBoxSelectBgm.addItem("Titanic");
		comboBoxSelectBgm.addItemListener(this);
		
		bgm = new BgmManager();
		bgm.playBgm(); // call the specified method in class BgmManager to load and play BGM
		game = new GameArea();
		add(game); // add the game area to the main frame to display it
		game.loadGameArea(); // call the specified method in class GameArea to load the game area
		game.requestFocus();
		validate();
	} // end constructor MainFrame
	
	/**
	 * Ask the user to confirm whether to exit the program or not.
	 */
	private void actionBeforeExiting()
	{
		if (JOptionPane.showConfirmDialog(this, "Are you sure to stop playing Sokoban?", "Sokoban - Zhao Jichen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
			System.exit(0); // completely exit the program
		else
			game.requestFocus();
	} // end method actionBeforeExiting
	
	/**
	 * Change BGM played according to the item selected in the combo box.
	 * @param index The index of the item selected in the combo box.
	 */
	private void changeBgmInComboBox(int index)
	{
		bgm.setMidiFileIndex(index); // call the specified method in class BgmManager to set the index of a MIDI file for playing
		
		// call the specified method in class BgmManager to check if BGM is played
		if (bgm.getPlayingStatus())
			bgm.stopBgm(); // call the specified method in class BgmManager to stop BGM
		
		bgm.playBgm(); // call the specified method in class BgmManager to load and play BGM
		buttonBgmOnOrOff.setText("BGM ON");
		buttonBgmOnOrOff.setToolTipText("Click on me to stop BGM.");
	} // end method changeBgmInComboBox
	
	@Override
	public void windowOpened(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowOpened

	@Override
	// it is the method which must be implemented to respond to the main frame to be closed that is in the interface WindowListener
	public void windowClosing(WindowEvent e)
	{
		actionBeforeExiting(); // call the specified method to ask the user to confirm whether to exit the program or not
	} // end method windowClosing
	
	@Override
	public void windowClosed(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowClosed

	@Override
	public void windowIconified(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowIconified

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowDeiconified

	@Override
	public void windowActivated(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowActivated

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// it is the method which must be implemented but acts nothing in the program that is in the interface WindowListener
	} // end method windowDeactivated
	
	/**
	 * It is the method which must be implemented to respond to the user click on a specified button or menu item that is in the interface ActionListener.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		int level = game.getLevel(); // call the specified method in GameArea to get the current level
		int finalLevel = game.getFinalLevel(); // call the specified method in GameArea to get the final level
		
		// undo a step
		if (e.getSource() == buttonUndo || e.getSource() == menuItemUndo)
		{
			// call the specified method to check if the undoing record is empty
			if (game.isUndoingRecordEmpty())
				JOptionPane.showMessageDialog(this, "No step to undo! The tiny man does not move.", "Sokoban - Zhao Jichen", JOptionPane.WARNING_MESSAGE);
			else
			{
				// call the specified method in class GameArea to undo a step represented by ID
				switch (game.undo())
				{
				case 10:
					game.undoUp(10); // call the specified method in class GameArea to undo a move-up step
					break;
					
				case 11:
					game.undoUp(11); // call the specified method in class GameArea to undo a move-up step
					break;
					
				case 20:
					game.undoDown(20); // call the specified method in class GameArea to undo a move-down step
					break;
					
				case 21:
					game.undoDown(21); // call the specified method in class GameArea to undo a move-down step
					break;
					
				case 30:
					game.undoLeft(30); // call the specified method in class GameArea to undo a move-left step
					break;
					
				case 31:
					game.undoLeft(31); // call the specified method in class GameArea to undo a move-left step
					break;
					
				case 40:
					game.undoRight(40); // call the specified method in class GameArea to undo a move-right step
					break;
					
				case 41:
					game.undoRight(41); // call the specified method in class GameArea to undo a move-right step
					break;
				
				default:
					JOptionPane.showMessageDialog(this, "Error! Something wrong during the undoing process.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
				} // end switch-case
			} // end if...else
			
			game.requestFocus();
		}
		// replay the current level
		else if (e.getSource() == buttonReplay || e.getSource()== menuItemReplay)
		{
			game.loadGameArea(); // call the specified method in GameArea to load the game area
			game.requestFocus();
		}
		// go to last level
		else if (e.getSource() == buttonLastLevel || e.getSource() == menuItemLastLevel)
		{			
			if (level - 1 < 1)
			{
				JOptionPane.showMessageDialog(this, "No last level! You are now at Level 1.", "Sokoban - Zhao Jichen", JOptionPane.WARNING_MESSAGE);
				game.requestFocus();
			}
			else 
			{
				game.setLevel(--level); // call the specified method in class GameArea to set the current level
				game.loadGameArea(); // call the specified method in class GameArea to load the game area
				game.requestFocus();
			} // end if...else
		}
		// go to next level
		else if (e.getSource() == buttonNextLevel || e.getSource() == menuItemNextLevel)
		{
			if (level + 1 > finalLevel )
			{
				JOptionPane.showMessageDialog(this, "No next level! You are now at the final level.", "Sokoban - Zhao Jichen", JOptionPane.WARNING_MESSAGE);
				game.requestFocus();
			}
			else 
			{
				game.setLevel(++level); // call the specified method in class GameArea to set the current level
				game.loadGameArea(); // call the specified method in class GameArea to load the game area
				game.requestFocus();
			} // end if...else
		}
		// select a level
		else if (e.getSource() == buttonSelectLevel || e.getSource() == menuItemSelectLevel)
		{
			String selection = JOptionPane.showInputDialog(this, "Please enter the level you want to select (1 - " + finalLevel + "):", "Sokoban - Zhao Jichen", JOptionPane.PLAIN_MESSAGE);
			
			if (selection != null)
			{
				try
				{
					int levelSelected = Integer.parseInt(selection.trim());
					
					if (levelSelected > finalLevel || levelSelected < 1)
					{
						JOptionPane.showMessageDialog(this, "No such level! Not an integer from 1 to " + finalLevel + "?", "Sokoban - Zhao Jichen", JOptionPane.WARNING_MESSAGE);
						game.requestFocus();
					}
					else
					{
						game.setLevel(levelSelected); // call the specified method in class GameArea to set the current level
						game.loadGameArea(); // call the specified method in class GameArea to load the game area
						game.requestFocus();
					} // end if...else
				}
				catch (NumberFormatException exception)
				{
					JOptionPane.showMessageDialog(this, "Illegal input! Please enter an integer from 1 to " + finalLevel + ".\n(" + exception.toString() + ")", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
					game.requestFocus();
				} // end try...catch
			}
			else
				game.requestFocus();
		}
		// go to Level 1
		else if (e.getSource() == buttonFirstLevel)
		{
			game.setLevel(1); // call the specified method in class GameArea to set the current level
			game.loadGameArea(); // call the specified method in class GameArea to load the game area
			game.requestFocus();
		}
		// go to the final level
		else if (e.getSource() == buttonFinalLevel)
		{
			game.setLevel(finalLevel); // call the specified method in class GameArea to set the current level
			game.loadGameArea(); // call the specified method in class GameArea to load the game area
			game.requestFocus();
		}
		// play or stop BGM
		else if (e.getSource() == buttonBgmOnOrOff || e.getSource() == checkBoxMenuItemPlayBgm)
		{
			// call the specified method in class BgmManager to check if BGM is played
			if (bgm.getPlayingStatus())
			{
				bgm.stopBgm(); // call the specified method in class BgmManager to stop BGM
				buttonBgmOnOrOff.setText("BGM OFF");
				buttonBgmOnOrOff.setToolTipText("Click on me to play BGM.");
				checkBoxMenuItemPlayBgm.setSelected(false);
			}
			else 
			{
				bgm.playBgm(); // call the specified method in class BgmManager to load and play BGM
				buttonBgmOnOrOff.setText("BGM ON");
				buttonBgmOnOrOff.setToolTipText("Click on me to stop BGM.");
				checkBoxMenuItemPlayBgm.setSelected(true);
			} // end if...else
			
			game.requestFocus();
		}
		// select BGM of Index 0
		else if (e.getSource() == menuItemBlueDanube)
			comboBoxSelectBgm.setSelectedIndex(0);
		// select BGM of Index 1
		else if (e.getSource() == menuItemCourage)
			comboBoxSelectBgm.setSelectedIndex(1);
		// select BGM of Index 2
		else if (e.getSource() == menuItemLuster)
			comboBoxSelectBgm.setSelectedIndex(2);
		// select BGM of Index 3
		else if (e.getSource() == menuItemTenYears)
			comboBoxSelectBgm.setSelectedIndex(3);
		// select BGM of Index 4
		else if (e.getSource() == menuItemTitanic)
			comboBoxSelectBgm.setSelectedIndex(4);
		// exit the program
		else if (e.getSource() == menuItemExit)
			actionBeforeExiting(); // call the specified method to ask the user to confirm whether to exit the program or not
		// help contents
		else if (e.getSource() == menuItemHelpContents)
			JOptionPane.showMessageDialog(this, "1. A menu bar and the option area are provided for you to\n    undo a step, replay the current level, go to last level, go\n    to next level, select a level, go to Level 1, go to the final\n    level, play or stop BGM, and select BGM.\n2. Press ↑ / ↓ / ← / → to move the tiny man.\n3. To expand the menu Options conveniently, press Alt + O.\n    To expand the menu Help conveniently, press Alt + H.\n    To undo a step conveniently, press Ctrl + Z.", "Help Contents", JOptionPane.PLAIN_MESSAGE);
		// about Sokoban
		else if (e.getSource() == menuItemAboutSokoban)
			JOptionPane.showMessageDialog(this, "Sokoban\n\nVersion: 4.0\nAuthor: Zhao Jichen (SCN: 187115469)\n\n© 2018 Retro Games", "About Sokoban", JOptionPane.PLAIN_MESSAGE);
	} // end method actionPerformed
	
	/**
	 * It is the method which must be implemented to respond to the change of the item selected in the combo box for selecting BGM that is in the interface ItemListener.
	 */
	@Override 
	public void itemStateChanged(ItemEvent e)
	{
		switch (comboBoxSelectBgm.getSelectedIndex())
		{
		case 0:
			changeBgmInComboBox(0); // call the specified method to change BGM played according to the item selected in the combo box
			menuItemBlueDanube.setEnabled(false);
			menuItemCourage.setEnabled(true);
			menuItemLuster.setEnabled(true);
			menuItemTenYears.setEnabled(true);
			menuItemTitanic.setEnabled(true);
			game.requestFocus();
			break;
			
		case 1:
			changeBgmInComboBox(1); // call the specified method to change BGM played according to the item selected in the combo box
			menuItemBlueDanube.setEnabled(true);
			menuItemCourage.setEnabled(false);
			menuItemLuster.setEnabled(true);
			menuItemTenYears.setEnabled(true);
			menuItemTitanic.setEnabled(true);
			game.requestFocus();
			break;
			
		case 2:
			changeBgmInComboBox(2); // call the specified method to change BGM played according to the item selected in the combo box
			menuItemBlueDanube.setEnabled(true);
			menuItemCourage.setEnabled(true);
			menuItemLuster.setEnabled(false);
			menuItemTenYears.setEnabled(true);
			menuItemTitanic.setEnabled(true);
			game.requestFocus();
			break;
			
		case 3:
			changeBgmInComboBox(3); // call the specified method to change BGM played according to the item selected in the combo box
			menuItemBlueDanube.setEnabled(true);
			menuItemCourage.setEnabled(true);
			menuItemLuster.setEnabled(true);
			menuItemTenYears.setEnabled(false);
			menuItemTitanic.setEnabled(true);
			game.requestFocus();
			break;
			
		case 4:
			changeBgmInComboBox(4); // call the specified method to change BGM played according to the item selected in the combo box
			menuItemBlueDanube.setEnabled(true);
			menuItemCourage.setEnabled(true);
			menuItemLuster.setEnabled(true);
			menuItemTenYears.setEnabled(true);
			menuItemTitanic.setEnabled(false);
			game.requestFocus();
			break;
		
		default:
			JOptionPane.showMessageDialog(this, "Error! Something wrong during the process of responding to the change of the item selected in the combo box for selecting BGM.\n(Unknown error)", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
		} // end switch-case
	} // end method itemStateChanged
} // end class MainFrame