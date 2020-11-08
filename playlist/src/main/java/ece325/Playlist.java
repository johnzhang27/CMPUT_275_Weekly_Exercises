package ece325;

import java.util.HashSet;
import java.util.Comparator;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Playlist} class
 * @author Yongquan Zhang 1515873
 */
// @SuppressWarnings("serial")

public class Playlist<E extends Song> extends java.util.ArrayList<E> {
    java.util.Iterator<E> itr = this.iterator();       // Generic Iterator; Use it whenever you need it!

    // TODO: Assignment 6 -- complete this Playlist class to pass the tests
    private String title;
    // Constructor. super() is the constructor for arraylist
    public Playlist(String title){
        super();
        this.title = title;
    }
    /** This method is for adding song object to an arraylist
     * @param song  {@code E extends Song} The Song object to be added
     * @return      {@code boolean} true or false
     */
    public boolean addtoPlist(E song) {
        int index = this.indexOf(song);
        if(song == null){
            return false;
        }
        else if(index == -1){
            return this.add(song);
        }
        return false;
    }
    /** This method is for fetching title information
     * @return      {@code String} title
     */
    public String getTitle(){
        return this.title;
    }
    /** This method is for removing Song object from the arraylist
     * @param song  {@code Song} The Song object to be removed
     * @return      {@code boolean} true or false
     */
	public boolean removeFromPlist(E song) {
		return this.remove(song);
    }
    /** This method is for fetching the entire Song oject
     * @param i  {@code int} The index of the Song
     * @return      {@code Song} Song at index i
     */
	public Song getSong(int i) {
		return this.get(i);
	}
    /** This method is for comparing title with input String
     * @param Title  {@code String} title
     * @return      {@code boolean} true or false
     */
	public boolean hasTitle(String Title) {
		return this.getTitle().toLowerCase().equals(Title.toLowerCase());
	}
    /** This method is for comparing artist with input String
     * @param Artist  {@code String} artist name
     * @return      {@code boolean} true or false
     */
	public boolean hasArtist(String Artist) {
		for(Song ss: this){
            if(ss.isArtist(Artist)){
                return true;
            }
        }
        return false;
	}
    /** This method is for getting the total number of existed songs
     * @return      {@code int} number of songs
     */
	public int numberOfSongs() {
		return this.size();
	}
    /** This method is for getting the total number of existed artists
     * @return      {@code int} number of artists
     */
	public int numberOfArtists() {
        HashSet<String> artists = new HashSet<>();
		for(Song ss: this){
            artists.add(ss.getArtist().toLowerCase());
        }
        return artists.size();
	}
    /** This method is for getting the total number of existed titles
     * @return      {@code int} number of titles
     */
	public int numberOfTitles() {
        HashSet<String> titles = new HashSet<>();
		for(Song ss: this){
            titles.add(ss.getTitle().toLowerCase());
        }
        return titles.size();
	}
    /** This method is for getting the total length of existed songs
     * @return      {@code int} length of all songs
     */
	public double playTime() {
        double ans = 0;
		for(Song ss: this){
            ans += ss.getLength();
        }
		return ans;
	}
    /** This method is for finding the index of the Song object
     * @param Song  {@code Song} Song name
     * @return      {@code int} index
     */
	public int findSong(E song) {
        int index = 0;
		for(Song ss: this){
            if(ss.equals(song)){
                return index;
            }
            index++;
        }
		return -1;
	}
    /** This method is for sorting the arraylist by artist
     */
	public void sortByArtist() {
        this.sort(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.getArtist().compareTo(s2.getArtist());
            }
        });
    }
    /** This method is for sorting the arraylist by title
     */
    public void sortByTitle() {
        this.sort(new Comparator<Song>() {
            @Override
            public int compare(Song s1, Song s2) {
                return s1.getTitle().compareTo(s2.getTitle());
            }
        });
	}
}
