package teach.vietnam.asia.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.List;

import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.db.table.PlaceDetailLanguageTable;
import teach.vietnam.asia.db.table.PlaceDetailTable;
import teach.vietnam.asia.db.table.PlaceTitleLanguageTable;
import teach.vietnam.asia.db.table.PlaceTitleTable;
import teach.vietnam.asia.entity.PlaceEntity;

public class PlaceDao extends BaseDao<PlaceEntity> {


    public PlaceDao(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return PlaceDetailTable.TABLE_NAME;
    }

    @Override
    protected ContentValues getContentValues(PlaceEntity entity) {
        ContentValues cv = new ContentValues();
        cv.put(PlaceDetailTable.COL_FAVORITE, entity.favorite);
        cv.put(PlaceDetailTable.COL_LINKS, entity.imgLinks);
        return cv;
    }

    @Override
    protected String whereClause() {
        return " area = ? AND type = ? AND id = ?";
    }

    @Override
    protected String[] whereArgs(PlaceEntity entity) {
        return new String[]{String.valueOf(entity.area), String.valueOf(entity.type), String.valueOf(entity.id)};
    }


    @Override
    protected PlaceEntity fetch(Cursor cursor) {
        PlaceEntity entity = new PlaceEntity();
        entity.area = cursor.getInt(cursor.getColumnIndex(PlaceTitleTable.COL_AREA));
        entity.type = cursor.getInt(cursor.getColumnIndex(PlaceTitleTable.COL_TYPE));

        if (cursor.getColumnIndex(BaseTable.COL_ID) > -1)
            entity.id = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_ID));

        entity.vn = cursor.getString(cursor.getColumnIndex(PlaceTitleTable.COL_TITLE));
        entity.ot1 = cursor.getString(cursor.getColumnIndex(PlaceTitleLanguageTable.COL_OT1));
//        entity.location = cursor.getString(cursor.getColumnIndex(PlaceTitleTable.COL_LOCATION));

        if (cursor.getColumnIndex(PlaceDetailTable.COL_LATITUDE) > -1)
            entity.latitude = cursor.getDouble(cursor.getColumnIndex(PlaceDetailTable.COL_LATITUDE));

        if (cursor.getColumnIndex(PlaceDetailTable.COL_LONGITUDE) > -1)
            entity.longitude = cursor.getDouble(cursor.getColumnIndex(PlaceDetailTable.COL_LONGITUDE));

        entity.sound = cursor.getString(cursor.getColumnIndex(PlaceTitleTable.COL_SOUND));

        if (cursor.getColumnIndex(PlaceDetailLanguageTable.COL_CONTENT) > -1)
            entity.content = cursor.getString(cursor.getColumnIndex(PlaceDetailLanguageTable.COL_CONTENT));

        if (cursor.getColumnIndex(PlaceDetailTable.COL_IMAGE) > -1)
            entity.image = cursor.getString(cursor.getColumnIndex(PlaceDetailTable.COL_IMAGE));

        if (cursor.getColumnIndex(PlaceDetailTable.COL_LINKS) > -1)
            entity.imgLinks = cursor.getString(cursor.getColumnIndex(PlaceDetailTable.COL_LINKS));

        return entity;
    }

    /*
    lay title cua dia diem, chi hien thị title cua dia diem ma đã có data trong bang place_detail
     */
    public List<PlaceEntity> getPlaces(int area) {
        String sql = "SELECT VN.AREA, VN.TYPE, TITLE, OT1, SOUND" +
                " FROM TBL_PLACE_TITLE VN" +
                " , " + PlaceTitleLanguageTable.getTableName(lang) + " OT " +
                " WHERE  VN.AREA = OT.AREA AND VN.TYPE = OT.TYPE" +
                " AND VN.AREA = " + area +
                " ORDER BY SORT ASC";

        return fetchAll(sql);
    }

//    public List<PlaceEntity> getPlaceDetail(int area, int type) {
//        String sql = "\nSELECT VN.AREA, VN.TYPE, VN.ID, TITLE, OT1, CONTENT, LATITUDE, LONGITUDE, SOUND, IMAGE, LINKS, COL_REF_PHRASE " +
//                "\n FROM TBL_PLACE_DETAIL VN " +
//                " , " + PlaceDetailLanguageTable.getTableName(lang) + " OT" +
//                "\n WHERE VN.AREA = OT.AREA AND VN.TYPE = OT.TYPE AND VN.ID = OT.ID " +
//                "\n AND VN.AREA=" + area + " AND VN.TYPE= " + type +
//                "\n ORDER BY SORT ASC";
//
//        return fetchAll(sql);
//    }

    public void updateData(PlaceEntity entity) {
        updateRow(entity);
    }

}
