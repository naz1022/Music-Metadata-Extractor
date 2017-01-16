package ku.piii.music;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Lists;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import org.junit.Ignore;

//@Ignore
public class MusicMediaServiceImplSearchTest {

    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Test
    public void nullStringInputTest() {

        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().build());
        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, null);

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullCollectionTest() {
        MUSIC_SERVICE.searchByTitle(null, "search text");
    }

    @Test
    public void exactMatchTest() {

        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Crazy").build());
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Angel").build());

        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, "Crazy");

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(1));
        assertThat(searchResults.getMusic().get(0).getTitle(), equalTo("Crazy"));
    }

    @Test
    public void variedCaseTest() {

        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("crazy").build());
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Angel").build());

        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, "Crazy");

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(1));
        assertThat(searchResults.getMusic().get(0).getTitle(), equalTo("crazy"));
    }

    @Test
    public void queryIsFragmentTest() {

        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("crazy").build());
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("I Will Always Love You").build());

        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, "Love");

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(1));
        assertThat(searchResults.getMusic().get(0).getTitle(), equalTo("I Will Always Love You"));
    }

    @Test
    public void multipleReturnTest() {

        final MusicMedia iWillAlwaysLoveYou = new MusicMediaBuilder().withTitle("I Will Always Love You").build();
        final MusicMedia loveMeDo = new MusicMediaBuilder().withTitle("Love Me Do").build();

        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("crazy").build());
        musicCollection.addMusicMedia(iWillAlwaysLoveYou);
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Because of You").build());
        musicCollection.addMusicMedia(loveMeDo);

        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, "love");

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(2));

        final List<MusicMediaEquality> expectedMusic = Lists.newArrayList(new MusicMediaEquality(iWillAlwaysLoveYou),
                new MusicMediaEquality(loveMeDo));

        final List<MusicMediaEquality> musicForEqualityCheck = searchResults.getMusic().stream()
                .map(MusicMediaEquality::new).collect(Collectors.toList());

        assertThat(musicForEqualityCheck, containsInAnyOrder(expectedMusic.toArray()));
    }

    @Test
    public void nullInTitleTest() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Crazy").build());
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle(null).build());
        musicCollection.addMusicMedia(new MusicMediaBuilder().withTitle("Angel").build());

        final MusicMediaCollection searchResults = MUSIC_SERVICE
                .searchByTitle(musicCollection, "Crazy");

        assertThat(searchResults, notNullValue());
        assertThat(searchResults.getMusic(), notNullValue());
        assertThat(searchResults.getMusic(), hasSize(1));
        assertThat(searchResults.getMusic().get(0).getTitle(), equalTo("Crazy"));
    }

}