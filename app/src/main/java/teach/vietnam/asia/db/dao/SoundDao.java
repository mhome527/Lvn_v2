package teach.vietnam.asia.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import teach.vietnam.asia.db.table.SoundTable;
import teach.vietnam.asia.entity.BaseEntity;


/**
 * Created by huynhtran on 5/12/16.
 */
public class SoundDao extends BaseDao<BaseEntity> {

    private static final String TAG = "SoundDao";

    public SoundDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return SoundTable.TABLE_NAME;
    }

    @Override
    protected ContentValues getContentValues(BaseEntity entity) {
        return null;
    }

    @Override
    protected String whereClause() {
        return null;
    }

    @Override
    protected String[] whereArgs(BaseEntity entity) {
        return new String[0];
    }

    @Override
    public BaseEntity fetch(Cursor cursor) {
        BaseEntity entity = new BaseEntity();
        entity.setText(cursor.getString(cursor.getColumnIndex(SoundTable.COL_SOUND)));
        return entity;
    }

    public static String getNameSound(Context context, String name) {
        SoundDao dao = new SoundDao(context);
        String sql = "SELECT " + SoundTable.COL_SOUND + " FROM " + SoundTable.TABLE_NAME + " WHERE " + SoundTable.COL_FILENAME + "='" + name.trim() + "' limit 1";
        BaseEntity entity = dao.fetch(sql);
        if (entity == null)
            return "";
        return entity.getText();
    }

}
