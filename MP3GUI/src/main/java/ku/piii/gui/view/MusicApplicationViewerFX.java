package ku.piii.gui.view;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.Scene;

import javafx.scene.control.ContextMenu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.mp3.MusicReader;

/**
 *
 * @author K1302575
 */
/**
 * Tutorials Used: http://docs.oracle.com/javafx/2/ui_controls/menu_controls.htm
 * http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm
 * https://docs.oracle.com/javafx/2/api/javafx/scene/layout/VBox.html
 * https://blogs.oracle.com/jmxetc/entry/connecting_scenebuilder_edited_fxml_to
 * https://developers.google.com/youtube/v3/code_samples/java
 * http://alvinalexander.com/java/java-file-utilities-open-read-write-copy-files
 */
public class MusicApplicationViewerFX extends Application {

    Stage mainStage;
    TableView<MusicMedia> table;
    VBox vboxLayout;
    Stage fileChoosor;
    private final ObservableList<MusicMedia> data;
    private final Image ico;
    

    public MusicApplicationViewerFX() {
        this.ico = new Image("/icons/music-icon.png");
        this.data = FXCollections.observableArrayList();

    }

    /**
     * AA: TO DO... make thread safe and compatible with FXML
     *
     * @param musicMediaStage
     */
    @Override
    public void start(Stage musicMediaStage) {
        //throw new UnsupportedOperationException("Not supported yet."); //AA:  not throwing exception
        mainStage = musicMediaStage;
        mainStage.setTitle("Music Playlist");
        mainStage.getIcons().add(ico);
        //Create new menu items (Will be displayed on top left by default)
        MenuBar mp3MenuBar;
        mp3MenuBar = new MenuBar();
        Menu fileMenu;
        fileMenu = new Menu("File");

        //create an export as Text Menu Item //AA: Needs fixing (Music Service -> PlaylistFileExporter)
        MenuItem exportPlaylistAsText;
        exportPlaylistAsText = new MenuItem("Save As...");
        exportPlaylistAsText.setOnAction((ActionEvent actionEvent) -> {
            MusicMenuBarAction.exportAction(new Stage());

        });
        //fileMenu.getItems().add(exportPlaylistAsText); //AA: Redundant

        //create an export as M3U Menu Item //AA: Needs fixing (Music Service -> PlaylistFileExporter) 
        MenuItem exportPlaylistAsM3U;
        exportPlaylistAsM3U = new MenuItem("Save Playlist...");
        exportPlaylistAsM3U.setOnAction((ActionEvent actionEvent) -> {
            try {
                MusicMenuBarAction.exportAsPlaylist(new Stage());
            } catch (IOException | NullPointerException ex) {
                Logger.getLogger(MusicApplicationViewerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //fileMenu.getItems().add(exportPlaylistAsM3U); //AA: Redundant

        //Clear entire playlist
        MenuItem clearPlaylist;
        clearPlaylist = new MenuItem("Clear All");
        clearPlaylist.setOnAction(actionEvent -> data.clear());
        //fileMenu.getItems().add(clearPlaylist); //AA: REDUNDANT

        //Create a close button within Menu Bar
        MenuItem closeApplication;
        closeApplication = new MenuItem("Close Application");
        closeApplication.setOnAction(actionEvent -> Platform.exit());
        //fileMenu.getItems().add(closeApplication); //AA: REDUNDANT

        //AA: Adds all menu items in a specific order. Reordering list will change structure
        fileMenu.getItems().addAll(
                exportPlaylistAsText,
                exportPlaylistAsM3U,
                new SeparatorMenuItem(),
                clearPlaylist,
                new SeparatorMenuItem(),
                closeApplication
        );

        //AA: Create Music File Event Handler
        Menu addMenu;
        addMenu = new Menu("Add Media");
        MenuItem addMusicMedia;
        addMusicMedia = new MenuItem("Add Music File");
        addMusicMedia.setOnAction((ActionEvent event) -> {
            Stage musicStage;
            musicStage = new Stage();
            //String musicPath = ("testfiles\testdata.mp3"); //AA: used for testing
            FileChooser musicFileChooser;
            musicFileChooser = new FileChooser();

            FileExtConfig.configureAudioFileExtension(musicFileChooser); //AA: choose which file extension to allow
            //musicFileChooser.setTitle("Add MP3 File"); //AA: Redundant
            File musicFile = musicFileChooser.showOpenDialog(musicStage);
            if (musicFile != null && musicFile.exists()) {
                data.add(MusicReader.musicFileReader(musicFile));
            } else {
                throw new NullPointerException("NO ACTION PERFORMED: MP3 FILE READ ERROR");//AA: null point exception handler
            }
        });
        //addMenu.getItems().add(addMusicMedia); //AA: Redundant

        //AA: Create Music File Directory Event Handler
        MenuItem addFileDirectory = new MenuItem("Add Folder");
        addFileDirectory.setOnAction((ActionEvent e) -> {
            Stage folderStage = new Stage();

            DirectoryChooser dc = new DirectoryChooser();
            dc.setTitle("Add Music Folder");

            File f = dc.showDialog(folderStage);
            File folder = new File(f.getAbsolutePath());

            MusicMediaCollection fileDir = MusicReader.musicFolderReader(folder);

            data.clear();
            //data.addAll(fileDir.getMusic());
            fileDir.getMusic().stream().forEach((m) -> {
                if (m.getPath() != null) {
                    System.out.println("FILE READ SUCCESSFUL: " + m.getPath()); //AA: Test file read
                    data.add(m);
                }
            });
        });
        //addMenu.getItems().add(addFileDirectory); //AA: Redundant

        //AA: Create M3U File Reader Event Handler
        MenuItem addM3UFile = new MenuItem("Add M3U playlist");
        addM3UFile.setOnAction((ActionEvent e) -> {
            Stage m3uStage = new Stage();
            //String musicPath = ("testfiles\testdata.m3u"); //AA: used for testing
            FileChooser m3uChooser = new FileChooser();
            FileExtConfig.configurePlaylistFileExtension(m3uChooser);

            File m3uFile = m3uChooser.showOpenDialog(m3uStage);

            if (m3uFile != null) {
                MusicMediaCollection m3uObj = MusicReader.readM3UFile(m3uFile);
                data.clear();
                m3uObj.getMusic().stream().forEach((MusicMedia m) -> {
                    data.add(m);
                });
            } else {
                throw new NullPointerException("NO ACTION PERFORMED: M3U chooser");
            }
        });

        //addMenu.getItems().add(addM3UFile); //AA: Redundant
        addMenu.getItems().addAll(
                addMusicMedia,
                addFileDirectory,
                new SeparatorMenuItem(),
                addM3UFile
        );

        //AA: Find File's directory and identify system's OS
        MenuItem directoryFinder = new MenuItem("Find in " + System.getProperty("os.name") + " File Manager");
        directoryFinder.setOnAction((ActionEvent eventHandler) -> {
            try {
                Runtime.getRuntime().exec("explorer.exe /select," + table.getSelectionModel().getSelectedItem().getPath());
            } catch (IOException ex) {
                Logger.getLogger(MusicApplicationViewerFX.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        //mp3MenuBar.getMenus().add(addMenu); //AA: Redundant

        //AA: set menus (File & Add)
        mp3MenuBar.getMenus().addAll(fileMenu, addMenu);

        //AA: Copy File
        MenuItem copyFileDesc = new MenuItem("Copy");
        copyFileDesc.setOnAction((ActionEvent eventHandler) -> {
            MusicContextMenuAction.copyAction(new MusicMedia(table.getSelectionModel().getSelectedItem()));
        });

        //AA: Delete from Playlist
        MenuItem deleteFromMusicPlaylist = new MenuItem("Remove From Playlist");
        deleteFromMusicPlaylist.setOnAction((ActionEvent t) -> {
            ////AA: TO FIX - Method deletes entire table selection : EDIT FIXED 12/12/2016
            ////MusicMediaCollection removeStuff = new MusicMediaCollection();
            ////removeStuff.removeMusicMedia(table.getSelectionModel().getSelectedItem());
            ////data.clear();
            MusicMedia singleObject = table.getSelectionModel().getSelectedItem();
            data.remove(singleObject);
        });

        //AA: Quantone Decibel - Display album's made by selected artist
        MenuItem queryAlbum;
        queryAlbum = new MenuItem("Search Albums");
        queryAlbum.setOnAction((ActionEvent e) -> {
            MusicInfoStage.displayAlbumList(table.getSelectionModel().getSelectedItem().getArtist());
        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(
                copyFileDesc,
                deleteFromMusicPlaylist,
                new SeparatorMenuItem(),
                directoryFinder,
                new SeparatorMenuItem(),
                queryAlbum
        );

        vboxLayout = new VBox();
        table = new TableView<>();

        table.setOnMousePressed((MouseEvent event) -> {
            if (table.hasProperties()) {
                //event for mouse leftclick/primary button
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) { //AA: click count used to check if double clikc action is made
                    //System.out.println(table.getSelectionModel().getSelectedItem()); //AA: Test if primary button is clicked
                    directoryFinder.fire(); //AA: fire initialises a new action event
                }
                //AA: Event for mouse rightclick/secondary button
                if (event.isSecondaryButtonDown()) {
                    //System.out.println("Right click button has been pressed");
                    contextMenu.show(mainStage, event.getScreenX(), event.getScreenY());
                }
            }
        });

        table.setItems(data);
        table.setMinHeight(800);
        table.getColumns().addAll(
                MusicTableColumns.titleColumn(),
                MusicTableColumns.artistColumn(),
                MusicTableColumns.albumColumn(),
                MusicTableColumns.durationColumn(),
                MusicTableColumns.genreColumn(),
                MusicTableColumns.yearColumn(),
                MusicTableColumns.pathColumn()
        );

        Scene scene;
        vboxLayout.getChildren().addAll(
                mp3MenuBar,
                table
        );
        scene = new Scene(vboxLayout);
        mainStage.setScene(scene);
        mainStage.show();
    }

    /**
     *
     * @return MusicMedia List
     */
    public ObservableList<MusicMedia> retrieveData() {
        MusicMediaCollection observationCollection = new MusicMediaCollection();
        ObservableList<MusicMedia> products;
        products = FXCollections.observableArrayList(observationCollection.getMusic());
        return products;
    }
    


}
