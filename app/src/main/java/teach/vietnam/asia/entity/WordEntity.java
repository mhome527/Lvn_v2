package teach.vietnam.asia.entity;

/**
 * Created by HuynhTD on 2/16/2017.
 */

public class WordEntity {
    private String vi;
    private Integer kind;
    private String img;
    private String o1;
    private String o2;
    private Integer level;
    private String sound;

    private int num = 0;
    ////////

    public WordEntity() {

    }

    public WordEntity(String vi, String o1) {
        this.vi = vi;
        this.o1 = o1;
        this.kind = 0;
        this.level = 0;
        this.o2 = "";
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getO1() {
        return o1;
    }

    public void setO1(String o1) {
        this.o1 = o1;
    }

    public String getO2() {
        return o2;
    }

    public void setO2(String o2) {
        this.o2 = o2;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
