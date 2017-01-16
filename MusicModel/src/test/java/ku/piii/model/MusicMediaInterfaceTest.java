package ku.piii.model;


import java.util.ArrayList;
import java.util.List;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author K1302575
 */
public class MusicMediaInterfaceTest {

    @Test
    public void testGetMusicMediaList() {
        List<MusicMedia> testList = new ArrayList<>();
        MusicMediaCollection testList1 = new MusicMediaCollection();
        testList1.getMusic();
        List<MusicMedia> expResult = testList;
        List<MusicMedia> result = testList1.getMusic();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetMusicMediaList() {
        List<MusicMedia> testList = new ArrayList<>();
        MusicMediaCollection testCollection = new MusicMediaCollection();
        testCollection.setMusic(testList);
        assertEquals(testList, testCollection.getMusic());
    }
    
    @Test
    public void testAddMusicMediaInMusicMediaCollection(){
        MusicMedia testMedia = new MusicMedia("title", "artist", "genre", "album", "year", "lengthInHMSFormat", "path");
        MusicMedia testMediaTwo = new MusicMedia("titleTwo", "artistTwo", "genreTwo", "albumTwo", "yearTwo", "lengthInHMSFormatTwo", "pathTwo");

        MusicMediaCollection testCollection = new MusicMediaCollection();
        
        testCollection.addMusicMedia(testMedia);
        testCollection.addMusicMedia(testMediaTwo);
        
        assertEquals(testMedia, testCollection.getMusic().get(0));
        assertEquals(testMediaTwo, testCollection.getMusic().get(1));
        
        assertEquals(testMedia.getAlbum(), testCollection.getMusic().get(0).getAlbum());
      
    }
    
}
