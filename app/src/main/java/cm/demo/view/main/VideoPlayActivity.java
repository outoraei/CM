package cm.demo.view.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import cm.demo.R;
import cm.demo.model.QuestionListModel;
import cm.demo.model.QuestionModel;
import cm.demo.util.QuestionListCreater;

public class VideoPlayActivity extends Activity {

    QuestionListModel questionModelList;
    private int answer_correct_count = 0;
    private int question_num = 0;
    QuestionModel currentModel;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_play);

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
        if(needToAskQuestion()) {
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
            if(needToAskQuestion()) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showQuestionDailog(questionModelList.getQuestionList().get(answer_correct_count));
                    }
                }, 100);
            } else {
                Toast.makeText(VideoPlayActivity.this, R.string.answer_correct_congratulation, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(VideoPlayActivity.this, R.string.answer_incorrect, Toast.LENGTH_SHORT).show();
        }
    }

    private void showQuestionDailog(QuestionModel model){
        currentModel = model;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(model.getQuestion_title());
        builder.setMessage(model.getQuestion_content());
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("right", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showNextQuestionDialog(true);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("wrong", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showNextQuestionDialog(false);
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
}
