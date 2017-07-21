package cm.demo.test;

import cm.demo.util.BonusUtils;

public class Test {
    public static void main(String[] args) {
//        String detail = "abcd$$$元";
//        String bonus = BonusUtils.getRandomBonus();
//        System.out.println("getRandomBonus " + bonus);
//        detail.replace(detail, "x");
//        System.out.println("detail " + detail);

//        String Str = new String("Welcome to Tutorialspoint.com");
        String detail = "abcd$$$元";
        String bonus = BonusUtils.getRandomBonus();
        System.out.println("getRandomBonus " + bonus);

//        System.out.print("Return Value :" );
//        System.out.println(detail.replace('a', 'T'));
//
//        System.out.print("Return Value :" );
//        System.out.println(detail.replace('b', 'D'));
//
//        System.out.print("Return Value :" );
//        System.out.println(detail.replace('$', 'X'));
//
//        System.out.print("Return Value :" );

        detail = detail.replace("$$$", bonus);
        System.out.println(detail);
    }
}
