package pl.adamsiedlecki.ExQue.util;

public class NumberThings {

    public static boolean isIntNumber(String value) {
        return value.matches("[0-9]+") && value.length() > 2;
    }
}
