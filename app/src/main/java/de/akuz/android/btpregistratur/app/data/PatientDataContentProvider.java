package de.akuz.android.btpregistratur.app.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import javax.inject.Inject;

import de.akuz.android.btpregistratur.app.BTPRegistraturApplication;
import de.akuz.android.btpregistratur.dao.PatientDao;

/**
 * Created by till on 08.06.14.
 */
public class PatientDataContentProvider extends ContentProvider {

    @Inject
    SQLiteDatabase db;

    private void checkAndEventuallyInitializeDB() {
        if (db == null) {
            BTPRegistraturApplication app = (BTPRegistraturApplication) getContext().getApplicationContext();
            app.getObjectGraph().inject(this);
        }
    }

    @Override
    public boolean onCreate() {

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        checkAndEventuallyInitializeDB();
        Cursor cursor = db.query(PatientDao.TABLENAME, projection, selection, selectionArgs, null, null, sortOrder);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // We aren't currently implenting this
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        throw new UnsupportedOperationException();
    }
}
