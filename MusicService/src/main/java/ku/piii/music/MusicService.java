package ku.piii.music;

import java.nio.file.Path;
import java.time.Year;
import ku.piii.model.GenreFilledStatistics;
import ku.piii.model.MusicMediaCollection;
import ku.piii.model.TagAndDurationStatistics;

public interface MusicService {

    public MusicMediaCollection createMusicMediaCollection(Path root);

    public void saveMusicMediaCollection(Path fileToSave, MusicMediaCollection collection);

    public MusicMediaCollection loadMusicMediaCollection(Path fileToLoad);

    public MusicMediaCollection searchByTitle(MusicMediaCollection collection, String query);

    public TagAndDurationStatistics getTagAndDurationStats(final MusicMediaCollection collection);

    public MusicMediaCollection findAllMusicReleasedInYear(final MusicMediaCollection collection,
            final Year year);
    
    public GenreFilledStatistics getGenreFilledStats(MusicMediaCollection collection);
    
 

}
