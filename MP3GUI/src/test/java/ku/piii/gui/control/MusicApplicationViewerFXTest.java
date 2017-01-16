/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.gui.control;


import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import ku.piii.model.MusicMedia;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MusicReader;

import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author K1302575
 */
public class MusicApplicationViewerFXTest {
        
    
    @Test
    public void testAddFileDirectory(){
        File filepath = new File("../test-music-files");
        MusicMediaCollection fileCollection = MusicReader.musicFolderReader(filepath);
        assertEquals(18 ,fileCollection.getMusic().size());
    }
    
    @Ignore
    public void testAddM3U(){
        File m3uOneTrack = new File("../test-m3u-data/testGetPlaylist/three_tracks");
        MusicMediaCollection m3uCollection = MusicReader.readM3UFile(m3uOneTrack);
        //System.out.println("music 0:"+m3uCollection.getMusic().get(0));
        assertEquals(3, m3uCollection.getMusic().size());
    }
    
}
