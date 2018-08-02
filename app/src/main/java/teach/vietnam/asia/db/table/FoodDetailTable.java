package teach.vietnam.asia.db.table;

import teach.vietnam.asia.Constant;

public class FoodDetailTable {
    public static final String TABLE_NAME_EN = "TBL_FOOD_DETAIL_EN";
    public static final String TABLE_NAME_JA = "TBL_FOOD_DETAIL_JA";

    public static final String COL_AREA = "AREA";
    public static final String COL_TYPE = "TYPE";
    public static final String COL_ID = "ID";
    public static final String COL_OT = "OT";
    public static final String COL_CONTENT = "CONTENT";

    public static String getTableName(String lang) {
        String table;
        if (lang.equals(Constant.JA))
            table = TABLE_NAME_JA;
        else
            table = TABLE_NAME_EN; //fixme change table name
        return table;
    }
}

/*
    AREA: 1: mien nam, 2: mien trung, 3: mien bac


 */