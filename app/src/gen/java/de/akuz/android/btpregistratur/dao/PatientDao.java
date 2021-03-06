package de.akuz.android.btpregistratur.dao;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;

import de.akuz.android.btpregistratur.dao.Patient;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PATIENT.
*/
public class PatientDao extends AbstractDao<Patient, Long> {

    public static final String TABLENAME = "PATIENT";

    /**
     * Properties of entity Patient.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property FirstName = new Property(1, String.class, "firstName", false, "FIRST_NAME");
        public final static Property LastName = new Property(2, String.class, "lastName", false, "LAST_NAME");
        public final static Property Gender = new Property(3, String.class, "gender", false, "GENDER");
        public final static Property Birthday = new Property(4, java.util.Date.class, "birthday", false, "BIRTHDAY");
        public final static Property Arrival = new Property(5, java.util.Date.class, "arrival", false, "ARRIVAL");
        public final static Property Leave = new Property(6, java.util.Date.class, "leave", false, "LEAVE");
        public final static Property UniqueId = new Property(7, String.class, "uniqueId", false, "UNIQUE_ID");
        public final static Property Comment = new Property(8, String.class, "comment", false, "COMMENT");
        public final static Property TransportCallSign = new Property(9, String.class, "transportCallSign", false, "TRANSPORT_CALL_SIGN");
        public final static Property StreetAddress = new Property(10, String.class, "streetAddress", false, "STREET_ADDRESS");
        public final static Property OperationId = new Property(11, Long.class, "operationId", false, "OPERATION_ID");
    };

    private DaoSession daoSession;


    public PatientDao(DaoConfig config) {
        super(config);
    }
    
    public PatientDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PATIENT' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'FIRST_NAME' TEXT NOT NULL ," + // 1: firstName
                "'LAST_NAME' TEXT NOT NULL ," + // 2: lastName
                "'GENDER' TEXT," + // 3: gender
                "'BIRTHDAY' INTEGER," + // 4: birthday
                "'ARRIVAL' INTEGER NOT NULL ," + // 5: arrival
                "'LEAVE' INTEGER," + // 6: leave
                "'UNIQUE_ID' TEXT," + // 7: uniqueId
                "'COMMENT' TEXT," + // 8: comment
                "'TRANSPORT_CALL_SIGN' TEXT," + // 9: transportCallSign
                "'STREET_ADDRESS' TEXT NOT NULL ," + // 10: streetAddress
                "'OPERATION_ID' INTEGER);"); // 11: operationId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_PATIENT__id ON PATIENT" +
                " (_id);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_PATIENT_FIRST_NAME ON PATIENT" +
                " (FIRST_NAME);");
        db.execSQL("CREATE INDEX " + constraint + "IDX_PATIENT_LAST_NAME ON PATIENT" +
                " (LAST_NAME);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PATIENT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Patient entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getFirstName());
        stmt.bindString(3, entity.getLastName());
 
        String gender = entity.getGender();
        if (gender != null) {
            stmt.bindString(4, gender);
        }
 
        java.util.Date birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindLong(5, birthday.getTime());
        }
        stmt.bindLong(6, entity.getArrival().getTime());
 
        java.util.Date leave = entity.getLeave();
        if (leave != null) {
            stmt.bindLong(7, leave.getTime());
        }
 
        String uniqueId = entity.getUniqueId();
        if (uniqueId != null) {
            stmt.bindString(8, uniqueId);
        }
 
        String comment = entity.getComment();
        if (comment != null) {
            stmt.bindString(9, comment);
        }
 
        String transportCallSign = entity.getTransportCallSign();
        if (transportCallSign != null) {
            stmt.bindString(10, transportCallSign);
        }
        stmt.bindString(11, entity.getStreetAddress());
 
        Long operationId = entity.getOperationId();
        if (operationId != null) {
            stmt.bindLong(12, operationId);
        }
    }

    @Override
    protected void attachEntity(Patient entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Patient readEntity(Cursor cursor, int offset) {
        Patient entity = new Patient( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // firstName
            cursor.getString(offset + 2), // lastName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // gender
            cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)), // birthday
            new java.util.Date(cursor.getLong(offset + 5)), // arrival
            cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)), // leave
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // uniqueId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // comment
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // transportCallSign
            cursor.getString(offset + 10), // streetAddress
            cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11) // operationId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Patient entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setFirstName(cursor.getString(offset + 1));
        entity.setLastName(cursor.getString(offset + 2));
        entity.setGender(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBirthday(cursor.isNull(offset + 4) ? null : new java.util.Date(cursor.getLong(offset + 4)));
        entity.setArrival(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setLeave(cursor.isNull(offset + 6) ? null : new java.util.Date(cursor.getLong(offset + 6)));
        entity.setUniqueId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setComment(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setTransportCallSign(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setStreetAddress(cursor.getString(offset + 10));
        entity.setOperationId(cursor.isNull(offset + 11) ? null : cursor.getLong(offset + 11));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Patient entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Patient entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getOperationDao().getAllColumns());
            builder.append(" FROM PATIENT T");
            builder.append(" LEFT JOIN OPERATION T0 ON T.'OPERATION_ID'=T0.'_id'");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected Patient loadCurrentDeep(Cursor cursor, boolean lock) {
        Patient entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        Operation operation = loadCurrentOther(daoSession.getOperationDao(), cursor, offset);
        entity.setOperation(operation);

        return entity;    
    }

    public Patient loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<Patient> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<Patient> list = new ArrayList<Patient>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<Patient> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<Patient> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
