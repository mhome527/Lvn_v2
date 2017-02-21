package teach.vietnam.asia.view.recognize;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.RecognizeTable;
import teach.vietnam.asia.entity.RecognizeEntity;


/**
 * Created by huynhtran on 5/12/16.
 */
public class RecognizeDao extends BaseDao<RecognizeEntity> {

    public RecognizeDao(Context context) {
        super(context);
    }

    @Override
    public RecognizeEntity fetch(Cursor cursor) {
        RecognizeEntity entity = new RecognizeEntity();
        entity.setWord_id(cursor.getInt(cursor.getColumnIndex(RecognizeTable.COL_WORD_ID)));
        entity.setGroup_id(cursor.getInt(cursor.getColumnIndex(RecognizeTable.COL_GROUP_ID)));
        entity.setVn(cursor.getString(cursor.getColumnIndex(RecognizeTable.COL_VN)));
        entity.setOt(cursor.getString(cursor.getColumnIndex(RecognizeTable.COL_OT)));
        entity.setEx(cursor.getString(cursor.getColumnIndex(RecognizeTable.COL_EX)));

        return entity;
    }

    public static List<RecognizeEntity> getListData(Context context, int kind) {
        RecognizeDao dao = new RecognizeDao(context);
        String where = " WHERE " + RecognizeTable.COL_GROUP_ID + "=" + kind;
        String sql = "SELECT * FROM " + RecognizeTable.getTableName(dao.lang) + where
                + " ORDER BY " + RecognizeTable.COL_VN;
        return dao.fetchAll(sql);
    }

    public static List<String> getGroupData(Context context) {
        RecognizeDao dao = new RecognizeDao(context);
        String sql = "SELECT GROUP_CONCAT(VN) FROM "
                + RecognizeTable.getTableName(dao.lang)
                + " GROUP BY " + RecognizeTable.COL_GROUP_ID;
        return dao.getListString(sql);
    }


}
