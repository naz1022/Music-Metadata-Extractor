package ku.piii.music;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.junit.Test;

import ku.piii.model.TagAndDurationStatistics;
import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import org.junit.Ignore;

//@Ignore
public class MusicMediaServiceImplStatsTest {

    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Test(expected = IllegalArgumentException.class)
    public void nullInputTest() {
        MUSIC_SERVICE.getTagAndDurationStats(null);
    }

    @Test
    public void totalCollectionLengthTest() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withLengthInSeconds(180).build());
        TagAndDurationStatistics metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalLengthInSeconds(), equalTo(180));

        musicCollection.addMusicMedia(new MusicMediaBuilder().withLengthInSeconds(200).build());
        metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalLengthInSeconds(), equalTo(380));
    }

    @Test
    public void canSumV1Items() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withId3Version(MusicMedia.Id3Version.V1).build());

        TagAndDurationStatistics metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalNumberOfV1Media(), equalTo(1));

        musicCollection.addMusicMedia(new MusicMediaBuilder().withId3Version(MusicMedia.Id3Version.V1).build());

        metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalNumberOfV1Media(), equalTo(2));
    }

    @Test
    public void canSumV2Items() {
        final MusicMediaCollection musicCollection = new MusicMediaCollection();
        musicCollection.addMusicMedia(new MusicMediaBuilder().withId3Version(MusicMedia.Id3Version.V2).build());

        TagAndDurationStatistics metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalNumberOfV2Media(), equalTo(1));

        musicCollection.addMusicMedia(new MusicMediaBuilder().withId3Version(MusicMedia.Id3Version.V2).build());

        metaDataStatistics = MUSIC_SERVICE.getTagAndDurationStats(musicCollection);
        assertThat(metaDataStatistics, notNullValue());
        assertThat(metaDataStatistics.getTotalNumberOfV2Media(), equalTo(2));
    }

}