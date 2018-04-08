package cm.demo.model;

import android.content.Context;

import java.util.Map;

public class VideoModel extends CMBaseModel {
    public static final int SEX_MASK = 0;
    public static final int SEX_ALL = SEX_MASK + 1;
    public static final int SEX_MALE = SEX_MASK + 2;
    public static final int SEX_FEMALE = SEX_MASK + 3;

    String video_url;
    String thumbnail_image_url;
    String title;
    int type;
    String intro;
    String time;
    int targetType;
    int sex;

    VideoModel(Context context) {
        super(context);
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getThumbnail_image_url() {
        return thumbnail_image_url;
    }

    public void setThumbnail_image_url(String thumbnail_image_url) {
        this.thumbnail_image_url = thumbnail_image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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
