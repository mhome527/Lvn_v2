package teach.vietnam.asia.view.search;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.WordTable;
import teach.vietnam.asia.entity.WordEntity;


/**
 * Created by huynhtran on 5/12/16.
 */
public class SearchDao extends BaseDao<WordEntity> {

    private static final String TAG = "PhrasesDao";

    public SearchDao(Context context) {
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
        entity.setSound(cursor.getString(cursor.getColumnIndex(WordTable.COL_SOUND)));
        return entity;
    }

    public static List<WordEntity> getListData(Context context, int kind) {
        SearchDao dao = new SearchDao(context);
        String where = " WHERE " + WordTable.COL_KIND + "!=" + kind;
        String sql = "SELECT * FROM " + WordTable.getTableName(dao.lang) + where
                + " ORDER BY " + WordTable.COL_VI;
        return dao.fetchAll(sql);
    }

    public static List<WordEntity> getListDataAll(Context context) {
        SearchDao dao = new SearchDao(context);
        String sql = "SELECT * FROM " + WordTable.getTableName(dao.lang)
                + " ORDER BY " + WordTable.COL_VI;
        return dao.fetchAll(sql);
    }

}
