package ku.piii.gui.view;

/**Install external services (via jar file)
 * modify pom.xml -> build with dependencies -> manual install jar.
 */

/**
 * 
 * Register for API key using:
 * developers.quantonemusic.com
 * https://github.com/Quantone/sdk-java/releases
 * 
 */

import DecibelSDK.Decibel;
import DecibelSDK.DecibelException;
import DecibelSDK.DecibelObjectModel.Album;
import DecibelSDK.DecibelObjectModel.AlbumsQueryResult;
import DecibelSDK.DecibelQuery.AlbumsQuery;

/** 
 *
 * https://github.com/echonest/jEN 
 * AA: Github Repo to use for Java Echo Nest Support
 * 
 * 
 */

import com.echonest.api.v4.Artist;
import com.echonest.api.v4.Biography;
import com.echonest.api.v4.EchoNestAPI;
import com.echonest.api.v4.EchoNestException;
import com.echonest.api.v4.Image;
import com.echonest.api.v4.Track;
import java.io.File;
import java.io.IOException;
 


//import java.io.File;
//import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Control;

/**
 *
 * @author K1302575
 */
public class WebContextMenuAction {
    
    
    //AA: DECIBEL SDK Stage
    public static List<Album> albumQueryAction(String artist){
        List<Album> albums = null;

        try {

            ArrayList<String> artistName = new ArrayList<>();
            artistName.add(artist);
            Decibel decibel = new Decibel("50f75786", "cb2c0cef40d2b6724742067db99bffdb");
            AlbumsQuery queryAlbumObject = new AlbumsQuery();
            queryAlbumObject.setArtists(artistName);
            AlbumsQueryResult queryAlbumResults = decibel.Execute(queryAlbumObject);
            albums = queryAlbumResults.getResults();
        } catch (DecibelException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        return albums;
    }
    
}
