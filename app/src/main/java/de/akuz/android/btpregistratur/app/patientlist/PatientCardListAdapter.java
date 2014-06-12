package de.akuz.android.btpregistratur.app.patientlist;

import android.content.Context;
import android.database.Cursor;

import de.akuz.android.btpregistratur.dao.Patient;
import de.akuz.android.btpregistratur.dao.PatientDao;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardCursorAdapter;

/**
 * Created by till on 05.06.14.
 */
public class PatientCardListAdapter extends CardCursorAdapter {

    PatientDao patientDao;

    private boolean expand = true;

    protected PatientCardListAdapter(Context context, Cursor c, PatientDao patientDao) {
        super(context, c, true);
        this.patientDao = patientDao;
    }

    @Override
    protected Card getCardFromCursor(Cursor cursor) {
        Patient patient = patientDao.readEntity(cursor, 0);
        PatientCard card = new PatientCard(getContext(), patient, expand);
        return card;
    }

    public void setCardsExpandable(boolean expand) {
        this.expand = expand;
    }

}
