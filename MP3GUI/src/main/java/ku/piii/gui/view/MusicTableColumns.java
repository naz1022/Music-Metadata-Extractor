/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ku.piii.gui.view;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import ku.piii.model.MusicMedia;

/**
 *
 * @author K1302575
 */
public class MusicTableColumns {

    /**
     *
     * @param 
     * @return artistColumn
     */
    public static TableColumn<MusicMedia, String> artistColumn() {
        TableColumn<MusicMedia, String> artistColumn;
        artistColumn = new TableColumn<>("Artist");
        artistColumn.setMinWidth(200);
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        return artistColumn;
    }


    /**
     *
     * @return albumColumn
     */
    public static TableColumn<MusicMedia, String> albumColumn() {
        TableColumn<MusicMedia, String> albumColumn;
        albumColumn = new TableColumn<>("Album");
        albumColumn.setMinWidth(200);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));
        return albumColumn;
    }

    /**
     *
     * @param durationColumn
     */
    public static TableColumn<MusicMedia, String> durationColumn() {
        TableColumn<MusicMedia, String> durationColumn;
        durationColumn = new TableColumn<>("Duration");
        durationColumn.setMinWidth(200);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("lengthInHMSFormat"));
        return durationColumn;
    }

    /**
     *
     * @param genreColumn
     */
    public static TableColumn<MusicMedia, String> genreColumn() {
        TableColumn<MusicMedia, String> genreColumn;
        genreColumn = new TableColumn<>("Genre");
        genreColumn.setMinWidth(200);
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        return genreColumn;
    }


    /**
     *
     * @param pathColumn
     */
    public static TableColumn<MusicMedia, String>  pathColumn() {
        TableColumn<MusicMedia, String> pathColumn;
        pathColumn = new TableColumn<>("Path");
        pathColumn.setMinWidth(200);
        pathColumn.setCellValueFactory(new PropertyValueFactory<>("path"));
        return pathColumn;
    }


    /**
     *
     * @param titleColumn
     */
    public static TableColumn<MusicMedia, String> titleColumn() {
        TableColumn<MusicMedia, String> titleColumn;
        titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        return titleColumn;
    }

    /**
     *

     * @return 
     */
    public static TableColumn<MusicMedia, String>  yearColumn() {
        TableColumn<MusicMedia, String> yearColumn;
        yearColumn = new TableColumn<>("Year");
        yearColumn.setMinWidth(200);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        return yearColumn;
    }


}
