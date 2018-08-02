package teach.vietnam.asia.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class PlaceEntity extends BaseEntity implements Parcelable {
    public int id;
    public int type;
    public int area;
    public String title;
    public String ot;
    public String content;
    public String image;
    public String imgLinks;
    public double latitude;
    public double longitude;
//    public String location;
    public String address;
    public String sound;
    public int favorite;

    public PlaceEntity() {

    }


    protected PlaceEntity(Parcel in) {
        id = in.readInt();
        type = in.readInt();
        area = in.readInt();
        title = in.readString();
        ot = in.readString();
        content = in.readString();
        image = in.readString();
        imgLinks = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        address = in.readString();
        sound = in.readString();
        favorite = in.readInt();
    }

    public static final Creator<PlaceEntity> CREATOR = new Creator<PlaceEntity>() {
        @Override
        public PlaceEntity createFromParcel(Parcel in) {
            return new PlaceEntity(in);
        }

        @Override
        public PlaceEntity[] newArray(int size) {
            return new PlaceEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(type);
        parcel.writeInt(area);
        parcel.writeString(title);
        parcel.writeString(ot);
        parcel.writeString(content);
        parcel.writeString(image);
        parcel.writeString(imgLinks);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
        parcel.writeString(address);
        parcel.writeString(sound);
        parcel.writeInt(favorite);
    }
}
