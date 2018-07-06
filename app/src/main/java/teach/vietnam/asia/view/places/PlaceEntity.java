package teach.vietnam.asia.view.places;

import android.os.Parcel;
import android.os.Parcelable;

import teach.vietnam.asia.entity.BaseEntity;

public class PlaceEntity extends BaseEntity implements Parcelable {
    public int id;
    public int placeId;
    public int area;
    public int refId;
    public int kind;
    public String Name;
    public String strImages;
    public String location;
    public int favorite;

    protected PlaceEntity(Parcel in) {
        id = in.readInt();
        placeId = in.readInt();
        area = in.readInt();
        refId = in.readInt();
        kind = in.readInt();
        Name = in.readString();
        strImages = in.readString();
        location = in.readString();
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
        parcel.writeInt(placeId);
        parcel.writeInt(area);
        parcel.writeInt(refId);
        parcel.writeInt(kind);
        parcel.writeString(Name);
        parcel.writeString(strImages);
        parcel.writeString(location);
        parcel.writeInt(favorite);
    }
}
