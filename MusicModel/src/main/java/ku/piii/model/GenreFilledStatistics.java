/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.model;

/**
 *
 * @author K1302575
 */
public class GenreFilledStatistics {
    private int totalNumTracks = 0;
    private int numTracksWithGenre = 0;
    private int numTracksWithoutGenre = 0;
    private int percentItemsNeedingGenre = 0;

    public int getTotalNumTracks() {
        return totalNumTracks;
    }

    public int getNumTracksWithGenre() {
        return numTracksWithGenre;
    }

    public int getPercentItemsNeedingGenre() {
        return (int) (100 - 100.0 * ( 
                (double) getNumTracksWithGenre() / getTotalNumTracks()));
    }

    public void addItemWithNoGenrePresent() {
        totalNumTracks++;
        this.numTracksWithoutGenre++;
        
    }

    public void addItemWithGenrePresent() {
        totalNumTracks++;
        this.numTracksWithGenre++;
    }

}
