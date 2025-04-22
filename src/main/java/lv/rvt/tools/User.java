package lv.rvt.tools;

public class User {

    private String name;
    private String parole;

    public User(String name, String parole) {
        this.name = name;
        this.parole = parole;
    }

    public String getNick() {
        return name;
    }
    public String getPasword(){
        return parole;
    }
    public String toCsvROW() {
        return name + ", " + parole;

    }
}
