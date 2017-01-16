/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.music;

import java.nio.file.Paths;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.model.GenreFilledStatistics;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Ignore;

public class MusicMediaServiceImplGenreFilledTest {

    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Test(expected = IllegalArgumentException.class)
    public void nullInputTest() {
        MUSIC_SERVICE.getGenreFilledStats(null);
    }

    @Test
    public void getCollectionCountTest() {
        String pathToAddFrom = "../test-music-files/Collection-B";
        MusicMediaCollection collection = 
                MUSIC_SERVICE.createMusicMediaCollection(Paths.get(pathToAddFrom));
       
        GenreFilledStatistics metaDataStatistics = MUSIC_SERVICE.getGenreFilledStats(collection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalNumTracks(), equalTo(9));
        

    }

    @Test
    public void getNumWithTitleTest() {
        String pathToAddFrom = "../test-music-files/Collection-B";
        MusicMediaCollection collection = 
                MUSIC_SERVICE.createMusicMediaCollection(Paths.get(pathToAddFrom));
       

        GenreFilledStatistics metaDataStatistics = MUSIC_SERVICE.getGenreFilledStats(collection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getNumTracksWithGenre(), equalTo(8));

    }

    @Test
    public void getPercentWithTitle() {
        String pathToAddFrom = "../test-music-files/Collection-B";
        MusicMediaCollection collection = 
                MUSIC_SERVICE.createMusicMediaCollection(Paths.get(pathToAddFrom));
       

        GenreFilledStatistics metaDataStatistics = MUSIC_SERVICE.getGenreFilledStats(collection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getPercentItemsNeedingGenre(), equalTo(11));

    }

}
