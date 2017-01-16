package ku.piii.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TagAndDurationStatistics {

    private final List<MusicMedia> music = new CopyOnWriteArrayList<>();
    
    
            
    private int totalLengthInSeconds = 0;
    private int totalNumberOfV1Media = 0;
    private int totalNumberOfV2Media = 0;

    public Integer getTotalLengthInSeconds() {
        return totalLengthInSeconds;
    }

    public void addToTotallengthInSeconds(final Integer anotherLengthInSeconds) {
        this.totalLengthInSeconds += anotherLengthInSeconds;
    }

    public Integer getTotalNumberOfV1Media() {
        return totalNumberOfV1Media;
    }

    public void incrementTotalNumberOfV1Media() {
        this.totalNumberOfV1Media++;
    }

    public Integer getTotalNumberOfV2Media() {
        return this.totalNumberOfV2Media;
    }

    public void incrementTotalNumberOfV2Media() {
        this.totalNumberOfV2Media++;
    }

    public TagAndDurationStatistics merge(final TagAndDurationStatistics m2) {
        throw new UnsupportedOperationException();
        //return m2;
    }
}
