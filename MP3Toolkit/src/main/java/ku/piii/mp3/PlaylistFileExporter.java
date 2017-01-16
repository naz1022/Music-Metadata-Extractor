/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.mp3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;


/**
 *
 * @author K1302575
 */
public class PlaylistFileExporter {
    
    //AA: TO FIX.....
    public static void exportLibAsTextFile(File mp3File) throws IOException{
        System.out.println("EXPORTING TEXT FILE");
        List<MusicMedia> mediaList = new ArrayList<>();
        MusicMediaCollection mediaCollection = new MusicMediaCollection();
        mediaList = mediaCollection.getMusic();
        FileWriter writer;
        try {
            writer = new FileWriter(mp3File);

            for (MusicMedia mp3 : mediaList) {
                writer.write(mp3.toString());
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(PlaylistFileExporter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public static void exportLibAsM3U(File mp3File) throws IOException {
        System.out.println("EXPORTING M3U FILE");
        List<MusicMedia> mediaList = new ArrayList<>();
        MusicMediaCollection mediaCollection = new MusicMediaCollection();
        //mediaList = mediaCollection.mergeCollection(mediaList);
        FileWriter writer;

        try {
            writer = new FileWriter(mp3File);
            writer.write("#EXTM3U\n\n");
            for (MusicMedia mp3 : mediaList) {
                writer.write(mp3.toM3UFormat());
                System.out.println(mp3.toString());
            }
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(PlaylistFileExporter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
