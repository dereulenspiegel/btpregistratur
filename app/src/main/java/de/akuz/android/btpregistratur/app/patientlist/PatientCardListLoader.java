package de.akuz.android.btpregistratur.app.patientlist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.akuz.android.btpregistratur.dao.Patient;
import de.akuz.android.btpregistratur.dao.PatientDao;
import de.greenrobot.dao.query.Query;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by till on 08.06.14.
 */
public class PatientCardListLoader extends AsyncTaskLoader<List<Card>> {

    private PatientDao patientDao;

    @Inject
    public PatientCardListLoader(Context context, PatientDao patientDao) {
        super(context);
        this.patientDao = patientDao;
    }

    @Override
    public List<Card> loadInBackground() {
        Query allPatientsQuery = patientDao.queryBuilder().orderAsc(PatientDao.Properties.LastName).build();
        List<Patient> patientList = allPatientsQuery.list();
        List<Card> cardList = new ArrayList<Card>(patientList.size());
        for (Patient patient : patientList) {
            cardList.add(new PatientCard(getContext(), patient, true));
        }
        return cardList;
    }
}
