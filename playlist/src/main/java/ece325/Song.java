package ece325;
import java.util.Comparator;

/**
 * Assignment 6: Test Driven Development <br />
 * The {@code Song} class
 * @author Yongquan Zhang 1515873
 */
public class Song {
    // TODO: Assignment 6 -- complete this Song class to pass the tests
    private String artist;
    private String title;
    private double length;
    // Constructor
    public Song(String artist, String title, double length){
        this.artist = artist;
        this.title = title;
        this.length = length;
    }
    /** This method is for comparing artist with input String
     * @param artist  {@code String} artist name
     * @return      {@code boolean} true or false
     */
    public boolean isArtist(String artist){
        return (this.artist.toLowerCase().equals(artist.toLowerCase()));
    }
    /** This method is for comparing title with input String
     * @param title  {@code String} title name
     * @return      {@code boolean} true or false
     */
    public boolean isTitle(String title){
        return (this.title.toLowerCase().equals(title.toLowerCase()));
    }
    /** Return the private variable artist
     * @return      {@code String} artist
     */
    public String getArtist(){
        return this.artist;
    }
    /** Return the private variable title
     * @return      {@code String} title
     */
    public String getTitle(){
        return this.title;
    }
    /** Return the private variable length
     * @return      {@code String} length
     */
    public double getLength(){
        return this.length;
    }
    /** Override the method equals in order to compare Song object
     * @param obj  {@code Object} Song
     * @return      {@code boolean} true or false
     */    
    @Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof Song)) {
			return false;
		}
		Song song = (Song) obj;
        return (this.isArtist(song.getArtist()) &&
        this.isTitle(song.getTitle()) &&
        this.getLength() == song.getLength());
	}
}
