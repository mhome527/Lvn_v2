package teach.vietnam.asia.db.table;

import android.database.sqlite.SQLiteDatabase;


/**
 * Created by huynhtran on 5/12/16.
 */
public class RecJaTable {

//    private static final String TAG = "RecJaTable";
    public static final String TABLE_NAME = "TBL_REC_JA";

    public static final String COL_GROUP_ID = "GROUP_ID";
    public static final String COL_WORD_ID = "WORD_ID";
    public static final String COL_VN = "VN";
    public static final String COL_EX = "EX";
    public static final String COL_OT = "OT";

    public static final String CLEAR_TABLE = "delete from " + TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
            + COL_GROUP_ID + " INTEGER, "
            + COL_WORD_ID + " INTEGER, "
            + COL_VN + " TEXT, "
            + COL_EX + " TEXT"
            + COL_OT + " TEXT"
            + " );";

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
