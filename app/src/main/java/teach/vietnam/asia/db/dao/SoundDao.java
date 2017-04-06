package teach.vietnam.asia.db.dao;

import android.content.Context;
import android.database.Cursor;

import teach.vietnam.asia.db.table.SoundTable;


/**
 * Created by huynhtran on 5/12/16.
 */
public class SoundDao extends BaseDao<String> {

    private static final String TAG = "SoundDao";

    public SoundDao(Context context) {
        super(context);
    }

    @Override
    public String fetch(Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(SoundTable.COL_SOUND));
    }

    public static String getNameSound(Context context, String name) {
        SoundDao dao = new SoundDao(context);
        String sql = "SELECT " + SoundTable.COL_SOUND + " FROM " + SoundTable.TABLE_NAME + " WHERE " + SoundTable.COL_FILENAME + "='" + name.trim() + "' limit 1";
        return dao.fetch(sql);
    }


}
