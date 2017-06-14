package cm.demo.model;

import java.util.ArrayList;
import java.util.List;

public class NavigateModel {

	List<Node> navigateList;
	Node level1;
	Node level2;
	Node item;

	public enum NAVIGATE_LEVEL {
		LEVEL1, LEVEL2, ITEM
	}

	class Node {
		NAVIGATE_LEVEL type;
		String code;
		String content;
		List<Node> nodeList;
	}

	public void init() {
		navigateList = new ArrayList<Node>();
	}

	public void setNavigateModel(NAVIGATE_LEVEL type, String code,
			String content) {
		if (NAVIGATE_LEVEL.LEVEL1 == type) {
			level1 = new Node();
			level1.code = code;
			level1.content = content;
			navigateList.add(level1);
			level1.nodeList = new ArrayList<Node>();
		} else if (NAVIGATE_LEVEL.LEVEL2 == type) {
			level2 = new Node();
			level2.code = code;
			level2.content = content;
			level1.nodeList.add(level2);
			level2.nodeList = new ArrayList<Node>();
		} else if (NAVIGATE_LEVEL.ITEM == type) {
			item = new Node();
			item.code = code;
			item.content = content;
			level2.nodeList.add(item);
		}
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		for (Node node : navigateList) {
			result.append("----level 1----\n");
			result.append("--code: " + node.code + "--content: "
					+ node.content);
			for (Node subNode : node.nodeList) {
				result.append("----level 2----\n");
				result.append("--code: " + subNode.code + "--content: "
						+ subNode.content);
				for (Node item : subNode.nodeList) {
					result.append("----item----\n");
					result.append("--code: " + item.code + "--content: "
							+ item.content);
				}
			}

		}
		return result.toString();
	};
}
