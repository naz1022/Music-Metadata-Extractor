/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.gui.view;

import ku.piii.model.MusicMedia;


//AA: Decibel Web Services
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

/**
 *
 * @author K1302575
 */
public class MusicContextMenuAction {
    
    public static void copyAction(MusicMedia musicFile){
        //AA: Create Clipboard Object + Parse MP3 toString method
        ClipboardContent content = new ClipboardContent();
        content.putString(musicFile.toStringPaste());
        Clipboard.getSystemClipboard().setContent(content);
    }

    /** .WEB SERVICE METHODS MOVED TO: ku.piii.gui.view.WebContextMenuAction. **/
}
