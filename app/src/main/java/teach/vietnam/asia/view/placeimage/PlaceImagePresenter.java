package teach.vietnam.asia.view.placeimage;


import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.view.base.BasePresenter;

public class PlaceImagePresenter extends BasePresenter<PlaceImageActivity> {
    public PlaceImagePresenter(PlaceImageActivity activity) {
        super(activity);
    }

//    place/area_%1$s/type_%2$s/%3$s/;

    public List<String> getLinks(int area, int type, int id, String links) {
        String path = String.format(Constant.FIREBASE_PLACE_PATH, area + "", type + "", id + "");

        String strS[] = links.split(",");
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < strS.length; i++) {
            arrayList.add(path + strS[i]);
        }

        return arrayList;
    }
}
