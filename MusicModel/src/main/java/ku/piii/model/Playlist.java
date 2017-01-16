
package ku.piii.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author k1302575
 * Code found on Stack Overflow
 */
public class Playlist {
    List<String> mp3;
    int next;
    public Playlist(File f){
        mp3=new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
               addMP3(line);
            }
        } catch (IOException ex) {
            System.out.println("error is reading the file");
        }
        next=-1;
    }    

    private void addMP3(String line){
        if(line==null)return;
        if(!Character.isUpperCase(line.charAt(0)))return;
        if(line.indexOf(":\\")!=1)return;
        if(line.indexOf(".mp3", line.length()-4)==-1)return;
        mp3.add(line);
    }

    public String getNext(){
        next++;
        if(mp3.size()<=next)next=0;
        return mp3.get(next);
    }
}