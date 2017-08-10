package cm.demo.view.main;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

        playImageButton = (ImageButton) findViewById(R.id.playImage);
        if (null != playImageButton) {
            playImageButton.setImageResource(R.drawable.gradient_bg);
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
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startAskQuestion();
            }
        }, 2000);
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

    }

    private void saveMoney(String money) {
        float s = Float.parseFloat(money);
        CMLog.LogD("earn " + s);
    }
}
