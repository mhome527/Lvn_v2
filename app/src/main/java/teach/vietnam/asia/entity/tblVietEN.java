package teach.vietnam.asia.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table TBL_VIET_EN.
 */
public class tblVietEN {

    private String vi;
    private Integer kind;
    private String img;
    private String o1;
    private String o2;
    private Integer level;
    private String default_word;

    public tblVietEN() {
    }

    public tblVietEN(String vi) {
        this.vi = vi;
    }

    public tblVietEN(String vi, Integer kind, String img, String o1, String o2, Integer level, String default_word) {
        this.vi = vi;
        this.kind = kind;
        this.img = img;
        this.o1 = o1;
        this.o2 = o2;
        this.level = level;
        this.default_word = default_word;
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

    public String getDefault_word() {
        return default_word;
    }

    public void setDefault_word(String default_word) {
        this.default_word = default_word;
    }

}
