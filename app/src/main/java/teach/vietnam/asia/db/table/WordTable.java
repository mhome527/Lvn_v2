package teach.vietnam.asia.db.table;

import android.database.sqlite.SQLiteDatabase;

import teach.vietnam.asia.Constant;


/**
 * Created by huynhtran on 5/12/16.
 */
public class WordTable extends SoundTable {

    private static final String TAG = "WordTable";
    public static final String TABLE_NAME = "TBL_VIET_EN";
//    public static final String TABLE_NAME_EN = "tblJpWordEn";

    public static final String COL_VI = "VI";
    public static final String COL_KIND = "KIND";
    public static final String COL_O1 = "O1";
    public static final String COL_O2 = "O2";
    public static final String COL_IMG = "IMG";
    public static final String COL_LEVEL = "LEVEL";
    public static final String COL_SOUND = "SOUND";
    public static final String COL_DEFAULT = "DEFAULT_WORD";
//    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

    public static void onCreate(SQLiteDatabase database) {
//        Log.i(TAG, "CREATE TABLE " + TABLE_NAME);
//        database.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
//        Log.i(TAG, "Upgrading database from version "
//                + oldVersion + " to " + newVersion
//                + ", which will destroy all old data");
//        database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(database);
    }

    public static String getTableName(String lang) {
        String table;
        if (lang.equals(Constant.TYPE_JA))
            table = "TBL_VIET_JA";
        else if (lang.equals(Constant.TYPE_KO))
            table = "TBL_VIET_KO";
        else if (lang.equals(Constant.TYPE_FR))
            table = "TBL_VIET_EN";
        else if (lang.equals(Constant.TYPE_RU))
            table = "TBL_VIET_EN";
        else
            table = "TBL_VIET_EN";
        return table;
    }

    public static String getTableName2(String lang) {
        String table;
        if (lang.equals(Constant.TYPE_JA))
            table = "TBL_VIET_JA";
        else if (lang.equals(Constant.TYPE_KO))
            table = "TBL_VIET_KO";
        else if (lang.equals(Constant.TYPE_FR))
            table = "TBL_VIET_EN";
        else if (lang.equals(Constant.TYPE_RU))
            table = "TBL_VIET_EN";
        else
            table = "TBL_VIET_EN";
        return table + " A, " + SoundTable.TABLE_NAME + " S";
    }
}
