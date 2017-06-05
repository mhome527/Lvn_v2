package teach.vietnam.asia.entity;

public class TblAlphabetEx {

    private String sound;
    private String alphabet;
    private String example;
    private int res;

    public TblAlphabetEx() {
    }

    public TblAlphabetEx(String alphabet, String example, int res) {
        this.alphabet = alphabet;
        this.sound = alphabet;
        this.res = res;
        this.example = example;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getSound() {
        return sound;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
