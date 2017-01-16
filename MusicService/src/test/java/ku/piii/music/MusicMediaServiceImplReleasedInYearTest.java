package ku.piii.music;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import org.junit.Ignore;

//@Ignore
public class MusicMediaServiceImplReleasedInYearTest {

    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Test
    public void nullStringInputTest() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().build());
        final MusicMediaCollection searchResults
                = MUSIC_SERVICE.findAllMusicReleasedInYear(musicCollection, null);

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCollectionInputTest() {
        MUSIC_SERVICE.findAllMusicReleasedInYear(null, Year.of(1999));
    }

    @Test
    public void oneItemRetrievedTest() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Crazy").withYear("2006").build());
        final MusicMediaCollection searchResults = MUSIC_SERVICE.findAllMusicReleasedInYear(musicCollection,
                Year.of(2006));

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(1));
        assertThat(searchResults.getMusic().get(0).getTitle(), equalTo("Crazy"));
        assertThat(searchResults.getMusic().get(0).getYear(), equalTo("2006"));
    }

    @Test
    public void multipleItemsRetrievedTest() {
        final MusicMedia crazy = new MusicMediaBuilder().withTitle("Crazy").withYear("2006").build();
        final MusicMedia becauseOfYou = new MusicMediaBuilder().withTitle("Because of You").withYear("2006").build();
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(crazy);
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Black Or White").withYear("1991").build());
        musicCollection.addMusicMedia(becauseOfYou);
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Back For Good").withYear("1995").build());
        final MusicMediaCollection searchResults = MUSIC_SERVICE.findAllMusicReleasedInYear(musicCollection,
                Year.of(2006));

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(2));

        final List<MusicMediaEquality> expectedMusic = Lists.newArrayList(new MusicMediaEquality(crazy),
                new MusicMediaEquality(becauseOfYou));

        final List<MusicMediaEquality> musicForEqualityCheck = searchResults.getMusic().stream()
                .map(MusicMediaEquality::new).collect(Collectors.toList());

        assertThat(musicForEqualityCheck, containsInAnyOrder(expectedMusic.toArray()));
    }

    @Test
    public void nullYearInCollectionTest() {
        final MusicMedia crazy = new MusicMediaBuilder().withTitle("Crazy").withYear("2006").build();
        final MusicMedia becauseOfYou = new MusicMediaBuilder().withTitle("Because of You").withYear("2006").build();
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(crazy);
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Black Or White").withYear(null).build());
        musicCollection.addMusicMedia(becauseOfYou);
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Back For Good").withYear("1995").build());
        final MusicMediaCollection searchResults = MUSIC_SERVICE.findAllMusicReleasedInYear(musicCollection,
                Year.of(2006));

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(2));

        final List<MusicMediaEquality> expectedMusic = Lists.newArrayList(new MusicMediaEquality(crazy),
                new MusicMediaEquality(becauseOfYou));

        final List<MusicMediaEquality> musicForEqualityCheck = searchResults.getMusic().stream()
                .map(MusicMediaEquality::new).collect(Collectors.toList());

        assertThat(musicForEqualityCheck, containsInAnyOrder(expectedMusic.toArray()));
    }
}