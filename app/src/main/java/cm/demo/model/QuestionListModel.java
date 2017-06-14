package cm.demo.model;


import java.util.ArrayList;
import java.util.List;

public class QuestionListModel {

    public static String ANSWER_TRUE = "true";

    List<QuestionModel> questionModelList;

    public QuestionListModel() {
        questionModelList = new ArrayList<QuestionModel>();
    }

    public void loadQuestion() {

    }

    public List<QuestionModel> getQuestionList() {
        return questionModelList;
    }

    public void addItem(String title, String content, String answer) {
        questionModelList.add(new QuestionModel(title, content, answer.contains(ANSWER_TRUE)));
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (QuestionModel item : questionModelList) {
            sb.append("title : " + item.getQuestion_title());
            sb.append("content : " + item.getQuestion_content());
            sb.append("answer : " + item.Question_answer());
        }
        return sb.toString();
    }
}
