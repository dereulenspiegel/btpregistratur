package de.akuz.android.btpregistratur.app.utils;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.util.Log;

/**
 * Created by till on 05.06.14.
 */
public class SQLiteCursorLoader extends AsyncTaskLoader<Cursor> {

    private final static String TAG = SQLiteCursorLoader.class.getSimpleName();

    private final DBObserver mObserver = new DBObserver();

    private SQLiteDatabase db;
    private String tablename;
    private String[] fields;
    private String selection;
    private String[] selectionArgs;
    private String orderBy;
    private String having;
    private String groupBy;

    public SQLiteCursorLoader(Context context, SQLiteDatabase db, String tablename, String[] fields,
                              String selection, String[] selectionArgs,
                              String having, String groupBy, String orderBy) {
        super(context);
        this.db = db;
        this.tablename = tablename;
        this.fields = fields;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.having = having;
        this.groupBy = groupBy;
        this.orderBy = orderBy;
    }

    @Override
    public Cursor loadInBackground() {
        Cursor cursor = db.query(tablename, fields, selection, selectionArgs, groupBy, having, orderBy);
        cursor.registerContentObserver(mObserver);
        return cursor;
    }

    private class DBObserver extends ContentObserver {

        /**
         * Creates a content observer.
         */
        public DBObserver() {
            super(new Handler());
        }

        @Override
        public boolean deliverSelfNotifications() {
            return true;
        }

        @Override
        public void onChange(boolean selfChange) {
            Log.d("DBObserver", "onChange event, selfChange: " + selfChange);
            onContentChanged();
        }
    }
}
