package kders.app.audioplayer;

/**
 * Created by User on 10/8/2017.
 */

public class Track {
    private String name,duration,singer;

    public Track() {
    }

    public Track(String name,String singer, String duration) {
        this.name = name;
        this.duration = duration;
        this.singer = singer;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSinger() {
        return singer;
    }
    public void setSinger(String singer) {
        this.singer = singer;
    }
}
