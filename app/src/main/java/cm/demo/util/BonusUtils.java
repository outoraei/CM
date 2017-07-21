package cm.demo.util;

import java.util.Random;

public class BonusUtils {

    private static final String NUMBER_OTHER_CHAR = "0123456789";
    private static final String NUMBER_ONE_CHAR = "0000000111";
    private static final int SIZE = 3;

    public static String getRandomBonus() {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < SIZE; i++) {
            if (0 == i) {
                sb.append(NUMBER_ONE_CHAR.charAt(random.nextInt(NUMBER_ONE_CHAR.length())));
                sb.append(".");
            } else {
                sb.append(NUMBER_OTHER_CHAR.charAt(random.nextInt(NUMBER_OTHER_CHAR.length())));
            }
        }
        return sb.toString();
    }
}
