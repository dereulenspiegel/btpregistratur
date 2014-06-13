package de.akuz.android.btpregistratur.app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.akuz.android.btpregistratur.dao.Patient;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by till on 11.06.14.
 */
public class PatientDetailCard extends Card {

    private Patient patient;

    @Inject
    @DateFormat(DateFormats.DATE_AND_TIME)
    SimpleDateFormat timeFormat;

    @Inject
    @DateFormat(DateFormats.DATE_ONLY)
    SimpleDateFormat birthdayFormat;

    @InjectView(R.id.cardDetailStreetAddress)
    TextView streetAddress;

    @InjectView(R.id.cardDetailBirthday)
    TextView birthday;

    @InjectView(R.id.cardDetailArrival)
    TextView arrival;

    @InjectView(R.id.cardDetailLeaveTime)
    TextView leaveTime;

    @InjectView(R.id.cardDetailComment)
    TextView comment;

    @InjectView(R.id.cardDetailFirstNameLastName)
    TextView firstNameLastName;

    private boolean viewSetup = false;

    @Inject
    public PatientDetailCard(Context ctx) {
        super(ctx, R.layout.card_patient_detail);
    }

    public PatientDetailCard(Context context, Patient patient) {
        super(context, R.layout.card_patient_detail);

        this.patient = patient;
        init();
    }

    private void init() {
        CardHeader header = new CardHeader(getContext());
        header.setTitle(patient.getFirstName() + " " + patient.getLastName());
        addCardHeader(header);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        ButterKnife.inject(this, view);
        viewSetup = true;

        if (patient != null) {
            setPatient(patient);
        }
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
        bindPatientToView();
    }

    private void bindPatientToView() {
        if (!viewSetup) {
            return;
        }
        firstNameLastName.setText(patient.getFirstName() + " " + patient.getLastName());
        streetAddress.setText(patient.getStreetAddress());
        if (patient.getBirthday() != null) {
            birthday.setText(birthdayFormat.format(patient.getBirthday()));
        }
        comment.setText(patient.getComment());
        arrival.setText(timeFormat.format(patient.getArrival()));
        if (patient.getLeave() != null) {
            leaveTime.setText(timeFormat.format(patient.getLeave()));
        }
    }
}
