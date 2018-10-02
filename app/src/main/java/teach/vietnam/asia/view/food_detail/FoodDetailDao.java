package teach.vietnam.asia.view.food_detail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.FoodDetailTable;
import teach.vietnam.asia.db.table.FoodTable;
import teach.vietnam.asia.entity.FoodEntity;


/**
 * Created by huynhtran on 5/12/16.
 */
public class FoodDetailDao extends BaseDao<FoodEntity> {

    public FoodDetailDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return FoodDetailTable.getTableName(lang);
    }

    @Override
    protected ContentValues getContentValues(FoodEntity entity) {
        return null;
    }

    @Override
    protected String whereClause() {
        return null;
    }

    @Override
    protected String[] whereArgs(FoodEntity entity) {
        return new String[0];
    }

    @Override
    public FoodEntity fetch(Cursor cursor) {
        FoodEntity entity = new FoodEntity();
        entity.area = cursor.getInt(cursor.getColumnIndex(FoodTable.COL_AREA));
        entity.type = cursor.getInt(cursor.getColumnIndex(FoodTable.COL_TYPE));
        entity.kind = cursor.getInt(cursor.getColumnIndex(FoodTable.COL_KIND));
        entity.id = cursor.getInt(cursor.getColumnIndex(FoodTable.COL_ID));

        entity.name = cursor.getString(cursor.getColumnIndex(FoodTable.COL_NAME));
        entity.image = cursor.getString(cursor.getColumnIndex(FoodTable.COL_IMAGE));
        entity.ot = cursor.getString(cursor.getColumnIndex(FoodDetailTable.COL_OT));
        entity.content = cursor.getString(cursor.getColumnIndex(FoodDetailTable.COL_CONTENT));

//        if (cursor.getColumnIndex(BaseTable.COL_ID) > -1)

        return entity;
    }

    public FoodEntity getData(int area, int type, int id) {
        String sql = "SELECT V.AREA, V.TYPE, KIND, V.ID, V.NAME, IMAGE, OT, CONTENT " +
                " FROM TBL_FOOD_VN V, " + FoodDetailTable.getTableName(lang) + " O" +
                " WHERE V.AREA = O.AREA AND V.TYPE = O.TYPE AND V.ID = O.ID" +
                " AND V.AREA=" + area +
                " AND V.TYPE=" + type +
                " AND V.ID=" + id +
                " ORDER BY V.TYPE, KIND";

        FoodDetailDao dao = new FoodDetailDao(context);
        return dao.fetch(sql);
    }

//    public List<FoodEntity> getListData2(int type) {
//        String sql = "SELECT V.AREA, V.TYPE, KIND, V.ID, V.NAME, IMAGE, OT, CONTENT " +
//                " FROM TBL_FOOD_VN V, " + FoodDetailTable.getTableName(lang) + " O" +
//                " WHERE V.AREA = O.AREA AND V.TYPE = O.TYPE AND V.ID = O.ID" +
//                " AND V.TYPE=" + type +
//                " ORDER BY V.TYPE, KIND";
//
//        FoodDetailDao dao = new FoodDetailDao(context);
//        return dao.fetchAll(sql);
//    }

    public static FoodEntity getData(Context context, int area, int type, int id) {
        FoodDetailDao dao = new FoodDetailDao(context);
        return dao.getData(area, type, id);
    }


}
