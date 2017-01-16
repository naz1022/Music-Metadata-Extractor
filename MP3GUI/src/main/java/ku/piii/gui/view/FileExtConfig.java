/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.gui.view;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;
import javafx.stage.FileChooser;
import org.apache.commons.lang.ArrayUtils;

/**
 *
 * @author K1302575
 */
public class FileExtConfig {

    //AA: Write config for Audio File Chooser

    /**
     *
     * @param musicFileChooser
     */
    public static void configureAudioFileExtension(final FileChooser musicFileChooser) {
        musicFileChooser.setTitle("Add MP3 File");
        musicFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        
        //AA: String builder(s) for File Extension
        String[] mp3FileExt = new String[] {"*.mp3"};
        String[] mp4FileExt = new String[] {"*.m4a", "*.m4b", "*.m4p"};
        String[] threegpFileExt = new String[] {"*.3gp"}; //AA: String cannot start with numbers or special characters
        String[] audibleFileExt = new String[] {"*.aa", "*.aax"};
        String[] aacFileExt = new String[] {"*.aac"};
        String[] actFileExt = new String[] {"*.act"};
        String[] appleAudioFileExt = new String[] {"*.aiff"};
        String[] adaptMultiRateFileExt = new String[] {"*.amr", "*.3ga"};
        String[] apeFileExt = new String[] {"*.ape"};
        String[] sunMicroFileExt = new String[] {"*.au"};
        String[] adaptMultiRateWBFileExt = new String[] {"*.awb"};
        String[] nCHSoftwareFileExt = new String[] {"*.dct"};
        String[] olympusFileExt = new String[] {"*.dss"};
        String[] sonyFileExt = new String[] {"*.dvf", "*.msv"};
        String[] flacFileExt = new String[] {"*.flac"};
        String[] gsmFileExt = new String[]{"*.gsm"};
        String[] iKlaxFileExt = new String[] {"*.iklax"};
        String[] threeDSolarUKFileExt = new String[] {"*.3ivs"};
        String[] yamahaFileExt = new String[] {"*.mmf"};
        String[] mpegPlusFileExt = new String[]{"*.mpc"};
        String[] vorbisFileExt = {"*.oga", "*.ogg","*.mogg"};
        String[] ietfFileExt = {"*.opus"};
        String[] realNetworksFileExt = {"*.ra", "*.rm"};
        String[] rawFileExt = {"*.raw"};
        String[] slPCMFileExt = {"*.sln"};
        String[] trueAudioFileExt = {"*.tta"};
        String[] voxFileExt = {"*.vox"};
        String[] wavFileExt = {"*.wav"};
        String[] wmaFileExt = {"*.wma"};
        String[] wavPackFileExt = {"*.wv"};
        String[] htmlAudioFileExt = {"*.webm"};
    
        String[] musicExtList = {"*.3ga", "*.3gp", "*.3ivs", "*.aa", "*.aac", "*.aax",
            "*.act", "*.aiff", "*.amr", "*.ape", "*.au", "*.awb", "*.dct",
            "*.dss", "*.dvf", "*.flac", "*.gsm", "*.iklax", "*.mmf",
            "*.mpc", "*.msv", "*.oga", "*.ogg", "*.opus", "*.mogg", "*.ra", "*.rm", "*.raw",
            "*.tta", "*.sln", "*.vox", "*.wv", "*.wav","*.webm", "*.wma" };
     
        musicFileChooser.getExtensionFilters().addAll(//AA: extensions filters commented out so that ONLY MP3 FILES are selected
                
                //LIST FROM: https://en.wikipedia.org/wiki/Audio_file_format
                new FileChooser.ExtensionFilter("All File Formats", "*.*"),
                new FileChooser.ExtensionFilter("All Music Files", musicExtList),
                
                new FileChooser.ExtensionFilter("MPEG-3", mp3FileExt),
                new FileChooser.ExtensionFilter("FLAC", flacFileExt),
                new FileChooser.ExtensionFilter("Vorbis", vorbisFileExt),
                /**
                 * AA: Unsupported File Extensions below:
                 * FILTRATION WORKS BUT NOT ADD TO LIST
                 * UPDATE 11/01/2017: FLAC File has full functionality
                 * UPDATE 12/01/2017: Vorbis File has full functionality
                 */     
                new FileChooser.ExtensionFilter("MPEG-4", mp4FileExt),
                new FileChooser.ExtensionFilter("3GP", threegpFileExt),
                new FileChooser.ExtensionFilter("Audible", audibleFileExt),
                new FileChooser.ExtensionFilter("Advanced Audio Coding", aacFileExt),
                new FileChooser.ExtensionFilter("ACT", actFileExt),
                new FileChooser.ExtensionFilter("Apple", appleAudioFileExt),
                new FileChooser.ExtensionFilter("AMR-NB", adaptMultiRateFileExt),
                new FileChooser.ExtensionFilter("APE", apeFileExt),
                new FileChooser.ExtensionFilter("Sun Micro", sunMicroFileExt),
                new FileChooser.ExtensionFilter("AMR-WB", adaptMultiRateWBFileExt),
                new FileChooser.ExtensionFilter("NCH Software", nCHSoftwareFileExt),
                new FileChooser.ExtensionFilter("Olympus", olympusFileExt),
                new FileChooser.ExtensionFilter("Sony", sonyFileExt),                
                new FileChooser.ExtensionFilter("GSM", gsmFileExt),
                new FileChooser.ExtensionFilter("iKlax", iKlaxFileExt),
                new FileChooser.ExtensionFilter("3D Solar UK", threeDSolarUKFileExt),
                new FileChooser.ExtensionFilter("Samsung Yamaha", yamahaFileExt),
                new FileChooser.ExtensionFilter("MPEG-PLUS", mpegPlusFileExt),
                new FileChooser.ExtensionFilter("IETF", ietfFileExt),
                new FileChooser.ExtensionFilter("RealNetworks", realNetworksFileExt),
                new FileChooser.ExtensionFilter("RAW", rawFileExt),
                new FileChooser.ExtensionFilter("Signed Liear PCM", slPCMFileExt),
                new FileChooser.ExtensionFilter("True Audio", trueAudioFileExt),
                new FileChooser.ExtensionFilter("Vox", voxFileExt),
                new FileChooser.ExtensionFilter("WAV", wavFileExt),
                new FileChooser.ExtensionFilter("Windows Media Audio", wmaFileExt),
                new FileChooser.ExtensionFilter("Wavpack", wavPackFileExt),
                new FileChooser.ExtensionFilter("HTML5 Video", htmlAudioFileExt)
        
        );
    }

    //AA: Write config for Audio File Chooser

   
    /**
     *
     * @param m3uFileChooser
     */
    
    public static void configurePlaylistFileExtension(final FileChooser m3uFileChooser) {
        
         String[] m3uFileExt = {"*.m3u", "*.m3u8"};
         String[] aSRFileExt = {"*.asx"};
         String[] plsFileExt = {"*.pls"};
         String[] xsfpFileExt = {"*.xsfp"};
         String[] wplFileExt = {"*.wpl"};
         
         String[][] allPlaylistFileExt = {
             aSRFileExt,
             m3uFileExt,
             plsFileExt,
             wplFileExt,
             xsfpFileExt
         }; 
        
        m3uFileChooser.setTitle("Add M3U File");
        m3uFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        m3uFileChooser.getExtensionFilters().addAll( //AA: TO COMPLETE EXTENSION FILTER LIST....
                //LIST FROM: https://en.wikipedia.org/wiki/Playlist
                new FileChooser.ExtensionFilter("All File Formats", "*."),
                new FileChooser.ExtensionFilter("All Playlist Formats", Arrays.deepToString(allPlaylistFileExt)),
                new FileChooser.ExtensionFilter("ASX", aSRFileExt),
                new FileChooser.ExtensionFilter("M3U", m3uFileExt),
                new FileChooser.ExtensionFilter("PLS", plsFileExt),
                new FileChooser.ExtensionFilter("Windows Media Playlist", wplFileExt),
                new FileChooser.ExtensionFilter("XSFP", xsfpFileExt)
        );
    }


}
