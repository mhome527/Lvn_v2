package teach.vietnam.asia.view.phrase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.WordTable;
import teach.vietnam.asia.entity.WordEntity;


/**
 * Created by huynhtran on 5/12/16.
 */
public class PhrasesDao extends BaseDao<WordEntity> {

    private static final String TAG = "PhrasesDao";

    public PhrasesDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected ContentValues getContentValues(WordEntity entity) {
        return null;
    }

    @Override
    protected String whereClause() {
        return null;
    }

    @Override
    protected String[] whereArgs(WordEntity entity) {
        return new String[0];
    }

    @Override
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.setVi(cursor.getString(cursor.getColumnIndex(WordTable.COL_VI)));
        entity.setO1(cursor.getString(cursor.getColumnIndex(WordTable.COL_O1)));
        entity.setO2(cursor.getString(cursor.getColumnIndex(WordTable.COL_O2)));
        entity.setLevel(cursor.getInt(cursor.getColumnIndex(WordTable.COL_LEVEL)));
        entity.setKind(cursor.getInt(cursor.getColumnIndex(WordTable.COL_KIND)));
        entity.setImg(cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG)));
        return entity;
    }

    public static List<WordEntity> getListData(Context context, int kind) {
        String where = " WHERE " + WordTable.COL_KIND + "=" + kind;
        PhrasesDao dao = new PhrasesDao(context);

        String sql = "SELECT " + WordTable.COL_ROW_ID + ", * FROM " + WordTable.getTableName(dao.lang) + where
                + " ORDER BY " + WordTable.COL_VI;
        return dao.fetchAll(sql);
    }


}
