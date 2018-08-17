package teach.vietnam.asia.view.dashboard.search;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import teach.vietnam.asia.Constant;
import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.db.table.FoodDetailTable;
import teach.vietnam.asia.db.table.PlaceDetailLanguageTable;
import teach.vietnam.asia.db.table.PlaceDetailTable;
import teach.vietnam.asia.db.table.PlaceTitleTable;
import teach.vietnam.asia.db.table.WordTable;


public class SearchDao extends BaseDao<SearchEntity> {
    public SearchDao(Context context) {
        super(context);
    }

    public int id;
    public int type;
    public int area;
    public int kind;
    public String ot;
    public String vn;

    @Override
    protected SearchEntity fetch(Cursor cursor) {
        SearchEntity entity = new SearchEntity();

        if (cursor.getColumnIndex(BaseTable.COL_AREA) > -1)
            entity.area = cursor.getInt(cursor.getColumnIndex(PlaceTitleTable.COL_AREA));

        if (cursor.getColumnIndex(BaseTable.COL_TYPE) > -1)
            entity.type = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_TYPE));

        if (cursor.getColumnIndex(BaseTable.COL_ID) > -1)
            entity.id = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_ID));

        if (cursor.getColumnIndex(BaseTable.COL_KIND) > -1)
            entity.kind = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_KIND));

        entity.vn = cursor.getString(cursor.getColumnIndex(BaseTable.COL_TITLE));
        entity.ot = cursor.getString(cursor.getColumnIndex(BaseTable.COL_OT1));

        if (cursor.getColumnIndex(PlaceDetailTable.COL_IMAGE) > -1)
            entity.image = cursor.getString(cursor.getColumnIndex(PlaceDetailTable.COL_IMAGE));

        return entity;
    }

    /*
select vn.area, vn.type, vn.id, 100 kind, vn.title, ot1 from TBL_PLACE_DETAIL vn, TBL_PLACE_DETAIL_EN ot
where vn.area = ot.area and vn.type = ot.type and vn.id = ot.id
and (vn.title2 like '%tfh%' or ot1 like '%動%' or search_text like '%こうえん%')
union all
Select 0 area, 0 type, 0 id, kind, vi title, o1 ot1 from TBL_VIET_JA
where kind in (1,2,3,4, 5, 6, 11, 31)
and (vi2 like '%con b%' or o1 like '%たこ%' or o2 like '%ちょ%')

     */

    public List<SearchEntity> getData(String key) {
        String sql = "SELECT 0 AREA, 0 TYPE, 0 ID, KIND, vi TITLE, o1 OT1 " +
                " FROM " + WordTable.getTableName(lang) +
                " WHERE kind IN (1, 2, 3, 4, 5, 6, 11, 31)" +
                " AND (vi2 LIKE '%" + key + "%' OR o2 LIKE '%" + key + "%')" +
                " UNION ALL " +
                " SELECT vn.area AREA, vn.type TYPE, vn.id ID, " + Constant.TYPE_DATA_PLACE + " KIND, vn.title TITLE, OT1 " +
                " FROM TBL_PLACE_DETAIL vn, " + PlaceDetailLanguageTable.getTableName(lang) + " ot" +
                " WHERE vn.area = ot.area AND vn.type = ot.type AND vn.id = ot.id" +
                " AND " +
                " ( vn.title2 LIKE '%" + key + "%' " +
                " OR ot1 like '%" + key + "%' " +
                " OR search_text LIKE '%" + key + "%'" +
                " )" +
                " ORDER BY kind";

        return fetchAll(sql);
    }

    /*
SELECT v.area, v.type, v.kind, v.id, v.name, ot, content
FROM TBL_FOOD_VN v, TBL_FOOD_DETAIL_JA e
WHERE v.area = e.area AND v.type = e.type AND v.id = e.id
AND (v.name like '%CAN%' OR OT like '%ゴイu%')
ORDER BY v.type, name asc
     */
    public List<SearchEntity> getFood(String key) {
        String sql = "SELECT v.area, v.type, " + Constant.SEARCH_DATA_FOOD + " KIND, v.id, v.name TITLE, ot OT1, IMAGE, content \n" +
                "FROM TBL_FOOD_VN v, " + FoodDetailTable.getTableName(lang) + " e \n" +
                "WHERE v.area = e.area AND v.type = e.type AND v.id = e.id \n" +
                "AND (v.name LIKE '%" + key + "%' " +
                "OR v.NAME_SEARCH LIKE '%" + key + "%' \n" +
                "OR OT LIKE '%" + key + "%') \n" +
                "ORDER BY v.type, name asc";

        return fetchAll(sql);
    }

    public List<SearchEntity> getPhrases(String key) {
        String sql = "SELECT 0 AREA, 0 TYPE, 0 ID, " + Constant.SEARCH_DATA_PHRASES + " KIND, vi TITLE, o1 OT1 \n" +
                " FROM " + WordTable.getTableName(lang) +
                " WHERE kind in (1, 2, 3, 4, 5, 6, 11, 31) \n" +
                " AND (vi2 LIKE '%" + key + "%' OR o1 LIKE '%" + key + "%' OR o2 LIKE '%" + key + "%') \n" +
                " ORDER BY kind";

        return fetchAll(sql);
    }

    public List<SearchEntity> getPlace(String key) {
        String sql = " SELECT vn.area AREA, vn.type TYPE, vn.id ID, " + Constant.SEARCH_DATA_PLACE + " KIND, vn.title TITLE, OT1, IMAGE " +
                " FROM TBL_PLACE_DETAIL vn, " + PlaceDetailLanguageTable.getTableName(lang) + " ot" +
                " WHERE vn.area = ot.area AND vn.type = ot.type AND vn.id = ot.id" +
                " AND " +
                " ( vn.title2 LIKE '%" + key + "%' " +
                " OR ot1 LIKE '%" + key + "%' " +
                " OR search_text LIKE '%" + key + "%'" +
                " )" +
                " ORDER BY kind, sort";

        return fetchAll(sql);
    }

    public List<SearchEntity> getPlaceDetail(int area, int type) {
        String sql = "SELECT VN.AREA, VN.TYPE, VN.ID, TITLE, OT1, CONTENT, LATITUDE, LONGITUDE, SOUND, IMAGE, LINKS " +
                " FROM TBL_PLACE_DETAIL VN " +
                " , " + PlaceDetailLanguageTable.getTableName(lang) + " OT" +
                " WHERE VN.AREA = OT.AREA AND VN.TYPE = OT.TYPE AND VN.ID = OT.ID " +
                " AND VN.AREA=" + area + " AND VN.TYPE= " + type +
                " ORDER BY SORT ASC";

        return fetchAll(sql);
    }
}
