package de.akuz.android.btpregistratur.app.patientlist;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.akuz.android.btpregistratur.app.BaseFragment;
import de.akuz.android.btpregistratur.app.PatientDetailFragment;
import de.akuz.android.btpregistratur.app.R;
import de.akuz.android.btpregistratur.dao.Patient;
import de.akuz.android.btpregistratur.dao.PatientDao;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardCursorAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by till on 04.06.14.
 */
public class PatientDataListFragment extends BaseFragment implements LoaderManager.LoaderCallbacks<List<Card>>, AdapterView.OnItemClickListener, Card.OnCardClickListener {

    public static interface DataChangedNotifier {
        public void notifyDataChanged();
    }

    private final static String TAG = PatientDataListFragment.class.getSimpleName();

    private final static int PATIENT_LOADER_ID = 1;

    @InjectView(R.id.patientCardList)
    CardListView patientListView;

    @Inject
    PatientDao patientDao;

    @Inject
    SQLiteDatabase db;

    @Inject
    LoaderManager loaderManager;

    private CardCursorAdapter cardCursorAdapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_list, container, false);
        ButterKnife.inject(this, view);

        cardCursorAdapter = getCursorAdapter();
        CardArrayAdapter fakeAdapter = getFakeAdapter();
        patientListView.setAdapter(cardCursorAdapter);
        patientListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        patientListView.setOnItemClickListener(this);

        loaderManager.initLoader(PATIENT_LOADER_ID, null, this).forceLoad();

        return view;
    }

    public void reloadData() {
        loaderManager.restartLoader(PATIENT_LOADER_ID, null, this).forceLoad();
    }

    private CardArrayAdapter getFakeAdapter() {
        List<Patient> patients = new ArrayList<Patient>(10);
        for (int i = 0; i < 10; i++) {
            Patient patient = new Patient();
            patient.setFirstName("Vorname " + i);
            patient.setLastName("Nachname " + i);
            patient.setStreetAddress("Straße " + i);
            patient.setArrival(new Date());
            patients.add(patient);
        }

        List<Card> cards = new ArrayList<Card>(patients.size());
        for (Patient patient : patients) {
            cards.add(new PatientCard(getActivity(), patient, true));
        }
        CardArrayAdapter adapter = new CardArrayAdapter(getActivity(), cards);
        return adapter;
    }

    private CardCursorAdapter getCursorAdapter() {
        PatientCardListAdapter adapter = new PatientCardListAdapter(getActivity(), null, patientDao);
        return adapter;
    }

    @Override
    public Loader<List<Card>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "Creating loader");

        return getObjectGraph().get(PatientCardListLoader.class);
    }

    @Override
    public void onLoadFinished(Loader<List<Card>> loader, List<Card> data) {
        Log.d(TAG, "Loading finished");
        for (Card card : data) {
            card.setOnClickListener(this);
        }
        patientListView.setAdapter(new CardArrayAdapter(getActivity(), data));
    }

    @Override
    public void onLoaderReset(Loader<List<Card>> loader) {
        Log.d(TAG, "Loader resettet");
        patientListView.setAdapter(new CardArrayAdapter(getActivity(), new ArrayList<Card>(0)));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(Card card, View view) {
        ((PatientDetailFragment.ShowPatientDetail) getActivity()).showPatientDetail(((PatientCard) card).getPatient().getId());
    }
}
