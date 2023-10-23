package music;

import java.util.*;

/**
 * This class represents a library of song playlists.
 *
 * An ArrayList of Playlist objects represents the various playlists 
 * within one's library.
 * 
 * @author Jeremy Hui
 * @author Vian Miranda
 */
public class PlaylistLibrary {

    private ArrayList<Playlist> songLibrary; // contains various playlists

    /**
     * DO NOT EDIT!
     * Constructor for Library.
     * 
     * @param songLibrary passes in ArrayList of playlists
     */
    public PlaylistLibrary(ArrayList<Playlist> songLibrary) {
        this.songLibrary = songLibrary;
    }

    /**
     * DO NOT EDIT!
     * Default constructor for an empty library. 
     */
    public PlaylistLibrary() {
        this(null);
    }

    /**
     * This method reads the songs from an input csv file, and creates a 
     * playlist from it.
     * Each song is on a different line.
     * 
     * 1. Open the file using StdIn.setFile(filename);
     * 
     * 2. Declare a reference that will refer to the last song of the circular linked list.
     * 
     * 3. While there are still lines in the input file:
     *      1. read a song from the file
     *      2. create an object of type Song with the song information
     *      3. Create a SongNode object that holds the Song object from step 3.2.
     *      4. insert the Song object at the END of the circular linked list (use the reference from step 2)
     *      5. increase the count of the number of songs
     * 
     * 4. Create a Playlist object with the reference from step (2) and the number of songs in the playlist
     * 
     * 5. Return the Playlist object
     * 
     * Each line of the input file has the following format:
     *      songName,artist,year,popularity,link
     * 
     * To read a line, use StdIn.readline(), then use .split(",") to extract 
     * fields from the line.
     * 
     * If the playlist is empty, return a Playlist object with null for its last, 
     * and 0 for its size.
     * 
     * The input file has Songs in decreasing popularity order.
     * 
     * DO NOT implement a sorting method, PRACTICE add to end.
     * 
     * @param filename the playlist information input file
     * @return a Playlist object, which contains a reference to the LAST song 
     * in the ciruclar linkedlist playlist and the size of the playlist.
     */
    public Playlist createPlaylist(String filename) {

        // WRITE YOUR CODE HERE
        Playlist playlist = new Playlist(); //create a new playlist object
        StdIn.setFile(filename); //open file
        SongNode lastSong = null; //last song does not exist yet
        int count = 0; //no songs in playlist yet
        while (StdIn.hasNextLine()) { //while file has lines
            String[] songInfo = StdIn.readLine().split(","); //split csv
            Song newsong = new Song(songInfo[0], songInfo[1], Integer.parseInt(songInfo[2]), Integer.parseInt(songInfo[3])); //parse data
            SongNode newnode = new SongNode(newsong, null); //create new node
            if (lastSong == null) { //if no songs in playlist
                lastSong = newnode; //new node is last song
                lastSong.setNext(lastSong); //last song points to itself
            } else {
                newnode.setNext(lastSong.getNext()); //new node points to (last.getnext) first song
                lastSong.setNext(newnode); //add new node to end of playlist
                lastSong = newnode; //new node is last song
            }
            count++; //increase song count
        }
        playlist = new Playlist(lastSong, count);


        return playlist; // update this line with your returning Playlist
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you. 
     * 
     * Adds a new playlist into the song library at a certain index.
     * 
     * 1. Calls createPlayList() with a file containing song information.
     * 2. Adds the new playlist created by createPlayList() into the songLibrary.
     * 
     * Note: initialize the songLibrary if it is null
     * 
     * @param filename the playlist information input file
     * @param playlistIndex the index of the location where the playlist will 
     * be added 
     */
    public void addPlaylist(String filename, int playlistIndex) {
        
        /* DO NOT UPDATE THIS METHOD */

        if (songLibrary == null) {
            songLibrary = new ArrayList<Playlist>();
        }
        if (playlistIndex >= songLibrary.size()) {
            songLibrary.add(createPlaylist(filename));
        } else {
            songLibrary.add(playlistIndex, createPlaylist(filename));
        }        
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * This method is already implemented for you.
     * 
     * It takes a playlistIndex, and removes the playlist located at that index.
     * 
     * @param playlistIndex the index of the playlist to remove
     * @return true if the playlist has been deleted
     */
    public boolean removePlaylist(int playlistIndex) {
        /* DO NOT UPDATE THIS METHOD */

        if ( songLibrary == null || playlistIndex >= songLibrary.size() ) {
            return false;
        }

        songLibrary.remove(playlistIndex);
            
        return true;
    }
    
    /** 
     * 
     * Adds the playlists from many files into the songLibrary
     * 
     * 1. Initialize the songLibrary
     * 
     * 2. For each of the filenames
     *       add the playlist into songLibrary
     * 
     * The playlist will have the same index in songLibrary as it has in
     * the filenames array. For example if the playlist is being created
     * from the filename[i] it will be added to songLibrary[i]. 
     * Use the addPlaylist() method. 
     * 
     * @param filenames an array of the filenames of playlists that should be 
     * added to the library
     */
    public void addAllPlaylists(String[] filenames) {
        
        // WRITE YOUR CODE HERE
        songLibrary = new ArrayList<Playlist>(); //initialize songLibrary
        int index = 0; //index of playlist
        for (String name : filenames) { //for each filename
            addPlaylist(name, index); //add playlist to songLibrary
            index++; //increase index
        }
    }

    /**
     * This method adds a song to a specified playlist at a given position.
     * 
     * The first node of the circular linked list is at position 1, the 
     * second node is at position 2 and so forth.
     * 
     * Return true if the song can be added at the given position within the 
     * specified playlist (and thus has been added to the playlist), false 
     * otherwise (and the song will not be added). 
     * 
     * Increment the size of the playlist if the song has been successfully
     * added to the playlist.
     * 
     * @param playlistIndex the index where the playlist will be added
     * @param position the position inthe playlist to which the song 
     * is to be added 
     * @param song the song to add
     * @return true if the song can be added and therefore has been added, 
     * false otherwise. 
     */
    public boolean insertSong(int playlistIndex, int position, Song song) {
        // WRITE YOUR CODE HERE
        int playlistSize = songLibrary.get(playlistIndex).getSize(); //get size of playlist
        if (this.songLibrary == null)
        {
            this.songLibrary = new ArrayList<Playlist>(); //initialize songLibrary
        }
        if (playlistIndex >= songLibrary.size()) { //if playlist does not exist
            return false; //return false, not added
        }
        if (position > playlistSize + 1 || position < 1) { //if position is invalid (greater than size or less than 1)
            return false; //return false, not added
        }
        if (position == 1 && playlistSize == 0) //if position is 1 and playlist is empty
        {
            SongNode newnode = new SongNode(song, null); //create new node
            newnode.setNext(newnode); //make song point to itself
            songLibrary.get(playlistIndex).setLast(newnode); //mark new node as last
            songLibrary.get(playlistIndex).setSize(playlistSize + 1); //increase playlist size
            return true; //return true, added
        }
        if (position == playlistSize + 1) //if position is one past current end of playlist 
        {
            SongNode newlast = new SongNode(song, songLibrary.get(playlistIndex).getLast().getNext()); //create new node with song, make it last song by making it's next the first node
            songLibrary.get(playlistIndex).getLast().setNext(newlast); //connect og last and new last
            songLibrary.get(playlistIndex).setLast(newlast); //mark new last as last
            songLibrary.get(playlistIndex).setSize(playlistSize + 1); //increase playlist size
            return true; //return true, added
        }
        if (position == 1)
        {
            SongNode newnode = new SongNode(song, songLibrary.get(playlistIndex).getLast().getNext()); //create new node with next as og first
            songLibrary.get(playlistIndex).getLast().setNext(newnode); //add song to beginning of playlist by connecting to last node
            songLibrary.get(playlistIndex).setSize(playlistSize + 1); //increase playlist size
            return true; //return true, added
        }
        SongNode newnode = new SongNode(song, null); //create new node
        int count = 1; //start at first node
        SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(); //pointer to first node
        while (count != position - 1) { //while not at position's previous node
            ptr = ptr.getNext(); //move pointer
            count++; //increase count
        }
        newnode.setNext(ptr.getNext()); //new node points to node at position
        ptr.setNext(newnode); //node at position - 1 points to new node
        songLibrary.get(playlistIndex).setSize(playlistSize + 1); //increase playlist size
        return true; // return true, added
    }

    /**
     * This method removes a song at a specified playlist, if the song exists. 
     *
     * Use the .equals() method of the Song class to check if an element of 
     * the circular linkedlist matches the specified song.
     * 
     * Return true if the song is found in the playlist (and thus has been 
     * removed), false otherwise (and thus nothing is removed). 
     * 
     * Decrease the playlist size by one if the song has been successfully
     * removed from the playlist.
     * 
     * @param playlistIndex the playlist index within the songLibrary where 
     * the song is to be added.
     * @param song the song to remove.
     * @return true if the song is present in the playlist and therefore has 
     * been removed, false otherwise.
     */
    public boolean removeSong(int playlistIndex, Song song) {
        // WRITE YOUR CODE HERE
        int playlistSize = songLibrary.get(playlistIndex).getSize(); //get size of playlist
        if (playlistIndex >= songLibrary.size()) { //if playlist does not exist
            return false; //return false, not removed
        }
        if (playlistSize == 0) { //if playlist is empty
            return false; //return false, not removed
        }
        if (playlistSize == 1) //if playlist has one song
        {
            if (songLibrary.get(playlistIndex).getLast().getSong().equals(song)) //if song is in playlist
            {
                songLibrary.get(playlistIndex).setSize(playlistSize - 1); //decrease playlist size
                songLibrary.get(playlistIndex).setLast(null); //remove song
                return true; //return true, removed
            }
            return false; //return false, not removed
        }
        SongNode ptr = songLibrary.get(playlistIndex).getLast(); //pointer to last node
        int count = 1; //start at first node
        while (count != playlistSize && !ptr.getNext().getSong().equals(song)) { //while song not found
            ptr = ptr.getNext(); //move pointer
            count++; //increase count
        }
        if (ptr.getNext().getSong().equals(song)) { //if song is found
            if (ptr.getNext().getSong().equals(songLibrary.get(playlistIndex).getLast().getSong()))
            {
                songLibrary.get(playlistIndex).setLast(ptr); //update last node
            }
            ptr.setNext(ptr.getNext().getNext()); //remove song
            songLibrary.get(playlistIndex).setSize(playlistSize - 1); //decrease playlist size
            return true; //return true, removed
        }
        return false; // return false, not removed
    }

    /**
     * This method reverses the playlist located at playlistIndex
     * 
     * Each node in the circular linked list will point to the element that 
     * came before it.
     * 
     * After the list is reversed, the playlist located at playlistIndex will 
     * reference the first SongNode in the original playlist (new last).
     * 
     * @param playlistIndex the playlist to reverse
     */
    public void reversePlaylist(int playlistIndex) {
        // WRITE YOUR CODE HERE
        int playlistSize = songLibrary.get(playlistIndex).getSize(); //get size of playlist
        if (playlistIndex >= songLibrary.size()) { //if playlist does not exist
            return; //return, not reversed
        }
        if (playlistSize == 0) { //if playlist is empty
            return; //return, not reversed
        }
        //use stack to reverse order
        Stack<SongNode> stack = new Stack<SongNode>(); //create stack
        SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(); //pointer to first node
        for (int i = 0; i < playlistSize; i++) { //for each node in playlist
            stack.push(ptr); //push node to stack
            ptr = ptr.getNext(); //move pointer
        }
        //pop nodes from stack to reverse order and connect nodes
        SongNode newfirst = stack.pop(); //pop node to be new first
        ptr = newfirst; //pointer we will use to find the last node
        while (!stack.isEmpty()) { //while stack is not empty
            ptr.setNext(stack.pop()); //set next node to popped node
            ptr = ptr.getNext(); //move pointer
        }
        ptr.setNext(newfirst); //connect last node to first
        songLibrary.get(playlistIndex).setLast(ptr); //save reordered playlist, identified by last node (ptr)
    }

    /**
     * This method merges two playlists.
     * 
     * Both playlists have songs in decreasing popularity order. The resulting 
     * playlist will also be in decreasing popularity order.
     * 
     * You may assume both playlists are already in decreasing popularity 
     * order. If the songs have the same popularity, add the song from the 
     * playlist with the lower playlistIndex first.
     * 
     * After the lists have been merged:
     *  - store the merged playlist at the lower playlistIndex
     *  - remove playlist at the higher playlistIndex 
     * 
     * 
     * @param playlistIndex1 the first playlist to merge into one playlist
     * @param playlistIndex2 the second playlist to merge into one playlist
     */
    public void mergePlaylists(int playlistIndex1, int playlistIndex2) {
      
        // WRITE YOUR CODE HERE
        int minplaylist = Math.min(playlistIndex1, playlistIndex2);
        int maxplaylist = Math.max(playlistIndex1, playlistIndex2);
        int mergesize = songLibrary.get(minplaylist).getSize() + songLibrary.get(maxplaylist).getSize(); //get size of merged playlist
        SongNode last = null;
        while (songLibrary.get(minplaylist).getSize() != 0 && songLibrary.get(maxplaylist).getSize() != 0) // while 
        {
            SongNode head1 = songLibrary.get(minplaylist).getLast().getNext(); //pointer to first node of playlist 1
            SongNode head2 = songLibrary.get(maxplaylist).getLast().getNext(); //pointer to first node of playlist 2
            if (head1.getSong().getPopularity() >= head2.getSong().getPopularity())
            {
                if (last == null)
                {
                    last = new SongNode(head1.getSong(), null); //create new node with song from playlist 1
                    last.setNext(last); //make song point to itself
                }
                else
                {
                    SongNode newlast = new SongNode(head1.getSong(), last.getNext()); //create new node with song from playlist 1, make it last song by making it's next the first node
                    last.setNext(newlast); //append song to merged playlist
                    last = newlast; //add song to end of merged playlist
                }
                removeSong(minplaylist, head1.getSong()); //remove song from playlist 1
            }
            else
            {
                if (last == null)
                {
                    last = new SongNode(head2.getSong(), null); //create new node with song from playlist 2
                    last.setNext(last); //make song point to itself
                }
                else
                {
                    SongNode newlast = new SongNode(head2.getSong(), last.getNext()); //create new node with song from playlist 2, make it last song by making it's next the first node
                    last.setNext(newlast); //append song to merged playlist
                    last = newlast; //add song to end of merged playlist
                }
                removeSong(maxplaylist, head2.getSong()); //remove song from playlist 2
            }
        }
        while (songLibrary.get(minplaylist).getSize() != 0)
        {
            SongNode head1 = songLibrary.get(minplaylist).getLast().getNext(); //pointer to first node of playlist 1
            if (last == null)
            {
                last = new SongNode(head1.getSong(), null); //create new node with song from playlist 1
                last.setNext(last); //make song point to itself
            }
            else
            {
                SongNode newlast = new SongNode(head1.getSong(), last.getNext()); //create new node with song from playlist 1, make it last song by making it's next the first node
                last.setNext(newlast); //append song to merged playlist
                last = newlast; //add song to end of merged playlist
            }
            removeSong(minplaylist, head1.getSong()); //remove song from playlist 1
        }
        while (songLibrary.get(maxplaylist).getSize() != 0)
        {
            SongNode head2 = songLibrary.get(maxplaylist).getLast().getNext(); //pointer to first node of playlist 2
            if (last == null)
            {
                last = new SongNode(head2.getSong(), null); //create new node with song from playlist 2
                last.setNext(last); //make song point to itself
            }
            else
            {
                SongNode newlast = new SongNode(head2.getSong(), last.getNext()); //create new node with song from playlist 2, make it last song by making it's next the first node
                last.setNext(newlast); //append song to merged playlist
                last = newlast; //add song to end of merged playlist
            }
            removeSong(maxplaylist, head2.getSong()); //remove song from playlist 2
        }
        songLibrary.set(minplaylist, new Playlist(last, mergesize)); //update playlist
        removePlaylist(maxplaylist); //remove playlist
    }

    /**
     * This method shuffles a specified playlist using the following procedure:
     * 
     * 1. Create a new playlist to store the shuffled playlist in.
     * 
     * 2. While the size of the original playlist is not 0, randomly generate a number 
     * using StdRandom.uniformInt(1, size+1). Size contains the current number
     * of items in the original playlist.
     * 
     * 3. Remove the corresponding node from the original playlist and insert 
     * it into the END of the new playlist (1 being the first node, 2 being the 
     * second, etc). 
     * 
     * 4. Update the old playlist with the new shuffled playlist.
     *    
     * @param index the playlist to shuffle in songLibrary
     */
    public void shufflePlaylist(int playlistIndex) {
        // WRITE YOUR CODE HERE
        Playlist shuffled = new Playlist(); //create new playlist
        while (songLibrary.get(playlistIndex).getSize() != 0)
        {
            int random = StdRandom.uniformInt(songLibrary.get(playlistIndex).getSize() + 1)+1; //generate random number
            SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(); //pointer to first node
            for (int i = 1; i < random; i++) { //for each node in playlist
                ptr = ptr.getNext(); //move pointer
            }
            if (shuffled.getSize() == 0)
            {
                shuffled.setLast(new SongNode(ptr.getSong(), null)); //add song to end of shuffled playlist
                shuffled.getLast().setNext(shuffled.getLast()); //make song point to itself
            }
            else
            {
                SongNode newlast = new SongNode(ptr.getSong(), shuffled.getLast().getNext()); //create new node with random song, make it last song by making it's next the first node
                shuffled.getLast().setNext(newlast); //append song to shuffled playlist
                shuffled.setLast(newlast); //add song to end of shuffled playlist
            }
            shuffled.setSize(shuffled.getSize() + 1); //increase size of shuffled playlist
            removeSong(playlistIndex, ptr.getSong()); //remove song from original playlist
        }
        songLibrary.set(playlistIndex, shuffled); //update original playlist
    }

    /**
     * This method sorts a specified playlist using linearithmic sort.
     * 
     * Set the playlist located at the corresponding playlistIndex
     * in decreasing popularity index order.
     * 
     * This method should  use a sort that has O(nlogn), such as with merge sort.
     * 
     * @param playlistIndex the playlist to shuffle
     */
    public void sortPlaylist ( int playlistIndex ) {

        // WRITE YOUR CODE HERE
        
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * Plays playlist by index; can use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     * @param repeats number of times to repeat playlist
     * @throws InterruptedException
     */
    public void playPlaylist(int playlistIndex, int repeats) {
        /* DO NOT UPDATE THIS METHOD */

        final String NO_SONG_MSG = " has no link to a song! Playing next...";
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("Nothing to play.");
            return;
        }

        SongNode ptr = songLibrary.get(playlistIndex).getLast().getNext(), first = ptr;

        do {
            StdOut.print("\r" + ptr.getSong().toString());
            if (ptr.getSong().getLink() != null) {
                StdAudio.play(ptr.getSong().getLink());
                for (int ii = 0; ii < ptr.getSong().toString().length(); ii++)
                    StdOut.print("\b \b");
            }
            else {
                StdOut.print(NO_SONG_MSG);
                try {
                    Thread.sleep(2000);
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
                for (int ii = 0; ii < NO_SONG_MSG.length(); ii++)
                    StdOut.print("\b \b");
            }

            ptr = ptr.getNext();
            if (ptr == first) repeats--;
        } while (ptr != first || repeats > 0);
    }

    /**
     * ****DO NOT**** UPDATE THIS METHOD
     * Prints playlist by index; can use this method to debug.
     * 
     * @param playlistIndex the playlist to print
     */
    public void printPlaylist(int playlistIndex) {
        StdOut.printf("%nPlaylist at index %d (%d song(s)):%n", playlistIndex, songLibrary.get(playlistIndex).getSize());
        if (songLibrary.get(playlistIndex).getLast() == null) {
            StdOut.println("EMPTY");
            return;
        }
        SongNode ptr;
        for (ptr = songLibrary.get(playlistIndex).getLast().getNext(); ptr != songLibrary.get(playlistIndex).getLast(); ptr = ptr.getNext() ) {
            StdOut.print(ptr.getSong().toString() + " -> ");
        }
        if (ptr == songLibrary.get(playlistIndex).getLast()) {
            StdOut.print(songLibrary.get(playlistIndex).getLast().getSong().toString() + " - POINTS TO FRONT");
        }
        StdOut.println();
    }

    public void printLibrary() {
        if (songLibrary.size() == 0) {
            StdOut.println("\nYour library is empty!");
        } else {
                for (int ii = 0; ii < songLibrary.size(); ii++) {
                printPlaylist(ii);
            }
        }
    }

    /*
     * Used to get and set objects.
     * DO NOT edit.
     */
     public ArrayList<Playlist> getPlaylists() { return songLibrary; }
     public void setPlaylists(ArrayList<Playlist> p) { songLibrary = p; }
}
