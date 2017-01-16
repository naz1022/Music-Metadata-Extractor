package ku.piii.music;

import com.mpatric.mp3agic.InvalidDataException;
import java.io.File;
import static java.util.Optional.ofNullable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle.Control;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import ku.piii.model.GenreFilledStatistics;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import ku.piii.model.TagAndDurationStatistics;
import ku.piii.mp3.MP3PathToMusicMapper;
import ku.piii.nio.file.SimpleMp3FileVisitor;

public class MusicServiceImpl implements MusicService {

    private final MusicRepository musicRepository;
    private final MP3PathToMusicMapper mapper;

    @Override
    public MusicMediaCollection loadMusicMediaCollection(final Path fileToLoad) {
        return musicRepository.loadCollection(fileToLoad);
    }

    @Override
    public void saveMusicMediaCollection(final Path fileToSave, final MusicMediaCollection collection) {
        musicRepository.saveCollection(fileToSave, collection);
    }

    public MusicServiceImpl(final MusicRepository musicRepository, MP3PathToMusicMapper myMapper) {
        this.musicRepository = musicRepository;
        this.mapper = myMapper;
    }

    @Override
    public MusicMediaCollection createMusicMediaCollection(final Path root) {
        SimpleMp3FileVisitor myVisitor = new SimpleMp3FileVisitor();

        try {
            Files.walkFileTree(root, myVisitor);
        } catch (IOException ex) {
            Logger.getLogger(MusicServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        final MusicMediaCollection collection = new MusicMediaCollection();
        for (Path p : myVisitor.getListOfMP3Files()) {
            collection.addMusicMedia(mapper.mapPath(p));
        }

        return collection;
    }

    //AA: PAPER 1
    @Override
    public MusicMediaCollection searchByTitle(MusicMediaCollection collection, String searchText) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Optional.ofNullable(collection)
                .map(Function.identity())
                .orElseThrow(IllegalArgumentException::new);

        /* //AA: PRE JAVA 8 METHOD
         if (collection == null) {
            throw new IllegalArgumentException();
        }*/
        if (searchText == null) {
            return new MusicMediaCollection();
        }

        //PRE JAVA 8 METHOD
        /*
          MusicMediaCollection newCollection = new MusicMediaCollection();
        for (MusicMedia media : collection.getMusic()) {
            if (media.getTitle() != null && media.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                newCollection.addMusicMedia(media);
            }
        }
         */
        //JAVA 8 METHOD
        MusicMediaCollection newCollection = new MusicMediaCollection();
        collection.getMusic().stream().filter((media) -> (media.getTitle() != null && media.getTitle().toLowerCase().contains(searchText.toLowerCase()))).forEach((media) -> {
            newCollection.addMusicMedia(media);
        });
        return newCollection;

    }

    //AA: PAPER 3
    @Override
    public MusicMediaCollection findAllMusicReleasedInYear(final MusicMediaCollection collection,
            final Year year) {
        //Nullpointer Handler(s) for MusicMediaCollection
        //AA: JAVA 8 METHOD 
        Optional.ofNullable(collection)
                .map(Function.identity())
                .orElseThrow(IllegalArgumentException::new);

        //AA: PRE JAVA 8 METHOD
        /*
        if(collection == null){
            throw new IllegalArgumentException();
        }*/
        //return new MusicMediaCollection if year object equals null
        //AA: JAVA 8 METHOD
        //TO DO....
        //AA: PRE JAVA 8 METHOD
        if (year == null) {
            return new MusicMediaCollection();
        }

        //Iterate through collection and add music media to a new collection
        MusicMediaCollection newCollection = new MusicMediaCollection();
        /* //AA: PRE JAVA 8 METHOD
        for (MusicMedia media : collection.getMusic()) {
            if ((media.getYear() != null)
                    && (media.getYear().contains(year.toString()))){
                newCollection.addMusicMedia(media);
            }
        }*/

        collection.getMusic().stream().filter(p -> Objects.nonNull(p.getYear()))
                .filter(p -> year.equals(Year.of(Integer.parseInt(p.getYear()))))
                //.filter(p -> p.getYear().toString().equals(year.toString())) //AA: DEPRECATED
                .forEach(p -> newCollection.addMusicMedia(p));
        return newCollection;

    }

    //AA: PAPER 4
    @Override
    public TagAndDurationStatistics getTagAndDurationStats(MusicMediaCollection collection) {
        //throw new UnsupportedOperationException("not yet written"); //AA: Operation Exception not thrown

        if (collection == null) {
            throw new IllegalArgumentException();
        }

        TagAndDurationStatistics myID3;
        myID3 = new TagAndDurationStatistics();
        for (MusicMedia x : collection.getMusic()) {

            if (x.getLengthInSeconds() != null) {

                myID3.addToTotallengthInSeconds(x.getLengthInSeconds());
            }
            if (x.getId3Version() != null) {
                if (x.getId3Version() == MusicMedia.Id3Version.V1) {
                    /*
                    System.out.println("no if statement issue");//AA: CHECK STATEMENT IS REACHABLE
                    System.out.println("there is " + myID3.getTotalNumberOfV1Media()
                            + " " + x.getId3Version().V1 + "tags"); //AA: Check ID3V1 tags
                     */
                    myID3.incrementTotalNumberOfV1Media(); //AA: Increment V1 int

                }
                if (x.getId3Version() == MusicMedia.Id3Version.V2) {
                    /*
                    System.out.println("no if statement issue");//AA: CHECK STATEMENT IS REACHABLE
                    System.out.println("there is " + myID3.getTotalNumberOfV2Media()
                            + " " + x.getId3Version().V2 + "tags");
                     */
                    myID3.incrementTotalNumberOfV2Media(); // AA: Increment V2 int}
                }
            }
        }
        return myID3;

    }

    @Override
    public GenreFilledStatistics getGenreFilledStats(MusicMediaCollection collection) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        GenreFilledStatistics abc = new GenreFilledStatistics();

        if (collection != null) {
            for (MusicMedia m : collection.getMusic()) {
                if (m.getGenre() != null) {
                    abc.addItemWithGenrePresent();
                } else {
                    abc.addItemWithNoGenrePresent();
                }
            }
            abc.getPercentItemsNeedingGenre();
            return abc;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
