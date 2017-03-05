package teach.vietnam.asia.view.phrase;

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
    public WordEntity fetch(Cursor cursor) {
        WordEntity entity = new WordEntity();
        entity.setVi(cursor.getString(cursor.getColumnIndex(WordTable.COL_VI)));
        entity.setO1(cursor.getString(cursor.getColumnIndex(WordTable.COL_O1)));
        entity.setO2(cursor.getString(cursor.getColumnIndex(WordTable.COL_O2)));
        entity.setLevel(cursor.getInt(cursor.getColumnIndex(WordTable.COL_LEVEL)));
        entity.setKind(cursor.getInt(cursor.getColumnIndex(WordTable.COL_KIND)));
        entity.setDefault_word(cursor.getString(cursor.getColumnIndex(WordTable.COL_DEFAULT)));
        entity.setImg(cursor.getString(cursor.getColumnIndex(WordTable.COL_IMG)));
//        entity.setSound(cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND)));
        return entity;
    }

    public static List<WordEntity> getListData(Context context, int kind) {
        String where = " WHERE " + WordTable.COL_KIND + "=" + kind;
        String sql = "SELECT * FROM " + WordTable.TABLE_NAME + where
                + " ORDER BY " + WordTable.COL_VI;
        PhrasesDao dao = new PhrasesDao(context);
        return dao.fetchAll(sql);
    }


}
