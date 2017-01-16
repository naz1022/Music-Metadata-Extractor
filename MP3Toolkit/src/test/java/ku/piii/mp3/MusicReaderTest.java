/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.mp3;

import ku.piii.mp3.MusicReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MusicReader;
import static org.junit.Assert.assertEquals;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author K1302575
 */
public class MusicReaderTest {

    @Test
    public void testAddMusicMedia() {
        MusicMedia o = new MusicMedia(
                "PERFECT WORLD (AMBIENT)",
                "DARKPOP BAND ANGELIQUE",
                "Alt. Rock",
                "PERFECT WORLD",
                "1999",
                "00:09",
                "../test-music-files/collection-A"
        );
        MusicMediaCollection testCollection = new MusicMediaCollection();
        testCollection.addMusicMedia(o);
        assertEquals(testCollection.getMusic().get(0).getPath(), o.getPath());
    }

    @Test
    public void testAddFileDirectory() {
        File filepath = new File("../test-music-files");        
        MusicMediaCollection fileCollection = MusicReader.musicFolderReader(filepath);
        assertEquals(18, fileCollection.getMusic().size());
    }

    @Ignore
    public void testAddM3U() {
        File m3uOneTrack = new File("../test-m3u-data/testGetPlaylist/one_track.m3u");
        MusicMediaCollection m3uCollection = MusicReader.readM3UFile(m3uOneTrack);
        assertEquals(3, m3uCollection.getMusic().size());
    }
    
    
    
}
