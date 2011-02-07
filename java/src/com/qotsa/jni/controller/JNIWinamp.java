/*
 * JNIWinamp.java
 *
 * Created on 23 de Abril de 2007, 20:41
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.qotsa.jni.controller;

import java.io.IOException;

/**
 *
 * @author Francisco Guimarães
 */
final class JNIWinamp {
    
    static { 
            System.loadLibrary("wpcom");
    }

    /**
     * Verify if Winamp is started
     * and if not started, starts it
     * @return True - if successful run Winamp
     *         False - if not successful run Winamp
     */

    protected static native boolean run() throws UnsatisfiedLinkError;                

    /*
     * Exit Winamp
     * @return True - if successful exit
     *         False - if not successful exit
     */
    protected static native boolean exit() throws UnsatisfiedLinkError;

    /**
     *  Play Winamp.
     *
     */
    protected static native boolean play() throws UnsatisfiedLinkError;

    /**
     *  Stop Winamp.
     *
     */
    protected static native boolean stop() throws UnsatisfiedLinkError;

    /**
     *  Resume Winamp.
     *
     */
    protected static native boolean resume() throws UnsatisfiedLinkError;

    /**
     *  Pause Winamp.
     *
     */
    protected static native boolean pause() throws UnsatisfiedLinkError;

    /** 
     *  Go to Previous Track.
     *
     */
    protected static native boolean previousTrack() throws UnsatisfiedLinkError;

    /**
     *  Go to Next Track.
     *
     */
    protected static native boolean nextTrack() throws UnsatisfiedLinkError;

    /**
     *  Fowards 5 seconds on the current song.
     *
     */
    protected static native boolean fwd5Secs() throws UnsatisfiedLinkError;

    /**
     *  Rewinds 5 seconds on the current song.
     *
     */
    protected static native boolean rew5Secs() throws UnsatisfiedLinkError;

    /**
     *  Increase Volume.
     *
     */
    protected static native boolean increaseVolume() throws UnsatisfiedLinkError;

    /**
     *  Decrease Volume.
     *
     */
    protected static native boolean decreaseVolume() throws UnsatisfiedLinkError;

    /**
     *  Increase Volume.
     * @param percent Percent to Increase.
     */
    protected static native boolean increaseVolumePercent(int percent) throws UnsatisfiedLinkError;

    /**
     *  Decrease Volume.
     * @param percent Percent to Decrease.
     */
    protected static native boolean decreaseVolumePercent(int percent) throws UnsatisfiedLinkError;

    /**
     *  Adjust Volume
     * @param pos Position to Set Volume between 0 and 99.
     */
    protected static native boolean setVolume(int pos) throws UnsatisfiedLinkError;

    /**
     * Get Volume.
     * @return volume.
     */
    protected static native int getVolume() throws UnsatisfiedLinkError;

    /**
     *  Go to a Specified Position in the List.
     * @param pos Position.
     */
    protected static native boolean setPlaylistPosition(int pos) throws UnsatisfiedLinkError;

    /**
     *  Clear List.
     *
     */
    protected static native boolean clearPlayList() throws UnsatisfiedLinkError;

    /**
     *  Refresh List´s Cache.
     *
     */
    protected static native boolean refreshPlayListCache() throws UnsatisfiedLinkError;

    /**
     * Return the PlayListLength.
     * @return List Length.
     */
    protected static native int getPlayListLength() throws UnsatisfiedLinkError;

    /**
     * Write a Playlist in <winampdir>\\Winamp.m3u.
     * @return List Position.
     */
    protected static native int writePlayListToFile() throws UnsatisfiedLinkError;

    /**
     *  Verify if Shuffle is On.
     * @return True - On  throws UnsatisfiedLinkError; False - Off.
     */
    protected static native int isShuffleStatusOn() throws UnsatisfiedLinkError;

    /**
     *  Verify if Repeat is On.
     * @return True - On  throws UnsatisfiedLinkError; False - Off.
     */
    protected static native int isRepeatStatusOn() throws UnsatisfiedLinkError;

    /**
     *  Turn on Repeat.
     * @param True - Turn on Repeat  throws UnsatisfiedLinkError; False - Turn off Repeat.
     */
    protected static native boolean setRepeatStatusOn(boolean mode) throws UnsatisfiedLinkError;

    /**
     *  Turn on Shuffle.
     * @param True - Turn on Shuffle  throws UnsatisfiedLinkError; False - Turn off Shuffle.
     */
    protected static native boolean setShuffleStatusOn(boolean mode) throws UnsatisfiedLinkError;

    /**
     * Append a File in the List
     * @param filename FileName to Append.
     */
    protected static native boolean appendToPlayList(String filename) throws UnsatisfiedLinkError;

    /**
     * Get Winamp Status.
     * @return  STOPPED - Stop 
     *          PLAYING - play 
     *          PAUSED  - Paused
     */
    protected static native int getStatus() throws UnsatisfiedLinkError;

    /**
     * Get Current List Pos.
     * @return Current List Position.
     */
    protected static native int getListPos() throws UnsatisfiedLinkError;

    /**
     * Get Current Track Title
     * @return Current Track Title
     */
    protected static native String getTitle() throws UnsatisfiedLinkError;

    /**
     * Get Track FileName in List´s index.
     * @param index Track Index in the Current PlayList
     * @return Current Track FileName
     */
    protected static native String getFileNameInList(int index) throws UnsatisfiedLinkError;     

    /**
     * Get Song Time
     * @param mode CURRENTTIME - Currently Time in Milliseconds
     *             TIMELENGHT  - Song Time Length in seconds
     * @return Song Time in Seconds
     */
    protected static native int getTime(int mode) throws UnsatisfiedLinkError;
    
    /**
     * Get File Name Playing In Winamp.
     * 
     * @return Current File Name.
     */
    protected static native String getFileNamePlaying() throws UnsatisfiedLinkError;
        
}

