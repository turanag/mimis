package com.dt.iTunesController;
import com.jacob.com.Dispatch;

/**
 * Defines a source, playlist or track.
 * 
 * An ITObject uniquely identifies a source, playlist, or track in iTunes using
 * four separate IDs. These are runtime IDs, they are only valid while the
 * current instance of iTunes is running.
 * 
 * As of iTunes 7.7, you can also identify an ITObject using a 64-bit persistent
 * ID, which is valid across multiple invocations of iTunes.
 * 
 * The main use of the ITObject interface is to allow clients to track iTunes
 * database changes using
 * <code>iTunesEventsInterface.onDatabaseChangedEvent()</code>.
 * 
 * You can retrieve an ITObject with a specified runtime ID using
 * <code>iTunes.getITObjectByID()</code>.
 * 
 * An ITObject will always have a valid, non-zero source ID.
 * 
 * An ITObject corresponding to a playlist or track will always have a valid
 * playlist ID. The playlist ID will be zero for a source.
 * 
 * An ITObject corresponding to a track will always have a valid track and
 * track database ID. These IDs will be zero for a source or playlist.
 * 
 * A track ID is unique within the track's playlist. A track database ID is
 * unique across all playlists. For example, if the same music file is in two
 * different playlists, each of the tracks could have different track IDs, but
 * they will have the same track database ID.
 * 
 * An ITObject also has a 64-bit persistent ID which can be used to identify
 * the ITObject across multiple invocations of iTunes.
 * 
 * @author <a href="mailto:steve@dot-totally.co.uk">Steve Eyre</a>
 * @version 0.2
 */
public class ITObject {
    
    protected Dispatch object;
    
    public ITObject(Dispatch d) {
        object = d;
    }
    
    /**
     * Returns the JACOB Dispatch object for this object.
     * @return Returns the JACOB Dispatch object for this object.
     */
    public Dispatch fetchDispatch() {
        return object;
    }
    
    /**
     * Set the name of the object.
     * @param name The new name of the object.
     */
    public void setName (String name) {
        Dispatch.put(object, "Name", name);
    }
    
    /**
     * Returns the name of the object.
     * @return Returns the name of the object.
     */
    public String getName() {
        return Dispatch.get(object, "Name").getString();
    }
    
    /**
     * Returns the index of the object in internal application order.
     * @return The index of the object in internal application order.
     */
    public int getIndex() {
        return Dispatch.get(object, "Index").getInt();
    }
    
    /**
     * Returns the ID that identifies the source.
     * @return Returns the ID that identifies the source.
     */
    public int getSourceID() {
        return Dispatch.get(object, "SourceID").getInt();
    }
    
    /**
     * Returns the ID that identifies the playlist.
     * @return Returns the ID that identifies the playlist.
     */
    public int getPlaylistID() {
        return Dispatch.get(object, "PlaylistID").getInt();
    }
    
    /**
     * Returns the ID that identifies the track within the playlist.
     * @return Returns the ID that identifies the track within the playlist.
     */
    public int getTrackID() {
        return Dispatch.get(object, "TrackID").getInt();
    }
    
    /**
     * Returns the ID that identifies the track, independent of its playlist.
     * @return Returns the ID that identifies the track, independent of its playlist.
     */
    public int getTrackDatabaseID() {
        return Dispatch.get(object, "TrackDatabaseID").getInt();
    }

}
