package teach.vietnam.asia.db.table;

import teach.vietnam.asia.Constant;


/**
 * Created by HuynhTD on 2/20/2017.
 */

public class RecognizeTable {
//    private static final String TAG = "RecognizeTable";

    public static final String COL_GROUP_ID = "GROUP_ID";
    public static final String COL_WORD_ID = "WORD_ID";
    public static final String COL_VN = "VN";
    public static final String COL_EX = "EX";
    public static final String COL_OT = "OT";

    public static String getTableName(String lang) {
        if (lang.equals(Constant.JA))
            return "TBL_REC_JA";
        else if (lang.equals(Constant.KO))
            return "TBL_REC_KO";
        else if (lang.equals(Constant.FR))
            return "TBL_REC_FR";
        else if (lang.equals(Constant.RU))
            return "TBL_REC_RU";
        else if (lang.equals(Constant.AR))
            return "TBL_REC_AR";
        else if (lang.equals(Constant.ZH))
            return "TBL_REC_CN";
        else if (lang.equals(Constant.ES))
            return "TBL_REC_ES";
        else
            return "TBL_REC_EN";
    }
}

