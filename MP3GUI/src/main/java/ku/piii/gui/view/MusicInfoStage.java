package ku.piii.gui.view;

import DecibelSDK.DecibelObjectModel.Album;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.util.Callback;

/**
 *
 * @author K1302575
 */
public class MusicInfoStage {

    public static void displayAlbumList(String artistName) {

        // List array to hold artist albums returned
        List<Album> albums = WebContextMenuAction.albumQueryAction(artistName);

        //Create stage and set window properties
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Albums: "+ artistName);
        window.getIcons().add(new Image("/icons/album-icon.png"));
        //window.setMinWidth(500);
 
        ObservableList<Album> observableAlbumList = FXCollections.observableList(albums);
        TableView<Album> tableView = new TableView<>();
        
              
        tableView.setRowFactory((TableView<Album> param) -> {
            TableRow<Album> row;
            row = new TableRow<Album>() {
                
                @Override
                protected void updateItem(Album t, boolean b) {
                    
                    super.updateItem(t, b);
                    if (t != null && b == true) { //AA: Boolean true solved duplication issue of albums
                        observableAlbumList.add(t);
                    }
                }
            };
            return row;
        });

        TableColumn<Album, String> albumColumn;
        albumColumn = new TableColumn<>("Album");
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        albumColumn.setMinWidth(200);
        
        TableColumn<Album, String> artistColumn;
        artistColumn = new TableColumn<>("Contributing Artists");
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artistsLiteral"));
        artistColumn.setMinWidth(200);
        
        TableColumn<Album, String> releaseColumn;
        releaseColumn = new TableColumn<>("Release Date");
        releaseColumn.setCellValueFactory(new PropertyValueFactory<>("originalReleaseDate"));
        releaseColumn.setMinWidth(200);



        tableView.setItems(observableAlbumList);
        tableView.getColumns().addAll(
                albumColumn,
                artistColumn,
                releaseColumn
        );
        
        // Create static vBox
        VBox layout;
        layout = new VBox();
        layout.getChildren().add(tableView);
        layout.setAlignment(Pos.CENTER);
        layout.setMinWidth(800);
        
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
}
