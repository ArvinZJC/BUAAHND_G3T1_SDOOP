/*
 * @Description: java class file for a BGM manager
 * @Version: 4.0
 * @Author: Arvin Zhao
 * @Date: 2018-12-08 13:46:47
 * @Last Editors: Arvin Zhao
 * @LastEditTime: 2018-12-16 00:14:03
 */

package Outcome_Sokoban;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

/**
 * Class BgmManager that acts as a BGM manager.
 */
public class BgmManager
{
	private int index = 0; // the index of a MIDI file for playin
	private String[] midiFile = {"BlueDanube.mid", "Courage.mid", "Luster.mid", "TenYears.mid", "Titanic.mid"}; // the BGM list
	private Sequence midiData; // a data structure used for containing musical information of MIDI files (BGM)
	private Sequencer midiPlayer; // a sequencer for playing back the MIDI sequence for BGM
	
	/**
	 * Load and play BGM.
	 */
	public void playBgm()
	{
		try
		{
			midiData = MidiSystem.getSequence(new File(getClass().getResource("").getPath() + "/bgm/" + midiFile[index])); // get musical information of BGM
            midiPlayer = MidiSystem.getSequencer(); // obtain the default sequencer for playing back the MIDI sequence for BGM
            midiPlayer.open();
            midiPlayer.setSequence(midiData);
			midiPlayer.start();
			midiPlayer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY); // loop until the player selects another BGM
        }
        catch (Exception e)
		{
        	JOptionPane.showMessageDialog(null, "Error! Something wrong during the process of loading and playing BGM.\n(" + e.toString() + ")", "Sokoban - Zhao Jichen", JOptionPane.ERROR_MESSAGE);
        } // end try...catch
	} // end method playBgm
	
	/**
	 * Stop BGM.
	 */
	public void stopBgm()
	{
		midiPlayer.stop();
		midiPlayer.close();
	} // end method stopBgm
	
	/**
	 * Check if BGM is played.
	 * @return the boolean value true if BGM is played, or false if it is stopped
	 */
	public boolean getPlayingStatus()
	{
		return midiPlayer.isRunning();
	} // end method getPlayingStatus
	
	/**
	 * Set the index of a MIDI file for playing.
	 * @param index the index of a MIDI file for playing
	 */
	public void setMidiFileIndex(int index)
	{
		this.index = index;
	} // end method setMidiFileIndex
} // end class BgmManager