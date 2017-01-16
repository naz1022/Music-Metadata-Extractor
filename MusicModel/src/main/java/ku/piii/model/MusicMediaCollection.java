package ku.piii.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.scene.control.TableView;


public class MusicMediaCollection {
    
    //AA: Static context needed for handling Delete function in FX
    private static List<MusicMedia> musicFX = new CopyOnWriteArrayList<>();

    public static List<MusicMedia> getMusicFX() {
        return musicFX;
    }

    public static void setMusicFX(List<MusicMedia> musicFX) {
        MusicMediaCollection.musicFX = musicFX;
    }
    
    //AA: Do not modify as it will break entire program
    private List<MusicMedia> music = new CopyOnWriteArrayList<>();

    public List<MusicMedia> getMusic() {
        return music;
    }

    
    public void setMusic(List<MusicMedia> music) {
        this.music = music;
    }

    public void addMusicMedia(final MusicMedia musicMedia) {
        music.add(musicMedia);
    }
    
    public static void addMusicMediaObject(MusicMedia o){
        MusicMediaCollection newC = new MusicMediaCollection();
        newC.music.add(o);
    }

    public static void removeMusicMedia(final MusicMedia o) {
       MusicMediaCollection deleteMedia = new MusicMediaCollection();
       deleteMedia.getMusic().remove(o);
    }

    public MusicMediaCollection mergeCollection(final MusicMediaCollection musicMedia) {
        final MusicMediaCollection merge = new MusicMediaCollection();
        final ArrayList<MusicMedia> mergedMusic = new ArrayList<>();
        mergedMusic.addAll(music);
        mergedMusic.addAll(musicMedia.getMusic());
        merge.setMusic(mergedMusic);
        return merge;
    }
    
    public void addCallBack(String title, String artist, String album, String genre) {
        MusicMediaCollection m = new MusicMediaCollection();
        MusicMedia musicMediaFile;
        musicMediaFile = new MusicMedia(title,artist,album,"track time sample",genre,"hello","pathhhhhhh");        
        addMusicMediaObject(musicMediaFile);
    }

    public static void editMusicMediaObject(MusicMedia media, String t, String a, String alb, String g) {
        MusicMedia musicFileNew = new MusicMedia(null, null, null, null, null, null, null);
        System.out.println("Edit Method Called");
        System.out.println(musicFileNew.getPath());
        File musicFilePath = new File(musicFileNew.getPath());
        
   
    }

 
}
