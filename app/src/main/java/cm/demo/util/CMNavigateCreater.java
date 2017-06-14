package cm.demo.util;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.content.Context;
import android.content.res.XmlResourceParser;
import cm.demo.R;
import cm.demo.model.NavigateModel;
import cm.demo.model.NavigateModel.NAVIGATE_LEVEL;

public class CMNavigateCreater {

	static String LEVEL1 = "level1";
	static String LEVEL2 = "level2";
	static String ITEM = "item";
	static String CODE = "code";
	static String CONTENT = "content";
	static int ATTRIBUTE_MUST_COUNT = 3;

	public static void getNavigateFromCode(Context context) {
		NavigateModel model = new NavigateModel();
		NAVIGATE_LEVEL type = NAVIGATE_LEVEL.LEVEL1;
		String code = null;
		String content = null;
		int attrbuteCount = 0;
		try {
			XmlResourceParser xpp = context.getResources().getXml(
					R.xml.navigate);

			int eventType = xpp.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_DOCUMENT) {
					model.init();
					eventType = xpp.next();
				}
				if (eventType == XmlPullParser.START_TAG) {
					String name = xpp.getName();
					if (LEVEL1.equals(name)) {
						type = NAVIGATE_LEVEL.LEVEL1;
					} else if (LEVEL2.equals(name)) {
						type = NAVIGATE_LEVEL.LEVEL2;
					} else if (ITEM.equals(name)) {
						type = NAVIGATE_LEVEL.ITEM;
					} else if (CODE.equals(name)) {
						xpp.next();
						code = xpp.getText();
					} else if (CONTENT.equals(name)) {
						xpp.next();
						content = xpp.getText();
					} else {
						attrbuteCount--;
					}
					attrbuteCount++;
					if (ATTRIBUTE_MUST_COUNT == attrbuteCount) {
						model.setNavigateModel(type, code, content);
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

		return;
	}
}
