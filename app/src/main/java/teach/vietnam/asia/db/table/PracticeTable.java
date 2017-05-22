package teach.vietnam.asia.db.table;

import teach.vietnam.asia.Constant;


/**
 * Created by Administrator on 2/20/2017.
 */

public class PracticeTable {
    private static final String TAG = "PracticeTable";
//    public static final String TABLE_NAME = "tblJpWord";
//    public static final String TABLE_NAME_EN = "tblJpWordEn";

    /*
    `GROUP_ID`	INTEGER,
	`WORD_ID`	INTEGER,
	`VN`	TEXT,
	`EX`	TEXT,
	`OT`	TEXT
*/

    public static String getTableName(String lang) {
        if (lang.equals(Constant.JA))
            return "TBL_REC_JA";
        else if (lang.equals(Constant.KO))
            return "TBL_REC_KO";
        else if (lang.equals(Constant.FR))
            return "TBL_REC_FR";
        else if (lang.equals(Constant.RU))
            return "TBL_REC_RU";
        else
            return "TBL_REC_EN";
    }
}

