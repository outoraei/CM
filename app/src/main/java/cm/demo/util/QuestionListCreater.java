package cm.demo.util;

import android.content.Context;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import cm.demo.R;
import cm.demo.model.QuestionListModel;

public class QuestionListCreater {

	public static String TITLE = "title";
	public static String CONTENT = "content";
	public static String ANSWER = "answer";
	public static int ATTRIBUTE_MUST_COUNT = 3;

	public static QuestionListModel getQuestionListFromXML(Context context) {
		QuestionListModel model = new QuestionListModel();
		String title = null;
		String content = null;
		String answer = null;
		int attrbuteCount = 0;

		try {
			XmlResourceParser xpp = context.getResources().getXml(
					R.xml.question);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					eventType = xpp.next();

				}
				if (eventType == XmlPullParser.START_TAG) {
					String name = xpp.getName();
					if (TITLE.equals(name)) {
						xpp.next();
						title = xpp.getText();
					} else if (CONTENT.equals(name)) {
						xpp.next();
						content = xpp.getText();
					} else if (ANSWER.equals(name)) {
						xpp.next();
						answer = xpp.getText();
					} else {
						attrbuteCount--;
					}
					attrbuteCount++;
					if (ATTRIBUTE_MUST_COUNT == attrbuteCount) {
						model.addItem(title, content, answer);
						attrbuteCount = 0;
					}
				}
				eventType = xpp.next();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}

		CMLog.LogD(model.toString());

		return model;
	}
}
