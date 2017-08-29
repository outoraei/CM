package cm.demo.view.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import cm.demo.R;
import cm.demo.model.QuestionListModel;
import cm.demo.model.QuestionModel;
import cm.demo.util.BonusUtils;
import cm.demo.util.CMLog;
import cm.demo.util.QuestionListCreater;
import cm.demo.view.base.CMBaseActivity;

public class VideoPlayActivity extends CMBaseActivity {

    private QuestionListModel questionModelList;
    private int answer_correct_count = 0;
    private int question_num = 0;
    private QuestionModel currentModel;
    private Handler handler = new Handler();
    private ImageButton playImageButton = null;
    private ImageButton openBounsButton = null;
    private TextView playHintText = null;
    private TextView bonusDetail = null;
    private TextView bonusHint = null;
    private TextView openBounsHint = null;
    private ImageButton wallet = null;
    private VideoView videoView = null;

    private static String big_buck_bunny_path = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CMLog.LogD("onCreate ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

        videoView = (VideoView) findViewById(R.id.videoView);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                setMediaPlayCompomentState(false);
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                CMLog.LogD("onCompletion");
                startAskQuestion();
            }
        });
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                CMLog.LogD("action : " + action);
                if (MotionEvent.ACTION_DOWN == action) {
                    int visible = playImageButton.getVisibility();
                    setMediaPlayCompomentState(!(View.VISIBLE == visible));
                }
                return true;
            }
        });

        playImageButton = (ImageButton) findViewById(R.id.playImage);
        if (null != playImageButton) {
            playImageButton.setImageResource(R.drawable.gradient_bg);
            playImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videoView.isPlaying()) {
                        videoView.pause();
                        if (null != playHintText) {
                            playHintText.setText(R.string.stop);
                        }
                    } else {
                        videoView.start();
                        if (null != playHintText) {
                            playHintText.setText(R.string.play);
                        }
                    }
                }
            });
        }

        playHintText = (TextView) findViewById(R.id.playHint);
        bonusDetail = (TextView) findViewById(R.id.bonusDetail);
        if (null != bonusDetail) {
            bonusDetail.setVisibility(View.GONE);
        }
        bonusHint = (TextView) findViewById(R.id.bonusHint);
        if (null != bonusHint) {
            bonusHint.setVisibility(View.GONE);
        }
        openBounsButton = (ImageButton) findViewById(R.id.openBouns);
        if (null != openBounsButton) {
            openBounsButton.setVisibility(View.GONE);
        }
        openBounsHint = (TextView) findViewById(R.id.openBounsHint);
        if (null != openBounsHint) {
            openBounsHint.setVisibility(View.GONE);
        }
        wallet = (ImageButton) findViewById(R.id.wallet);
        if (null != wallet) {
            wallet.setVisibility(View.GONE);
        }

        questionModelList = QuestionListCreater.getQuestionListFromXML(this);
        handler.post(vieoRunnable);
    }

    private Runnable vieoRunnable = new Runnable() {
        @Override
        public void run() {
            if (videoView != null) {
                videoView.setVideoURI(Uri.parse(big_buck_bunny_path));
            }
        }
    };

    private Runnable autoHideCompoment = new Runnable() {
        @Override
        public void run() {
            setMediaPlayCompomentState(false);
        }
    };

    private void setMediaPlayCompomentState(boolean display) {
        if (null != playImageButton && null != playHintText) {
            playImageButton.setVisibility(display ? View.VISIBLE : View.GONE);
            playHintText.setVisibility(display ? View.VISIBLE : View.GONE);
        }
        if (display) {
            handler.postDelayed(autoHideCompoment, 2000);
        } else {
            handler.removeCallbacks(autoHideCompoment);
        }
    }

    private void startAskQuestion() {
        answer_correct_count = 0;
        question_num = questionModelList.getQuestionList().size();
        if (needToAskQuestion()) {
            showQuestionDailog(questionModelList.getQuestionList().get(answer_correct_count));
        }
    }

    private boolean needToAskQuestion() {
        if (question_num == answer_correct_count) {
            return false;
        }
        return true;
    }

    private void showNextQuestionDialog(boolean user_answer) {
        if (currentModel.isAnswerCorrect(user_answer)) {
            answer_correct_count++;
            if (needToAskQuestion()) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showQuestionDailog(questionModelList.getQuestionList().get(answer_correct_count));
                    }
                }, 100);
            } else {
                // show bonus hint and button
                if (null != openBounsButton) {
                    openBounsButton.setVisibility(View.VISIBLE);
                }
                if (null != openBounsHint) {
                    openBounsHint.setVisibility(View.VISIBLE);
                }

                // hide play hint and button
                if (null != playImageButton) {
                    playImageButton.setVisibility(View.GONE);
                }
                if (null != playHintText) {
                    playHintText.setVisibility(View.GONE);
                }
                Toast.makeText(VideoPlayActivity.this, R.string.answer_correct_congratulation, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(VideoPlayActivity.this, R.string.answer_incorrect, Toast.LENGTH_SHORT).show();
            showRetryDialog();
        }
    }

    private void showQuestionDailog(QuestionModel model) {
        currentModel = model;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(model.getQuestion_title());
        builder.setMessage(model.getQuestion_content());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(getString(R.string.button_right), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showNextQuestionDialog(true);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.button_wrong), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showNextQuestionDialog(false);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showRetryDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.retry);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton(getString(R.string.button_right), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (videoView != null) {
                    videoView.setVideoURI(Uri.parse(big_buck_bunny_path));
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.button_wrong), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                exitPlayActivity();
            }
        });
        builder.create().show();
    }

    private void exitPlayActivity() {
        finish();
    }

    public void openBonus(View view) {
        if (null != openBounsButton) {
            openBounsButton.setVisibility(View.GONE);
        }
        if (null != openBounsHint) {
            openBounsHint.setVisibility(View.GONE);
        }
        if (null != bonusDetail) {
            bonusDetail.setVisibility(View.VISIBLE);
            String detail = getString(R.string.bonus_detail);
            String bonus = BonusUtils.getRandomBonus();
            CMLog.LogD("getRandomBonus " + bonus);
            detail = detail.replace("$$$", bonus);
            CMLog.LogD("detail " + detail);
            bonusDetail.setText(detail);
            saveMoney(bonus);
        }
        if (null != bonusHint) {
            bonusHint.setVisibility(View.VISIBLE);
            bonusHint.setText(R.string.bonus_hint);
        }
        if (null != wallet) {
            wallet.setVisibility(View.VISIBLE);
        }
    }

    public void goToWalletScreen(View view) {
        CMLog.LogD("goToWalletScreen");
        Intent intent = new Intent();
        intent.setClass(this, WalletActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveMoney(String money) {
        float s = Float.parseFloat(money);
        CMLog.LogD("earn " + s);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(vieoRunnable);
        super.onDestroy();
    }
}
