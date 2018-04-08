package cm.demo.model;


import android.content.Context;

import java.util.Map;

public class QuestionModel extends CMBaseModel{
    public QuestionModel(Context context, String title, String content, boolean answer) {
        super(context);
        question_title = title;
        question_content = content;
        question_answer = answer;
    }

    public String getQuestion_title() {
        return question_title;
    }

    public void setQuestion_title(String question_title) {
        this.question_title = question_title;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public boolean Question_answer() {
        return question_answer;
    }

    public void setQuestion_answer(boolean question_answer) {
        this.question_answer = question_answer;
    }

    String question_title;
    String question_content;
    boolean question_answer;

    public boolean isAnswerCorrect(boolean user_answer) {
        return question_answer == user_answer;
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
