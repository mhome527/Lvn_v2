package teach.vietnam.asia.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodEntity extends BaseEntity implements Parcelable {
    public int area;
    public int type;
    public int kind;
    public int id;
    public String name;
    public String ot;
    public String image;
    public String content;


    public FoodEntity() {

    }


    protected FoodEntity(Parcel in) {
        area = in.readInt();
        type = in.readInt();
        kind = in.readInt();
        id = in.readInt();
        name = in.readString();
        ot = in.readString();
        image = in.readString();
        content = in.readString();
    }

    public static final Creator<FoodEntity> CREATOR = new Creator<FoodEntity>() {
        @Override
        public FoodEntity createFromParcel(Parcel in) {
            return new FoodEntity(in);
        }

        @Override
        public FoodEntity[] newArray(int size) {
            return new FoodEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(area);
        parcel.writeInt(type);
        parcel.writeInt(kind);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(ot);
        parcel.writeString(image);
        parcel.writeString(content);
    }
}
