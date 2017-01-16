/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.music;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import ku.piii.model.Playlist;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author k1302575
 */
public class M3UReaderTest {

    public static final String M3U_HEADER = "#EXTM3U";
    public static final String M3U_METADATA = "#EXTINF";
    public static final String MP3_EXTENSION = "mp3";

    @Ignore
    //@Test
    public static boolean isValidHeader(String filename) {
        boolean returnValue = false;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(new File(filename)));
            String firstLine = br.readLine();
            if (firstLine.startsWith(M3U_HEADER)) {
                returnValue = true;            }
            br.close();
        } catch (Exception e) {
            System.err.println("isValidHeader:: error with file "
                    + filename + ": " + e.getMessage());
        }
        return returnValue;
    }

    @Ignore
    //@Test
    public static int getNumberOfTracks(String filename) {
        int trackCounter = 0;

        if (isValidHeader(filename)) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(new File(filename)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    //Check that we can find .mp3 and that the line is not metadata
                    //How could we handle multiple file extensions?
                    if (!nextLine.startsWith(M3U_METADATA) && nextLine.endsWith(MP3_EXTENSION)) {
                        trackCounter++;
                    }
                }
            } catch (Exception e) {
                //Exception caught - set trackCounter to 0
                System.err.println("getNumberOfTracks:: error with file "
                        + filename + ": " + e.getMessage());
                trackCounter = 0;
            }
        }

        return trackCounter;
    }

    @Ignore
    //@Test
    public static int getTotalSeconds(String filename) {
        int totalSeconds = 0;

        if (isValidHeader(filename)) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(new File(filename)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    //If the next line is metadata it should be possible to extract the length of the song
                    if (nextLine.startsWith(M3U_METADATA)) {
                        int i1 = nextLine.indexOf(":");
                        int i2 = nextLine.indexOf(",");
                        String substr = nextLine.substring(i1 + 1, i2);
                        totalSeconds += Integer.parseInt(substr);
                    }
                }
            } catch (IOException | NumberFormatException e) {
                //Exception caught - set totalSeconds to 0
                System.err.println("getTotalSeconds:: error with file "
                        + filename + ": " + e.getMessage());
                totalSeconds = 0;
            }
        }
        return totalSeconds;
    }

    @Ignore
    //@Test
    public static boolean copy(String inputPlayList, String outputPlayList, int numberOfSeconds) {
        boolean isWritten = false;
        int minutesWritten = 0;
        int minutesToWrite = 0;

        if (isValidHeader(inputPlayList)) {
            BufferedReader br;
            BufferedWriter bw;
            try {
                br = new BufferedReader(new FileReader(new File(inputPlayList)));
                bw = new BufferedWriter(new FileWriter(new File(outputPlayList)));
                String nextLine;
                while ((nextLine = br.readLine()) != null) {
                    if (nextLine.startsWith(M3U_METADATA)) {
                        int start = nextLine.indexOf(":");
                        int end = nextLine.indexOf(",");
                        String subString = nextLine.substring(start + 1, end);
                        //Assign minutesToWrite the length of the current track
                        minutesToWrite = Integer.parseInt(subString);
                    }
                    /*
                     Check to see that the minutes written so far plus the minutes
                     that will be written this time does not exceed the specified
                     length of the playlist
                     */
                    if (minutesWritten + minutesToWrite <= numberOfSeconds) {
                        bw.write(nextLine);
                        bw.newLine();
                        minutesWritten += minutesToWrite;
                        minutesToWrite = 0;
                    }
                }
                //Assuming the loop ran - something should have been written to the output file
                //even if it's only the file header
                isWritten = true;
                br.close();
                bw.close();
            } catch (IOException | NumberFormatException e) {
                System.err.println("getTotalSeconds:: error with file "
                        + inputPlayList + ": " + e.getMessage());
            }
        }
        return isWritten;
    }
    
    
}

