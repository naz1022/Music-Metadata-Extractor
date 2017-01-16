package ku.piii.music;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.not;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hamcrest.Matchers;
import org.junit.Test;

import ku.piii.model.MusicMedia;
import ku.piii.model.MusicMediaCollection;
import org.junit.Ignore;




public class MusicServiceImplHamcrestTest {

    private final static MusicService MUSIC_SERVICE = MusicServiceFactory.getMusicServiceInstance();

    @Test
    public void canCreateMusicCollectionFromPath() {
        final MusicMediaCollection collectionA = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get("../test-music-files/collection-A"));

        assertEquals(9, collectionA.getMusic().size());

        final MusicMediaCollection collectionB = MUSIC_SERVICE
                .createMusicMediaCollection(Paths.get("../test-music-files/collection-B"));

        final MusicMediaCollection merge = collectionA.mergeCollection(collectionB);
        assertEquals(18, merge.getMusic().size());
    }

    @Test
    public void canAddFilesThenSaveThenLoad() {

        final String pathToAddFrom = "../test-music-files/Collection-B";
        final String jsonFileToSaveToAndLoadFrom = "../test-json-files/tmp3.json";

        final MusicMediaCollection collection = MUSIC_SERVICE.createMusicMediaCollection(Paths.get(pathToAddFrom));

        assertThat(collection, Matchers.notNullValue());

        MUSIC_SERVICE.saveMusicMediaCollection(get(jsonFileToSaveToAndLoadFrom), collection);
        final MusicMediaCollection savedCollection = MUSIC_SERVICE
                .loadMusicMediaCollection(get(jsonFileToSaveToAndLoadFrom));

        assertEquals(collection.getMusic().size(), savedCollection.getMusic().size());
        assertThat(savedCollection, Matchers.notNullValue());
        assertThat(savedCollection.getMusic(), Matchers.hasSize(collection.getMusic().size()));

        final List<MusicMediaEquality> expectedMusic = collection.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        final List<MusicMediaEquality> actualMusic = savedCollection.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        assertThat(actualMusic, containsInAnyOrder(expectedMusic.toArray()));
    }

    @Test
    public void canLoadThenSaveThenLoad() {
        final String jsonFileToLoadFrom = "../test-json-files/Collection-B.json";
        final String jsonFileToSaveTo = "../test-json-files/tmp4.json";

        final MusicMediaCollection collectionA = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileToLoadFrom));
        MUSIC_SERVICE.saveMusicMediaCollection(get(jsonFileToSaveTo), collectionA);

        final MusicMediaCollection copyOfCollectionA = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileToSaveTo));

        final List<MusicMediaEquality> expectedMusic = collectionA.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        final List<MusicMediaEquality> actualMusic = copyOfCollectionA.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        assertThat(actualMusic, containsInAnyOrder(expectedMusic.toArray()));
    }
    
    @Test
    public void canDetectMissingArtistInJsonFile() {
        final String jsonFileWithArtist = "../test-json-files/Collection-B.json";
        final String jsonFileWithNoArtist = "../test-json-files/Collection-B-no-artist.json";
        final MusicMediaCollection collectionWithArtist
                = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileWithArtist));
        final MusicMediaCollection collectionWithNoArtist
                = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileWithNoArtist));

        final List<MusicMediaEquality> expectedMusic = collectionWithArtist.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        final List<MusicMediaEquality> actualMusic = collectionWithNoArtist.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        assertThat(actualMusic.get(0), not(expectedMusic.get(0)));
    }
    
    @Test
    public void canDetectMissingAlbumInJsonFile() {
        final String jsonFileWithAlbum = "../test-json-files/Collection-B.json";
        final String jsonFileWithNoAlbum = "../test-json-files/Collection-B-no-album.json";
        final MusicMediaCollection collectionWithAlbum
                = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileWithAlbum));
        final MusicMediaCollection collectionWithNoAlbum
                = MUSIC_SERVICE.loadMusicMediaCollection(get(jsonFileWithNoAlbum));

        final List<MusicMediaEquality> expectedMusic = collectionWithAlbum.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        final List<MusicMediaEquality> actualMusic = collectionWithNoAlbum.getMusic().stream().map(MusicMediaEquality::new)
                .collect(Collectors.toList());

        assertThat(actualMusic.get(2), not(expectedMusic.get(2)));
    }

    public static class MusicMediaEquality {

        public final MusicMedia musicMedia;

        public MusicMediaEquality(final MusicMedia musicMedia) {
            this.musicMedia = musicMedia;
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(musicMedia.getPath())
                    .append(musicMedia.getArtist())
                    .append(musicMedia.getTitle())
                    .append(musicMedia.getGenre())
                    .append(musicMedia.getAlbum())
                    .append(musicMedia.getYear())
                    .hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            final MusicMediaEquality other = (MusicMediaEquality) obj;
            return new EqualsBuilder()
                    .append(musicMedia.getPath(), other.musicMedia.getPath())
                    .append(musicMedia.getArtist(), other.musicMedia.getArtist())
                    .append(musicMedia.getTitle(), other.musicMedia.getTitle())
                    .append(musicMedia.getGenre(), other.musicMedia.getGenre())
                    .append(musicMedia.getAlbum(), other.musicMedia.getAlbum())
                    .append(musicMedia.getYear(), other.musicMedia.getYear()).isEquals();
        }

        @Override
        public String toString() {
            return ToStringBuilder.reflectionToString(musicMedia);
        }
    }
}
