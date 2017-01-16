/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.gui.view;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import com.mpatric.mp3agic.Mp3File;
import java.io.IOException;
import ku.piii.mp3.PlaylistFileExporter;

/**
 *
 * @author K1302575
 */
public class MusicMenuBarAction {

    public static void exportAction(Stage exportStage) {
        FileChooser fileChooserOne = new FileChooser();
        fileChooserOne.setTitle("Save As...");
        File mp3File = fileChooserOne.showSaveDialog(exportStage);
    }

    public static void addAction(Stage stage) {

        String path = "C:\\Users\\Anas Adurker\\Documents";
        File musicDirectory = new File(path);
        FileChooser fileChooser1 = new FileChooser();
        fileChooser1.setTitle("Add Musicjhh...");
        File file = fileChooser1.showOpenDialog(stage);
        file.renameTo(new File(path + file.getName()));


    }
    
     public static void exportAsPlaylist(Stage stage) throws IOException{
        
        FileChooser.ExtensionFilter fileChooserExt = new FileChooser.ExtensionFilter(
                "M3U(*.m3u)", "*.m3u"
        ); //AA: Can be extended to include more music enxtensions
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(fileChooserExt);
        fileChooser.setTitle("Export M3U");
        File file = fileChooser.showSaveDialog(stage);
        PlaylistFileExporter.exportLibAsM3U(file); //AA: If extended this needs to changed with if statement OR use getters and setters, Enum etc.
        
    }

}

