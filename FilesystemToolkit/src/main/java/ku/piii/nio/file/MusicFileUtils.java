package ku.piii.nio.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
//import org.apache.commons.io.FilenameUtils; //AA: NOT NEEDED
//import java.io.File;

public class MusicFileUtils {

    //AA: Check if file is of format MP3
    public static boolean isMP3(final Path myFile) {

        if (myFile != null) {
            // Check for uppercase
            if (myFile.toFile().toString().endsWith(".MP3")
                    || myFile.toFile().toString().endsWith(".mp3")
                    || myFile.toFile().toString().endsWith(".Mp3")
                    || myFile.toFile().toString().endsWith(".mP3")) {
                return true;
            }
        }
        return false;
    }

    
    //AA: Check for other types of Music Media Formats - FLAC
    public static boolean isFlac(final Path myFile){
        
        if(myFile != null) {
            //check for uppercase
            if (myFile.toFile().toString().endsWith(".FLAC")
                    || myFile.toFile().toString().endsWith(".flac")
                    || myFile.toFile().toString().endsWith(".Flac")
                    || myFile.toFile().toString().endsWith(".FLac")
                    || myFile.toFile().toString().endsWith(".FlAc")
                    || myFile.toFile().toString().endsWith(".FlaC")
                    || myFile.toFile().toString().endsWith(".FLAc")
                    || myFile.toFile().toString().endsWith(".FLaC")
                    || myFile.toFile().toString().endsWith(".fLac")
                    || myFile.toFile().toString().endsWith(".fLAc")
                    || myFile.toFile().toString().endsWith(".fLaC")
                    || myFile.toFile().toString().endsWith(".fLAC")
                    || myFile.toFile().toString().endsWith(".flAC")
                    || myFile.toFile().toString().endsWith(".flaC")) {
                return true;
            } //check for lowercase
        } return false;
    }

    public static boolean isVorbis(final Path myFile) {
        if (myFile != null) {
            //check for uppercase
            if (myFile.toFile().toString().endsWith(".ogg".toLowerCase())
                    || myFile.toFile().toString().endsWith(".ogg".toUpperCase())
                    || myFile.toFile().toString().endsWith(".Ogg")
                    || myFile.toFile().toString().endsWith(".oGg")
                    || myFile.toFile().toString().endsWith(".ogG")
                    || myFile.toFile().toString().endsWith(".OgG")
                    || myFile.toFile().toString().endsWith(".OGg")
                    || myFile.toFile().toString().endsWith(".oGG")) {
                return true;
            } else
                if (myFile.toFile().toString().endsWith(".oga".toLowerCase())
                    || myFile.toFile().toString().endsWith(".oga".toUpperCase())
                    || myFile.toFile().toString().endsWith(".Oga")
                    || myFile.toFile().toString().endsWith(".oGa")
                    || myFile.toFile().toString().endsWith(".ogA")
                    || myFile.toFile().toString().endsWith(".OgA")
                    || myFile.toFile().toString().endsWith(".OGa")
                    || myFile.toFile().toString().endsWith(".oGA")) {
                return true;
            } else
                if (myFile.toFile().toString().endsWith(".mogg".toLowerCase())
                        || myFile.toFile().toString().endsWith(".mogg".toUpperCase())
                        || myFile.toFile().toString().endsWith(".mOgg")
                        || myFile.toFile().toString().endsWith(".moGg")
                        || myFile.toFile().toString().endsWith(".mogG")
                        || myFile.toFile().toString().endsWith(".mOgG")
                        || myFile.toFile().toString().endsWith(".mOGg")
                        || myFile.toFile().toString().endsWith(".moGG")
                        || myFile.toFile().toString().endsWith(".MOgg")
                        || myFile.toFile().toString().endsWith(".MoGg")
                        || myFile.toFile().toString().endsWith(".MogG")
                        || myFile.toFile().toString().endsWith(".MOgG")
                        || myFile.toFile().toString().endsWith(".MOGg")
                        || myFile.toFile().toString().endsWith(".MoGG")) {
                    return true;
                }
            //return false; //AA: Redundant
        }
        return false;
    }

    //AA: check if file is a M3U file
    public static boolean isM3U(final Path myFile) {
        if (myFile != null) {
            //check for uppercase
            if (myFile.toFile().toString().endsWith(".M3U")) {
                return true;
            } //check for lowercase
            else if (myFile.toFile().toString().endsWith(".m3u")) {
                return true;
            } //check for mixedcase
            else if (myFile.toFile().toString().toLowerCase().endsWith(".m3U")) {
                return true;
            }
        }
        return false;
    }
    
    //AA: Last year's code with slight modifications
    public static final String M3U_HEADER = "#EXTM3U";

    public static boolean isValidHeader(String filename) {
        boolean returnValue = false;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String firstLine = br.readLine();
            if (firstLine.startsWith(M3U_HEADER)) {
                returnValue = true;            
            }
            br.close();
        } catch (Exception e) {
            System.err.println("isValidHeader:: error with file "
                    + filename + ": " + e.getMessage());
        }
        return returnValue;
    }
    
    
    

    //AA: Check if file is a playlist
    public enum playlistExtensions {
        PLS, M3U, SMI, SAMI, WPL, XSPF
    }//enum through playlist file types

    public class Playlist {

        public playlistExtensions getExt() {
            return ext;
        }

        private playlistExtensions ext;

        public void setExt(final playlistExtensions ext) {
            this.ext = ext;
        }
    }

    public static boolean isPlaylist(final Playlist playlist, Path myPlaylistFile) {

        if (myPlaylistFile != null) {
            if (myPlaylistFile.toFile().toString().endsWith("PLS".toLowerCase())) {
                playlist.setExt(playlistExtensions.PLS);
                return true;
            } else if (myPlaylistFile.toFile().toString().endsWith("M3U".toLowerCase())) {
                playlist.setExt(playlistExtensions.M3U);
                return true;
            } else if (myPlaylistFile.toFile().toString().endsWith("SMI".toLowerCase())) {
                playlist.setExt(playlistExtensions.SMI);
                return true;
            } else if (myPlaylistFile.toFile().toString().endsWith("SAMI".toLowerCase())) {
                playlist.setExt(playlistExtensions.SAMI);
                return true;
            } else if (myPlaylistFile.toFile().toString().endsWith("WPL".toLowerCase())) {
                playlist.setExt(playlistExtensions.WPL);
                return true;
            } else if (myPlaylistFile.toFile().toString().endsWith("XSPF".toLowerCase())) {
                playlist.setExt(playlistExtensions.XSPF);
                return true;
            }
            //return false; //AA: Redundant return statement
        }return false;

    }

}
