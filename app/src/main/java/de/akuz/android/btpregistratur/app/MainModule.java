package de.akuz.android.btpregistratur.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.akuz.android.btpregistratur.app.data.DataStore;
import de.akuz.android.btpregistratur.app.data.GreenDAODataStore;
import de.akuz.android.btpregistratur.dao.DaoMaster;
import de.akuz.android.btpregistratur.dao.DaoSession;
import de.akuz.android.btpregistratur.dao.OperationDao;
import de.akuz.android.btpregistratur.dao.PatientDao;

/**
 * Created by till on 03.06.14.
 */

@Module(injects = {BaseActivity.class, BTPRegistraturApplication.class}, complete = false, library = true)
public class MainModule {

    private BTPRegistraturApplication app;

    public MainModule(BTPRegistraturApplication app) {
        this.app = app;
    }

    @Provides
    public Context provideContext() {
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    public SQLiteDatabase provideSQLiteDb(Context ctx) {
        return new DaoMaster.DevOpenHelper(ctx, "patient-db", null).getWritableDatabase();
    }

    @Provides
    @Singleton
    public DaoMaster provideDaoMaster(SQLiteDatabase db) {
        return new DaoMaster(db);
    }

    @Provides
    @Singleton
    public PatientDao providePatientDao(DaoSession session) {
        return session.getPatientDao();
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession(DaoMaster master) {
        return master.newSession();
    }

    @Provides
    @Singleton
    public OperationDao provideOperationDao(DaoSession session) {
        return session.getOperationDao();
    }

    @Provides
    @Singleton
    @DateFormat(DateFormats.DATE_ONLY)
    public SimpleDateFormat provideDateOnlyFormat() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Provides
    @Singleton
    @DateFormat(DateFormats.DATE_AND_TIME)
    public SimpleDateFormat provideDateAndTimeFormat() {
        return new SimpleDateFormat("HH:mm dd.MM.yyyy");
    }

    @Provides
    @Singleton
    public DataStore provideDataStore(PatientDao patientDao) {
        return new GreenDAODataStore(patientDao);
    }
}
