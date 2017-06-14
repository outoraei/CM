package cm.demo.model;


public class CMInfoModel extends CMBaseModel {

	private int id;
	private String title;
	private String creator;
	private String duration;
	private String thumb_image_uri;
	private String video_uri;

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
}
