package teach.vietnam.asia.db.table;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by huynhtran on 5/12/16.
 */
public class WordTable {

    private static final String TAG = "WordTable";
    public static final String TABLE_NAME = "tblJpWord";
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

//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
//            + COL_NUM + " INTEGER, "
//            + COL_KIND + " INTEGER, "
//            + COL_JP1 + " TEXT, "
//            + COL_JP2 + " TEXT, "
//            + COL_OT + " TEXT"
//            + COL_IMG + " TEXT"
//            + COL_SOUND + " TEXT"
//            + COL_ROMAJI + " TEXT"
//            + " );";

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

}
