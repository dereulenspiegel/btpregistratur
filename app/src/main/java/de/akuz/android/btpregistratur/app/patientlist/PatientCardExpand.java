package de.akuz.android.btpregistratur.app.patientlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.akuz.android.btpregistratur.app.R;
import de.akuz.android.btpregistratur.dao.Patient;
import it.gmariotti.cardslib.library.internal.CardExpand;

/**
 * Created by till on 04.06.14.
 */
public class PatientCardExpand extends CardExpand {

    private Patient patient;

    @InjectView(R.id.cardBirthdayTextView)
    TextView birthdayTextView;

    @InjectView(R.id.commentTextView)
    TextView commentTextView;

    public PatientCardExpand(Context context, Patient patient) {
        super(context, R.layout.card_expand_patient);
        this.patient = patient;
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        //super.setupInnerViewElements(parent, view);
        ButterKnife.inject(this, view);

        commentTextView.setText(patient.getComment());
        birthdayTextView.setText("01.01.1970");
    }
}
