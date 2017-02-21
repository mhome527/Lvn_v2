package teach.vietnam.asia.view.alphabet;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.R;
import teach.vietnam.asia.entity.TblAlphabetEx;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.BasePresenter;
import teach.vietnam.asia.view.ICallback;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class AlphabetPresenter extends BasePresenter<AlphabetActivity> {
    public AlphabetPresenter(AlphabetActivity activity) {
        super(activity);
    }

    public void loadData(ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return getData();
                    }
                }
        );

    }

    private List<TblAlphabetEx> getData() {
        TblAlphabetEx tbl;
        String[] arrAlphabet;
        List<TblAlphabetEx> lstData = new ArrayList<>();
        arrAlphabet = activity.getResources().getStringArray(R.array.alphabet);

        try {
            for (String name : arrAlphabet) {
                tbl = new TblAlphabetEx();
                tbl.setAlphabet(name);
                if (name.toLowerCase().equals("sắc"))
                    tbl.setSymbol("/");
                else if (name.toLowerCase().equals("huyền"))
                    tbl.setSymbol("\\");
                else if (name.toLowerCase().equals("hỏi"))
                    tbl.setSymbol("?");
                else if (name.toLowerCase().equals("ngã"))
                    tbl.setSymbol("~");
                else if (name.toLowerCase().equals("nặng"))
                    tbl.setSymbol(".");
//                    sound = Common.getNameSound(name);
                tbl.setSound(name);
                lstData.add(tbl);
            }

        } catch (Exception e) {
            Log.e(AlphabetActivity.class, "load data error:" + e.getMessage());
            e.printStackTrace();
        }

        return lstData;
    }
}
