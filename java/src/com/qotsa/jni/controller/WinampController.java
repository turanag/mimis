/*
 * WinampController.java
 *
 * Created on 9 de Outubro de 2007, 14:06
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

/**
 * Package containing the Controller Class to communicate with Winamp.
 *
 * @author Francisco Guimarães
 */
package com.qotsa.jni.controller;

import com.qotsa.exception.InvalidHandle;
import com.qotsa.exception.InvalidParameter;
import java.io.File;

/**
 * This class is a wrapper to call JNI functions
 * to send Message to Winamp Window.
 *
 * @author Francisco Guimarães
 * 
 *
 */
public class WinampController {
    
    /**
     * Constant used as return in getTime()
     * Value = -1
     */
    public static final int ISNOTPLAYING   = -1;    
    
    /**
     * Constant used as parameter in getTime() method
     * Value = 0
     */
    public static final int CURRENTTIME    = 0;
    
    /**
     * Constant used as parameter in getTime() method
     * Value = 1
     */
    public static final int TIMELENGTH     = 1;
    
    /**
     * Constant used as return in getStatus() method
     * Value = 0
     */
    public static final int STOPPED     = 0;
    
    /**
     * Constant used as return in getStatus() method
     * Value = 1
     */
    public static final int PLAYING     = 1;
    
    /**
     * Constant used as return in getStatus() method
     * Value = 3
     */
    public static final int PAUSED      = 3;
    
    
    /**
     * Verify if Winamp is started
     * and if not started, starts it.
     * 
     * @throws java.lang.Exception When the key HKEY_LOCAL_MACHINE\Software\Clients\Media\Winamp\shell\open\command
     * is not found in the register. This key is used to find Winamp Directory installation to execute it.
     */

    public static void run() throws Exception{
        
        if (!JNIWinamp.run())
            throw new Exception("Unable to run Winamp. Verify if it is properly installed");
        
    }

    /**
     * Exit Winamp.
     * 
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running)
     */
    public static void exit() throws InvalidHandle {
        
        if (!JNIWinamp.exit())
            throw new InvalidHandle();
        
            
    }

    /**
     *  Play current file in Winamp.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running)
     */
    public static void play() throws InvalidHandle {
        
        if (!JNIWinamp.play())
            throw new InvalidHandle();
        
    }

    /**
     *  Stop current file in Winamp.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void stop() throws InvalidHandle {
        
        if (!JNIWinamp.stop())
            throw new InvalidHandle();
        
    }

    /**
     *  Resume current file in Winamp.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void resume() throws InvalidHandle {
        
        if (!JNIWinamp.resume())
            throw new InvalidHandle();
        
    }

    /**
     *  Pause Winamp.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void pause() throws InvalidHandle {
        
        if (!JNIWinamp.pause())
            throw new InvalidHandle();
        
    }

    /**
     * 
     *  Go to Previous Track in the play list.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void previousTrack() throws InvalidHandle {
        
        if (!JNIWinamp.previousTrack())
            throw new InvalidHandle();
        
    }

    /**
     *  Go to Next Track in the play list.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void nextTrack() throws InvalidHandle {
        
        if (!JNIWinamp.nextTrack())
            throw new InvalidHandle();
        
    }

    /**
     *  Fowards 5 seconds on the current song.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void fwd5Secs() throws InvalidHandle {
        
        if (!JNIWinamp.fwd5Secs())
            throw new InvalidHandle();
        
    }

    /**
     *  Rewinds 5 seconds on the current song.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void rew5Secs() throws InvalidHandle {
        
        if (!JNIWinamp.rew5Secs())
            throw new InvalidHandle();
        
    }

    /**
     *  Increase Volume a little bit.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void increaseVolume() throws InvalidHandle {
        
        if (!JNIWinamp.increaseVolume())
            throw new InvalidHandle();
        
    }

    /**
     *  Decrease Volume a little bit.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void decreaseVolume() throws InvalidHandle {
        
        if (!JNIWinamp.decreaseVolume())
            throw new InvalidHandle();
        
    }

    /**
     *  Increase Volume.
     * 
     * @param percent Percent to Increase Volume.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If percent not between 0 and 100
     */
    public static void increaseVolumePercent (int percent) throws InvalidHandle, InvalidParameter {
        
        if ( (percent < 0) || (percent > 100) ) 
            throw new InvalidParameter("percent´s value must be between 0 and 100");
        
        if (!JNIWinamp.increaseVolumePercent(percent))
            throw new InvalidHandle();
        
    }

    /**
     *  Decrease Volume.
     * 
     * @param percent Percent to Decrease Volume.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If percent not between 0 and 100
     */
    public static void decreaseVolumePercent(int percent) throws InvalidHandle, InvalidParameter {
        
        if ( (percent < 0) || (percent > 100) ) 
            throw new InvalidParameter("percent´s value must be between 0 and 100");
        if (!JNIWinamp.decreaseVolumePercent(percent))
            throw new InvalidHandle();
        
    }

    /**
     *  Adjust Volume.
     *  Note that the pos must be between 0(0%) and 255 (100%).
     * 
     * @param pos Position to Set Volume.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If pos not between 0 and 255
     */
    public static void setVolume(int pos) throws InvalidHandle, InvalidParameter {
        
        if ( (pos < 0) || (pos > 255) )
            throw new InvalidParameter("pos value must be between 0 and 255");
        if (!JNIWinamp.setVolume(pos))
            throw new InvalidHandle();
        
    }

    /**
     * Get Volume.
     * @return The volume which is a number between 0 (0%) and 255(100%)
     * @throws com.qotsa.exception.InvalidHandle
     */
    public static int getVolume() throws InvalidHandle {
        int volume = JNIWinamp.getVolume();
        if (volume == -1)
            throw new InvalidHandle();
        return volume;
    }

    /**
     * Get Volume Percent.
     * @return Volume percent.
     * @throws com.qotsa.exception.InvalidHandle
     */
    public static int getVolumePercent() throws InvalidHandle {

        int volume = getVolume();
        int percent = (volume * 100) / 255;
        return percent;
    }

    /**
     *  Restarts Winamp.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws java.lang.Exception When failed to restart Winamp.
     */
    public static void restart() throws InvalidHandle, Exception {

        exit();
        run();

    }

    /**
     *  Go to a Specified Position in the List. This method doesn´t play.
     *  Just set list position. If you wanna play call play() after set position.
     *  Parameter pos is Zero-based index.
     * @param pos Position.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If pos is negative or greater than List Length.
     */
    public static void setPlaylistPosition(int pos) throws InvalidHandle, InvalidParameter {
        
        int listLength = getPlayListLength();
        if ( (pos < 0) || ( (pos + 1) > listLength) )
            throw new InvalidParameter("Position is invalid in the list.");
        if (!JNIWinamp.setPlaylistPosition(pos))
            throw new InvalidHandle();
        
    }

    /**
     *  Clear Winamp's internal playlist.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void clearPlayList() throws InvalidHandle {
        
        if (!JNIWinamp.clearPlayList())
            throw new InvalidHandle();
        
    }

    /**
     *  Flush the playlist cache buffer.
     *  Call this if you want it to go refetch titles for tracks in the list.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void refreshPlayListCache() throws InvalidHandle{
        
        if (!JNIWinamp.refreshPlayListCache())
            throw new InvalidHandle();
        
    }

    /**
     * Return the PlayListLength.
     * 
     * @return List Length.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static int getPlayListLength() throws InvalidHandle {
        
        int length = JNIWinamp.getPlayListLength();
        if (length == -1)
            throw new InvalidHandle();
        return length;
        
    }

    /**
     * Write a Playlist in winampDirInstallation\\Winamp.m3u.
     * This is default Winamp IPC Default. If you wanna change this,
     * just rename the file you´ve created.
     * 
     * @return Current List Position.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static int writePlayListToFile() throws InvalidHandle {
        
        int playListPos = JNIWinamp.writePlayListToFile();
        if (playListPos == -1)
            throw new InvalidHandle();
        return playListPos;
        
    }

    /**
     *  Verify if Shuffle is On.
     * 
     * @return True - On ; False - Off.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static boolean isShuffleStatusOn() throws InvalidHandle {
        
        int status = JNIWinamp.isShuffleStatusOn();
        if (status == -1)
            throw new InvalidHandle();
        return (status == 1 ? true : false);
        
    }

    /**
     *  Verify if Repeat is On.
     * 
     * @return True - On ; False - Off.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static boolean isRepeatStatusOn() throws InvalidHandle {
        
        int status = JNIWinamp.isRepeatStatusOn();
        if (status == -1)
            throw new InvalidHandle();
        return (status == 1 ? true : false);
        
    }

    /**
     *  Turn on Repeat.
     * 
     * @param mode True - Turn on Repeat ; False - Turn off Repeat.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void setRepeatStatusOn(boolean mode) throws InvalidHandle {
        
        if (!JNIWinamp.setRepeatStatusOn(mode))
            throw new InvalidHandle();
        
    }

    /**
     *  Turn on Shuffle.
     * 
     * @param mode True - Turn on Shuffle ; False - Turn off Shuffle.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static void setShuffleStatusOn(boolean mode) throws InvalidHandle {
        
        if (!JNIWinamp.setShuffleStatusOn(mode))
            throw new InvalidHandle();
        
    }

    /**
     * Append a File into the List.
     * 
     * @param filename FileName to Append in the list.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If the filename is an invalid path.
     */
    public static void appendToPlayList(String filename) throws InvalidHandle, InvalidParameter {
        
        File file = new File(filename);
        if (!file.exists())
            throw new InvalidParameter("File doesn´t exists.");
        if (!JNIWinamp.appendToPlayList(filename))
            throw new InvalidHandle();
                    
    }

    /**
     * Get Winamp Status.
     * 
     * @return STOPPED - Stop 
     *          PLAYING - play 
     *          PAUSED  - Paused
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static int getStatus() throws InvalidHandle {
        
        int status = JNIWinamp.getStatus();
        if (status == -1)
            throw new InvalidHandle();
        return status;        
        
    }

    /**
     * Get Current List Pos.
     * 
     * @return Current List Position.
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static int getListPos() throws InvalidHandle {
        
        int pos = JNIWinamp.getListPos();
        if (pos == -1)
            throw new InvalidHandle();
        return pos;
        
    }

    /**
     * Get Current Track Title.
     * 
     * @return Current Track Title
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     */
    public static String getTitle() throws InvalidHandle {
        
        String title = JNIWinamp.getTitle();
        if (title == null)
            throw new InvalidHandle();
        return title;
        
    }

    /**
     * Get Current Track FileName.
     * Parameter pos is Zero-based index. 
     *
     * @return Current Track FileName
     * @param pos Track Position in the Current PlayList
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter If pos is negative or greater than List Length
     */
    public static String getFileNameInList(int pos) throws InvalidHandle, InvalidParameter {
        
        int listLength = getPlayListLength();
        if ( (pos < 0) || (pos > listLength) )
            throw new InvalidParameter("Position is invalid in the list.");
        String filename = JNIWinamp.getFileNameInList(pos);
        if (filename == null)
            throw new InvalidHandle();
        return filename;
        
    }

    /**
     * Get Song Time.
     * 
     * @return Time Song (depends on Param mode) or 
     * ISNOTPLAYING if there is no info about time because it not starts to play.
     * @param mode CURRENTTIME - Currently Time in Milliseconds
     *             TIMELENGHT  - Song Time Length in seconds
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter if parameter is not CURRENTTIME or TIMELENGTH.
     */
    public static int getTime(int mode) throws InvalidHandle, InvalidParameter {
        
        if (mode != CURRENTTIME && mode != TIMELENGTH)
            throw new InvalidParameter();
        int time = JNIWinamp.getTime(mode);
        if (time == -2)
            throw new InvalidHandle();
        return time;
        
    }

    /**
     * Fowards n Tracks in Play List.
     * 
     * @param n Number of Tracks to Foward
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter if n is negative or Zero.
     */
    public static void fwdTracks(int n) throws InvalidParameter, InvalidHandle {

        if (n <= 0)
            throw new InvalidParameter("Value must be Positive");
        int pos = getListPos();
        int lengthList = getPlayListLength();
        int newPos = pos + n;
        if (newPos > lengthList ) {
            setPlaylistPosition(lengthList);
            play();
        }
        else {
            setPlaylistPosition(newPos);
            play();
        }

    }
    
    /**
     * Rewinds n Tracks in Play List.
     * 
     * @param n Number of Tracks to Rewind
     * @throws com.qotsa.exception.InvalidHandle When the Winamp Windows is not handle(in most case, it means that winamp is not running) 
     * @throws com.qotsa.exception.InvalidParameter if n is negative or Zero.
     */ 
    public static void rewTracks(int n) throws InvalidParameter, InvalidHandle {

        if (n <= 0)
            throw new InvalidParameter("Value must be Positive");
        int pos = getListPos();
        int lengthList = getPlayListLength();
        int newPos = pos - n;
        if (newPos < 0 ) {
            setPlaylistPosition(0);
            play();
        }
        else {
            setPlaylistPosition(newPos); 
            play();
        }  

    }
    
    /**
     * Get File Name Playing In Winamp.
     * 
     * @return Current File Name.
     */
    public static String getFileNamePlaying() throws InvalidHandle {
        
        String fileName = JNIWinamp.getFileNamePlaying();
        if (fileName == null)
            throw new InvalidHandle();
        return fileName;
        
    }
    
}
