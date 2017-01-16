/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.mp3;

//AA: ADD EXTRA DEPENDENCIES [Right click 'Dependencies'  -> 'Add Dependencies' -> search -> results filter -> Add]
import java.io.BufferedReader;

//AA: Replaced with JAudioTagger
//import com.mpatric.mp3agic.ID3v2;
//import com.mpatric.mp3agic.InvalidDataException;
//import com.mpatric.mp3agic.Mp3File;
//import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.nio.file.MusicFileUtils;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.audio.flac.FlacInfoReader;
import org.jaudiotagger.audio.flac.FlacTagReader;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.ogg.util.OggInfoReader;

import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.AbstractID3Tag;
import org.jaudiotagger.tag.id3.AbstractID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;


/**
 *
 * @author Anas tutorials and code used:
 * http://stackoverflow.com/questions/26975833/array-scanner-for-loop
 * http://stackoverflow.com/questions/18775846/how-to-load-all-file-paths-into-a-string-array
 * https://docs.oracle.com/javase/tutorial/essential/io/pathOps.html
 * https://docs.oracle.com/javase/tutorial/essential/environment/paths.html
 * https://docs.oracle.com/javase/tutorial/java/nutsandbolts/for.html
 * http://codingbat.com/doc/java-for-while-loops.html
 * http://stackoverflow.com/questions/5600422/method-to-find-string-inside-of-the-text-file-then-getting-the-following-lines
 * http://stackoverflow.com/questions/21989887/how-to-print-lines-from-a-file-that-contain-a-specific-word-using-java
 * http://stackoverflow.com/questions/1761051/difference-between-n-and-r
 * http://stackoverflow.com/questions/11839190/how-to-read-next-line-in-txt-file
 * http://stackoverflow.com/questions/16919501/create-a-path-from-string-in-java7
 * http://www.programcreek.com/java-api-examples/index.php?source_dir=xtrememp-swing-master/src/xtrememp/tag/FlacInfo.java
 * http://www.jthink.net/jaudiotagger/javadoc/org/jaudiotagger/audio/asf/data/class-use/Chunk.html#org.jaudiotagger.audio.asf.data
 * Programming III: Year 2014/15 starting code
 *
 */
public class MusicReader {

    //NOTE: JAudio Tagger will will used as Vorbis and FLAC are supported
    public static MusicMedia musicFileReader(File file) {
        MusicMedia musicMedia = new MusicMedia();
        /**
         * modes for Generic Audio Header
         * r = reading
         * rw = reading and writing
         * rws = reading and writing
         * rwd = reading and writing and also update content or metadata
         * rws = reading and writing and also update content
         */
        GenericAudioHeader genAudHead;
        AudioFile musicFile;
        if (MusicFileUtils.isMP3(file.toPath())) {
            try {

                /* AA: DEPRECATED - No longer using MP3agic as additonal file format such as .FLAC is unavaialble*/
                // Mp3File mp3file = new Mp3File(file);
                // ID3v2 id3v2Tag = mp3file.getId3v2Tag();
                // musicMedia.setTitle(id3v2Tag.getTitle());
                // musicMedia.setArtist(id3v2Tag.getArtist());
                // musicMedia.setAlbum(id3v2Tag.getAlbum());
                // musicMedia.setLengthInSeconds(id3v2Tag.getLength());
                // musicMedia.setGenre(id3v2Tag.getGenreDescription());
                // musicMedia.setYear(id3v2Tag.getYear());
                // musicMedia.setPath(file.getAbsolutePath());
                //AA: Replaced MP3Agic library with JAudiotagger
                MP3File mp3file = new MP3File(file);
                MP3AudioHeader audioHead = mp3file.getMP3AudioHeader();

                org.jaudiotagger.tag.Tag tag = mp3file.getTag();
                AbstractID3v1Tag id3v1 = mp3file.getID3v1Tag();
                AbstractID3v2Tag id3v2 = mp3file.getID3v2Tag();
                AbstractID3v2Tag id3v24 = (AbstractID3v2Tag) mp3file.getID3v2TagAsv24();

                //System.out.println(id3v1); //AA: Testing id3v1
                //System.out.println(id3v2); //AA: Testing id3v2
                //System.out.println(id3v24); //AA: Testing id3v24
                if (id3v24 != null) {
                    musicMedia.setTitle(id3v24.getFirst(ID3v24Frames.FRAME_ID_TITLE));
                    musicMedia.setArtist(id3v24.getFirst(ID3v24Frames.FRAME_ID_ARTIST));
                    musicMedia.setAlbum(id3v24.getFirst(ID3v24Frames.FRAME_ID_ALBUM));
                    musicMedia.setGenre(id3v24.getFirst(ID3v24Frames.FRAME_ID_GENRE));
                    musicMedia.setYear(id3v24.getFirst(ID3v24Frames.FRAME_ID_YEAR));
                    musicMedia.setLengthInHMSFormat(audioHead.getTrackLengthAsString());
                    musicMedia.setLengthInSeconds(audioHead.getTrackLength());
                    musicMedia.setPath(file.getAbsolutePath());
                } else if (id3v2 != null) {
                    musicMedia.setTitle(id3v2.getFirst(FieldKey.TITLE));
                    musicMedia.setArtist(id3v2.getFirst(FieldKey.ARTIST));
                    musicMedia.setAlbum(id3v2.getFirst(FieldKey.ALBUM));
                    musicMedia.setGenre(id3v2.getFirst(FieldKey.GENRE));
                    musicMedia.setYear(id3v2.getFirst(FieldKey.YEAR));
                    musicMedia.setLengthInHMSFormat(audioHead.getTrackLengthAsString());
                    musicMedia.setPath(file.getAbsolutePath());
                }

            } catch (TagException | ReadOnlyFileException | InvalidAudioFrameException | IOException /* | UnsupportedTagException | InvalidDataException*/ ex) {
                Logger.getLogger(MusicReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (MusicFileUtils.isFlac(file.toPath())) {

            try {
                musicFile = AudioFileIO.read(file);
                FlacTag flacTag = (FlacTag) musicFile.getTag();
                FlacInfoReader flacInfoReader = new FlacInfoReader();
                
                genAudHead = flacInfoReader.read(new RandomAccessFile(file, "r"));
                if (genAudHead != null) {
                    musicMedia.setLengthInSeconds(genAudHead.getTrackLength());
                    /**
                     * AA: REDUNDANT METHOD ALTERNATIVE BELOW
                     * int seconds =musicMedia.getLengthInSeconds();
                     * String converted =musicMedia.convertLengthFromSecondsToHMS(seconds);
                     * musicMedia.setLengthInHMSFormat(converted);
                     */
                    musicMedia.setLengthInHMSFormat(musicMedia.convertLengthFromSecondsToHMS(musicMedia.getLengthInSeconds()));
                }
                if (flacTag != null) {
                    musicMedia.setTitle(flacTag.getFirst(FieldKey.TITLE));
                    musicMedia.setArtist(flacTag.getFirst(FieldKey.ARTIST));
                    musicMedia.setAlbum(flacTag.getFirst(FieldKey.ALBUM));
                    musicMedia.setGenre(flacTag.getFirst(FieldKey.GENRE));
                    musicMedia.setYear(flacTag.getFirst(FieldKey.YEAR));
                    musicMedia.setPath(file.getAbsolutePath());
                }

            } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
                Logger.getLogger(MusicReader.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (MusicFileUtils.isVorbis(file.toPath())){
            try {
                musicFile = AudioFileIO.read(file);
                VorbisCommentTag vorbCommentTag = (VorbisCommentTag) musicFile.getTag();
                OggInfoReader oggInfoReader = new OggInfoReader();
                genAudHead = oggInfoReader.read(new RandomAccessFile(file, "r"));
                
                if (genAudHead != null){
                    musicMedia.setLengthInSeconds(genAudHead.getTrackLength());
                    musicMedia.setLengthInHMSFormat(musicMedia.convertLengthFromSecondsToHMS(musicMedia.getLengthInSeconds()));
                }
                if (vorbCommentTag != null){
                    musicMedia.setTitle(vorbCommentTag.getFirst(FieldKey.TITLE));
                    musicMedia.setArtist(vorbCommentTag.getFirst(FieldKey.ARTIST));
                    musicMedia.setAlbum(vorbCommentTag.getFirst(FieldKey.ALBUM));
                    musicMedia.setGenre(vorbCommentTag.getFirst(FieldKey.GENRE));
                    musicMedia.setYear(vorbCommentTag.getFirst(FieldKey.YEAR));
                    musicMedia.setPath(file.getAbsolutePath());
                }
            } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException ex) {
                Logger.getLogger(MusicReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return musicMedia;
    }

    public static MusicMediaCollection musicFolderReader(File f) { //METHOD NEEDS FIXING - EDIT 05/12/2016 METHOD WAS FIXED;

        File[] files;
        MusicMediaCollection newCollection = new MusicMediaCollection();
        if (f.isDirectory() && (files = f.listFiles()) != null) {
            for (File file : files) {
                //AA: Method caller
                System.out.println(f.getAbsolutePath());
                MusicMediaCollection tmpCollection = musicFolderReader(file);
                newCollection = newCollection.mergeCollection(tmpCollection);
                //newCollection.getMusic().addAll(tmpCollection.getMusic());
            }
        } else {
            String path = f.getPath();
            Path p = f.toPath();

            if (MusicFileUtils.isMP3(p) || MusicFileUtils.isFlac(p) || MusicFileUtils.isVorbis(p)) {
                //if(MusicFileUtils.isMP3(f.toPath())){ //AA: Alternate if statement but does nto work 
                System.out.println(f.getAbsolutePath());
                newCollection.addMusicMedia(musicFileReader(p.toFile()));
            }
        }
        return newCollection;
    }

    //TO DO.....
    //ATTEMPT Buffered Reader Method
    public static MusicMediaCollection readM3UFile(File m3u) {
        //AA: Possible solution(s): get a path of the file from the #EXTINF: OR CHECK IF line.contains .mp3

        MusicMediaCollection m3uCollection = new MusicMediaCollection();
        Scanner m3uFileScanner;

        //MusicMedia m3uMix = new MusicMedia(); //AA: No need for this line of code
        System.out.println("HELLO M3U");

        try {
            m3uFileScanner = new Scanner(new FileReader(m3u));
            String line = m3uFileScanner.toString();
            System.out.println("try works"); //AA: Check if program gets to try

            int lineNumb = 0;
            while (m3uFileScanner.hasNextLine() == true || line.startsWith("#EXTM3U") || !line.startsWith("#EXTINF")) { //go through each line within the M3U file 
                //m3uFileScanner.next();

                m3uFileScanner.next();
            }

            System.out.println("while loop works");

            lineNumb++;
            System.out.println("current line number is: " + lineNumb); //AA: Test if scanner is working
            //if (MusicFileUtils.isM3U(m3u.toPath())) {
            //MERGE LINES TOGETHER

            line = m3uFileScanner.nextLine().replace("\\", "/").replace(" ", "20%");
            Path filePath;
            if (line.endsWith(".mp3")) {
                //m3uFileScanner.nextLine();
                String lineTwo = m3uFileScanner.nextLine().replace("\\", "/");//.replace(" ", "20%");

                System.out.println(lineTwo);
                filePath = Paths.get(URI.create(lineTwo));
            } else {
                filePath = Paths.get(URI.create(line));
                m3uFileScanner.nextLine();
            }
            //AA: Illegal character in opaque ISSUE found possible solution(s):
            /**
             * http://stackoverflow.com/questions/4992317/illegal-character-in-path-at-index-16
             * http://stackoverflow.com/questions/13153697/how-to-replace-with-in-a-java-string
             */

            //replaceAll = filePath.toString().replace("\\\\", File.separator);
            System.out.println(filePath); //AA: ***CODE BREAKS HERE: 13/12/2016***

            System.out.println("line :" + filePath + " is a mp3 file directory");
            //it doesn't like the number 2 LOL
            System.out.println(filePath.toString().indexOf(64));
            MusicMedia temp = musicFileReader(filePath.toAbsolutePath().toFile());
            MusicMediaCollection tempCol = new MusicMediaCollection();
            m3uCollection.mergeCollection(tempCol);

            //AA: Test which line is read as a mp3 directory
            //AA: Create a file path from STRING 'line' variable. Throws null pointer and Illegal args exceptions
            //m3uMix.setPath(line); //AA: not needed as readMusicFile sets all attributes of MusicMedia
            //MP3File mp3file = new MP3File(filePath.toFile()); //AA: No need, Rand line of code
            //m3uCollection.addMusicMedia(readMusicFile(filePath.toFile())); //AA: Alternate method can be used, i.e. more elegant way
            //MusicMedia tempMedia =  readMusicFile(filePath.toFile()); //AA: remove as it is concurrent
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
            Logger.getLogger(MusicReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Current Collection:" + m3uCollection);
        return m3uCollection;

    }

}
