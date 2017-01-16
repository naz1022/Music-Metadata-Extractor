/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.nio.file;

import static org.junit.Assert.assertTrue;
import static ku.piii.nio.file.MusicFileUtils.isMP3;
import static ku.piii.nio.file.MusicFileUtils.isM3U;
import static ku.piii.nio.file.MusicFileUtils.isFlac;
import static ku.piii.nio.file.MusicFileUtils.isVorbis;
import static org.junit.Assert.assertFalse;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class MusicFileUtilsTest {

    public MusicFileUtilsTest() {
    }

    @Test
    public void canIdentifyNullPathAndReturnsFalse() {
        final Path myFile = null;
        assertFalse(isMP3(myFile));
        assertFalse(isM3U(myFile));
        assertFalse(isFlac(myFile));
        assertFalse(isVorbis(myFile));
    }

    @Test
    public void canIdentifyLowercaseMP3FileAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyUppercaseMP3FileAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.MP3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyMP4FileIsNotMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp4");
        assertFalse(isMP3(myFile));
    }

    @Test
    public void canIdentifyVariedCaseMP3FileExtentionAsMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mP3");
        assertTrue(isMP3(myFile));
    }

    @Test
    public void canIdentifyFileWithMp3InTitleIsNotMP3File() {
        final Path myFile = Paths.get("test-data/simple-file.mp3.txt");
        assertFalse(isMP3(myFile));
    }
    
    //FLAC Tests
    @Test
    public void canIdentifyLowercaseFlacFileAsFlacFile() {
        final Path myFile = Paths.get("test-data/simple-file.flac");
        assertTrue(isFlac(myFile));
    }

    @Test
    public void canIdentifyUppercaseFlacFileAsFlacFile() {
        final Path myFile = Paths.get("test-data/simple-file.FLAC");
        assertTrue(isFlac(myFile));
    }

    @Test
    public void canIdentifyMP4FileIsNotFlacFile() {
        final Path myFile = Paths.get("test-data/simple-file.mp4");
        assertFalse(isFlac(myFile));
    }

    @Test
    public void canIdentifyVariedCaseFlacFileExtentionAsFlacFile() {
        final Path myFile = Paths.get("test-data/simple-file.FlaC");
        assertTrue(isFlac(myFile));
    }

    @Test
    public void canIdentifyFileWithFlacInTitleIsNotFlacFile() {
        final Path myFile = Paths.get("test-data/simple-file.mp3.txt");
        assertFalse(isMP3(myFile));
    }
    
    //Vorbis Test
    @Test
    public void canIdentifyLowercaseVorbisFileAsVorbisFile() {
        final Path myFile = Paths.get("test-data/simple-file.ogg");
        assertTrue(isVorbis(myFile));
    }

    @Test
    public void canIdentifyUppercaseVorbisFileAsVorbisFile() {
        final Path myFile = Paths.get("test-data/simple-file.OGG");
        assertTrue(isVorbis(myFile));
    }

    @Test
    public void canIdentifyMP4FileIsNotVorbisFile() {
        final Path myFile = Paths.get("test-data/simple-file.mp4");
        assertFalse(isVorbis(myFile));
    }

    @Test
    public void canIdentifyVariedCaseVorbisFileExtentionAsVorbisFile() {
        final Path myFile = Paths.get("test-data/simple-file.oGG");
        assertTrue(isVorbis(myFile));
    }

    @Test
    public void canIdentifyFileWithOggExtensionInTitleIsNotVorbisFile() {
        final Path myFile = Paths.get("test-data/simple-file.mogg.txt");
        assertFalse(isVorbis(myFile));
    }
    
    //AA: JUnit Tests for Playlist File Types: PLS, M3U, SMI, SAMI, WPL, XSPF 
    //TO DO....
    
    @Test
    public void canIdentifyInvalidHaederInM3UFile(){
        final Path myFile = Paths.get("test-m3u-data/testCopy/invalidHeader");
        String testFileName = myFile.toString();
        assertFalse(MusicFileUtils.isValidHeader(testFileName));
    }

    
}
