package de.akuz.android.btpregistratur.app.patientlist;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.akuz.android.btpregistratur.app.data.DataStore;
import de.akuz.android.btpregistratur.dao.Patient;
import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by till on 08.06.14.
 */
public class PatientCardListLoader extends AsyncTaskLoader<List<Card>> {

    private DataStore dataStore;

    @Inject
    public PatientCardListLoader(Context context, DataStore dataStore) {
        super(context);
        this.dataStore = dataStore;
    }

    @Override
    public List<Card> loadInBackground() {
        List<Patient> patientList = dataStore.queryAllPatients();
        List<Card> cardList = new ArrayList<Card>(patientList.size());
        for (Patient patient : patientList) {
            cardList.add(new PatientCard(getContext(), patient, true));
        }
        return cardList;
    }
}
