package lv.rvt.tools;

public class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";

    // Shift + Alt + F
    public String RED(String p) {
        return RED + p + RESET;
    }

    public String GREEN(String p) {
        return GREEN + p + RESET;
    }

    public String YELLOW(String p) {
        return YELLOW + p + RESET;
    }

    public String BLUE(String p) {
        return BLUE + p + RESET;
    }

    public String PURPLE(String p) {
        return PURPLE + p + RESET;
    }
    public String CYAN(String p) {
        return CYAN + p + RESET;
    }
}
