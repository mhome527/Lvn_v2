package teach.vietnam.asia.view.placedetail;

import android.content.Context;
import android.database.Cursor;

import teach.vietnam.asia.db.dao.BaseDao;
import teach.vietnam.asia.db.table.BaseTable;
import teach.vietnam.asia.db.table.PlaceDetailLanguageTable;
import teach.vietnam.asia.db.table.PlaceDetailTable;
import teach.vietnam.asia.db.table.PlaceTitleLanguageTable;
import teach.vietnam.asia.db.table.PlaceTitleTable;
import teach.vietnam.asia.entity.PlaceEntity;

public class PlaceDetailDao extends BaseDao<PlaceEntity> {

    public PlaceDetailDao(Context context) {
        super(context);
    }

    @Override
    protected PlaceEntity fetch(Cursor cursor) {
        PlaceEntity entity = new PlaceEntity();
        entity.area = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_AREA));
        entity.type = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_TYPE));
        entity.id = cursor.getInt(cursor.getColumnIndex(BaseTable.COL_ID));
        entity.title = cursor.getString(cursor.getColumnIndex(PlaceTitleTable.COL_TITLE));
        entity.ot = cursor.getString(cursor.getColumnIndex(PlaceTitleLanguageTable.COL_OT1));

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

        if (cursor.getColumnIndex(PlaceDetailTable.COL_ADDRESS) > -1)
            entity.address = cursor.getString(cursor.getColumnIndex(PlaceDetailTable.COL_ADDRESS));

        return entity;
    }

    public PlaceEntity getPlaceDetail(int area, int type, int id) {
        String sql = "SELECT p.AREA, p.TYPE, p.id, TITLE, OT1, CONTENT, LATITUDE, LONGITUDE, ADDRESS, SOUND, IMAGE, LINKS " +
                " FROM TBL_PLACE_DETAIL p " +
                " , " + PlaceDetailLanguageTable.getTableName(lang) + " OT" +
                " WHERE p.AREA = OT.AREA AND p.TYPE = OT.TYPE AND p.ID = OT.ID " +
                " AND p.AREA=" + area + " AND p.TYPE= " + type +
                " AND p.id=" + id +
                " ORDER BY SORT ASC";

        return fetch(sql);
    }


}
