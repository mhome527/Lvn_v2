package teach.vietnam.asia.db.table;

import teach.vietnam.asia.Constant;

public class PlaceDetailLanguageTable {
    public static final String TABLE_NAME_EN = "TBL_PLACE_DETAIL_EN";
    public static final String TABLE_NAME_JA = "TBL_PLACE_DETAIL_JA";

    public static final String COL_AREA = "AREA";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_ID = "ID";
    public static final String COL_OT1 = "OT1";
    public static final String COL_OT2 = "OT2";
    public static final String COL_CONTENT = "CONTENT";

    public static String getTableName(String lang) {
        String table;
        if (lang.equals(Constant.JA))
            table = TABLE_NAME_JA;
        else
            table = TABLE_NAME_EN;//fixme change table name
        return table;
    }
}
