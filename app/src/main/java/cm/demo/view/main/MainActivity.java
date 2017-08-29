package cm.demo.view.main;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.widget.EditText;

import cm.demo.R;
import cm.demo.util.CMLog;
import cm.demo.util.CMNavigateCreater;
import cm.demo.view.base.CMBaseActivity;
import cm.demo.view.base.CMFragmentManager;
import io.vov.vitamio.widget.VideoView;

public class MainActivity extends CMBaseActivity {

    private VideoView m_videoView;
    private EditText m_input;
    private String path;
    GestureDetector detector;
//    private static String big_buck_bunny_path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
//    private static String big_buck_bunny_path = "http://baidu.ku6.com/watch/04276583939511319049.html?page=videoMultiNeed";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        CMLog.LogD("CMNavigateCreater Init");
        CMNavigateCreater.getNavigateFromCode(this);
        CMFragmentManager.getInstatnce().initMainActivityFragment(this);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            CMLog.LogD("KEYCODE_BACK");
            this.finish();
            System.exit(0);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
