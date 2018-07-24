package teach.vietnam.asia.view.dashboard.search;

import android.os.Parcel;
import android.os.Parcelable;

import teach.vietnam.asia.entity.BaseEntity;

public class SearchEntity extends BaseEntity implements Parcelable {
    public int id;
    public int type;
    public int area;
    public int kind;
    public String ot;
    public String vn;

    public SearchEntity() {
    }

    protected SearchEntity(Parcel in) {
        id = in.readInt();
        type = in.readInt();
        area = in.readInt();
        kind = in.readInt();
        ot = in.readString();
        vn = in.readString();
    }

    public static final Creator<SearchEntity> CREATOR = new Creator<SearchEntity>() {
        @Override
        public SearchEntity createFromParcel(Parcel in) {
            return new SearchEntity(in);
        }

        @Override
        public SearchEntity[] newArray(int size) {
            return new SearchEntity[size];
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
        parcel.writeInt(kind);
        parcel.writeString(ot);
        parcel.writeString(vn);
    }
}
