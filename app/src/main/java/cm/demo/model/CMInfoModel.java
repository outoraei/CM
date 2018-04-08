package cm.demo.model;


import android.content.Context;

import java.util.Map;

public class CMInfoModel extends CMBaseModel {

    private int id;
    private String title;
    private String creator;
    private String duration;
    private String thumb_image_uri;
    private String video_uri;
    private String tag1;
    private String tag2;
    private String tag3;

    public CMInfoModel(Context context) {
        super(context);
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getThumb_image_uri() {
        return thumb_image_uri;
    }

    public void setThumb_image_uri(String thumb_image_uri) {
        this.thumb_image_uri = thumb_image_uri;
    }

    public String getVideo_uri() {
        return video_uri;
    }

    public void setVideo_uri(String video_uri) {
        this.video_uri = video_uri;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creater) {
        this.creator = creator;
    }

    @Override
    protected String makeStrRequestURL() {
        return null;
    }

    @Override
    protected Map<String, String> makeMapRequestURL() {
        return null;
    }
}
