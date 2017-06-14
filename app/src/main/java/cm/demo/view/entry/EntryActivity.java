package cm.demo.view.entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import cm.demo.R;
import cm.demo.util.CMLog;
import cm.demo.view.base.EntryFragmentManager;
import cm.demo.view.main.MainActivity;

public class EntryActivity extends Activity implements GestureDetector.OnGestureListener {

    public static GestureDetector detector;
    private static final int DISTANCE = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
        EntryFragmentManager.getInstatnce().initEntryActivityFragment(this);
        EntryFragmentManager.getInstatnce().flingEntryFragments(this, EntryFragmentManager.EntryFragmentType.ENTRYFRAGMENT_INTRO_ONE);
        detector = new GestureDetector(this, this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return detector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent arg0) {
        CMLog.LogD("onDown");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
                           float arg3) {
        CMLog.LogD("onFling");
        CMLog.LogD("arg1.getX() : " + arg1.getX() + " , arg0.getX() : " + arg0.getX());

        if ((arg0.getX() - arg1.getX()) > DISTANCE) {
            EntryFragmentManager.getInstatnce().flingNextFragment(this);
        } else if ((arg1.getX() - arg0.getX()) > DISTANCE) {
            EntryFragmentManager.getInstatnce().flingPreFragment(this);
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent arg0) {
        CMLog.LogD("onLongPress");

    }

    @Override
    public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
                            float arg3) {
//        CMLog.LogD("onScroll");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent arg0) {
        CMLog.LogD("onShowPress");

    }

    @Override
    public boolean onSingleTapUp(MotionEvent arg0) {
        CMLog.LogD("onSingleTapUp");
        return false;
    }

    public void gotoMainScreen(View view) {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
