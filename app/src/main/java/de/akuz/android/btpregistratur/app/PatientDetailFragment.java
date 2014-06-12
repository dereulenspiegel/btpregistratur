package de.akuz.android.btpregistratur.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.akuz.android.btpregistratur.dao.Patient;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by till on 11.06.14.
 */
public class PatientDetailFragment extends BaseFragment {

    public static interface ShowPatientDetail {
        public void showPatientDetail(long patientId);
    }

    @InjectView(R.id.patientDetailCardView)
    CardView cardView;

    private Patient currentPatient;

    public PatientDetailFragment() {

    }

    public PatientDetailFragment(Patient patient) {
        this.currentPatient = patient;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_patient_detail, container, false);
        ButterKnife.inject(this, view);
        if (currentPatient != null) {
            setPatient(currentPatient);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setPatient(currentPatient);
    }

    public void setPatient(Patient patient) {
        currentPatient = patient;
        if (cardView == null) {
            return;
        }
        PatientDetailCard detailCard = getObjectGraph().get(PatientDetailCard.class);
        detailCard.setPatient(currentPatient);
        if (cardView.getCard() == null) {
            cardView.setCard(detailCard);
        } else {
            cardView.replaceCard(detailCard);
        }
    }
}