package kders.app.audioplayer.model;

/**
 * Created by User on 10/8/2017.
 */

public class Option {
    private String type;

    public Option() {
    }

    public Option(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String name) {
        this.type = name;
    }
}
