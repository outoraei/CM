package cm.demo.view.video;

public class VideoLayer {

    private VideoState currentVideoState = VideoState.Unknown;

    enum VideoState {
        Unknown, Init, Ready, Start, Stop, FINISH
    }

    private VideoLayer() {
    }

    private static VideoLayer instance = new VideoLayer();

    public static VideoLayer getInstance() {
        return instance;
    }

    public VideoState getVideoState() {
        return VideoState.Unknown;
    }

    private void setVideoState(VideoState state) {
        currentVideoState = state;
    }

    public void initVideoLayer() {
        setVideoState(VideoState.Init);
    }
}
