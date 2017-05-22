package teach.vietnam.asia.db.dao;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import teach.vietnam.asia.BaseApplication;
import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.Constant;
import teach.vietnam.asia.db.DatabaseHelper;
import teach.vietnam.asia.entity.BaseEntity;


public abstract class BaseDao<T extends BaseEntity> {
    public final static String TAG = BaseDao.class.getName();
    protected Context context;
    public String lang = Constant.EN;

    public BaseDao(Context context) {
        this.context = context;
        lang = BaseApplication.getInstance().pref.getStringValue(Constant.EN, Constant.TYPE_LANGUAGE);

    }

//    protected abstract ContentValues getContentValues(T entity);

    protected abstract T fetch(Cursor cursor);

//    protected List<T> fetchAll(Cursor cursor) {
//        List<T> listData = new ArrayList<>();
//
//        if (cursor != null) {
//            Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
//            if (cursor.moveToFirst()) {
//                do {
//                    listData.add(fetch(cursor));
//                } while (cursor.moveToNext());
//            }
//            cursor.close();
//        }
//        return listData;
//    }

    protected T fetch(String sql) {
        Log.i(TAG, "fetchAll sql:" + sql);
        T entity = null;
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    entity = fetch(cursor);
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return entity;
    }


//    protected List<T> fetchAll(String sql) {
//        Log.i(TAG, "fetchAll sql:" + sql);
//        List<T> listData = new ArrayList<>();
//        try {
//            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
//            if (cursor != null) {
//                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
//                if (cursor.moveToFirst()) {
//                    do {
//                        T entity = fetch(cursor);
//                        entity.setNum(count);
//                        listData.add(fetch(cursor));
//                    } while (cursor.moveToNext());
//                }
//                cursor.close();
//            }
//        } catch (Exception e) {
//            if (BuildConfig.DEBUG)
//                e.printStackTrace();
//        }
//        return listData;
//    }

    protected List<T> fetchAll(String sql) {
        Log.i(TAG, "fetchAll sql:" + sql);
        List<T> listData = new ArrayList<>();
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                int count = 0;
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        T entity = fetch(cursor);
                        entity.setNum(count++);
                        listData.add(entity);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return listData;
    }

    public int getCount(String sql) {
        int count = 0;
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    count = cursor.getInt(0);
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return count;
    }

    public List<String> getListString(String sql) {
        List<String> listData = new ArrayList<>();
        try {
            Cursor cursor = DatabaseHelper.getInstance(context).executeQuery(sql);
            if (cursor != null) {
                Log.i(TAG, "list " + this.getClass() + " size:" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        listData.add(cursor.getString(0));
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        } catch (Exception e) {
            if (BuildConfig.DEBUG)
                e.printStackTrace();
        }
        return listData;
    }

//    protected SQLiteDatabase getDB(){
//        return DatabaseHelper.getInstance(context).getDB();
//    }
}
