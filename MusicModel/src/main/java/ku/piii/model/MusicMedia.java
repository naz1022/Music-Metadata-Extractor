package ku.piii.model;

/**
 * Tutorials and code used:
 * http://stackoverflow.com/questions/11357945/java-convert-seconds-into-day-hour-minute-and-seconds-using-timeunit
 */
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class MusicMedia  implements Comparable<MusicMedia>{

    //TO DO....
    //AA: Convert to JaudioTagger: n/a - not needed
    
    
    public enum Id3Version {
        V1, V2
    }
    private String path;
  
    private Integer lengthInSeconds;
    private String lengthInHMSFormat; //AA: Song length displayed as HH:MM:SS

    private Id3Version id3Version;
    // retrieves from the ID tag:
    private String title;
    private String year;
    private String genre;
    private String artist;
    private String album;

    public MusicMedia(){
        
    }
    
    //AA:Used in Application Viewer FX
    public MusicMedia(MusicMedia selectedItem) {
        this.title = selectedItem.getTitle();
        this.artist = selectedItem.getArtist();
        this.genre = selectedItem.getGenre();
        this.album = selectedItem.getAlbum();
        this.year = selectedItem.getYear();
        this.lengthInHMSFormat = selectedItem.getLengthInHMSFormat();
        this.lengthInSeconds = selectedItem.getLengthInSeconds();
        this.path = selectedItem.getPath();
    }
    
    
    public MusicMedia(String title, String artist, String genre, String album, String year, String lengthInHMSFormat, String path) {        
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
        this.lengthInHMSFormat = lengthInHMSFormat;
        this.path = path; 
    }
    
    public String getLengthInHMSFormat() {
        return lengthInHMSFormat;
    }

    public void setLengthInHMSFormat(String lengthInHMSFormat) {
        this.lengthInHMSFormat = lengthInHMSFormat;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

   

    public String getPath() {
        return path;
    }

    public void setPath(String thisPath) {
        this.path = thisPath;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public Id3Version getId3Version() {
        return id3Version;
    }

    public void setId3Version(Id3Version id3Version) {
        this.id3Version = id3Version;
    }

    @Override
    public int compareTo(MusicMedia o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //title.compareTo(o.title);
        //artist.compareTo(o.artist);
        //album.compareTo(o.album);
        
        //return compareTo(o);
        
    }

    @Override
    public String toString() {
        return "title=" + title + ", artist=" + artist + ", album=" + album + ","
                + " trackLength=" + lengthInHMSFormat + ", genre=" + genre + "\n";
    }

    /**
     *
     * @return
     */
    public String toStringPaste() {
        return title + " - " + artist + " - " + lengthInHMSFormat + " - "
                + album + " / " + genre + "/" + year + ")";
    }

    public String toM3UFormat() {

        return "#EXTINF:" + lengthInSeconds+ ", " + artist + " - " + title
                + "\n" + path + "\n\n";
    }
    
    //ISSUE: Some file typrd will display the trackLength in seconds NOT HH:MM:SS format
    //SOLUTION: Java 7 -  java.util.concurrent.TimeUnit;
    public String convertLengthFromSecondsToHMS(int seconds) {

        //int day = (int) TimeUnit.SECONDS.toDays(seconds);
        long hour = (int) TimeUnit.SECONDS.toHours(seconds) /*- (day * 24)*/;
        long minute = (int) TimeUnit.SECONDS.toMinutes(seconds) - (TimeUnit.SECONDS.toHours(seconds) * 60);
        long second = TimeUnit.SECONDS.toSeconds(seconds) - (TimeUnit.SECONDS.toMinutes(seconds) * 60);

        //String dayToString = null;
        String hourToString = "0";
        String minuteToString = null;
        String secondToString = null;

        //dayToString
        /*
                    if(day != 0){
                        dayToString = Integer.toString(day);
                    }
         */
        //hourToString
        if (hour < 10 && hour != 0) {
            hourToString = ("0" + hour);
        } else if (hour > 10) {
            hourToString = Long.toString(hour);
        }

        //minuteToString
        if (minute < 10 && minute != 0) {
            minuteToString = ("0" + minute);
        } else if (minute > 10) {
            minuteToString = Long.toString(minute);
        }

        //secondsToString
        if (second < 10) {
            secondToString = ("0" + second);
        } else if (seconds > 10) {
            secondToString = (Long.toString(second));
        }
        String HMS;

        if (!"0".equals(hourToString)){
            HMS = hourToString + ":"+ minuteToString + ":" + secondToString;
        } else {
            HMS = minuteToString + ":" + secondToString;
        }
        return HMS;
    }

}
