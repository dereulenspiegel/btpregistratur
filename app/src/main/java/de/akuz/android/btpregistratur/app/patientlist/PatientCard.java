package de.akuz.android.btpregistratur.app.patientlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.akuz.android.btpregistratur.app.R;
import de.akuz.android.btpregistratur.dao.Patient;
import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by till on 04.06.14.
 */
public class PatientCard extends Card {

    private Patient patient;
    private Context ctx;
    private boolean expandable;

    @InjectView(R.id.cardStreetAddress)
    TextView streetAddress;

    public PatientCard(Context context, Patient patient, boolean expandable) {
        super(context, R.layout.card_patient);
        this.patient = patient;
        ctx = context;
        this.expandable = expandable;
        init();
    }

    private void init() {
        CardHeader header = new CardHeader(ctx);
        header.setTitle(patient.getFirstName() + " " + patient.getLastName());
        header.setButtonExpandVisible(expandable);
        addCardHeader(header);
        if (expandable) {
            PatientCardExpand expand = new PatientCardExpand(ctx, patient);
            addCardExpand(expand);
        }
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        ButterKnife.inject(this, view);

        streetAddress.setText(patient.getStreetAddress());
        super.setupInnerViewElements(parent, view);
    }

    public Patient getPatient(){
        return patient;
    }

}
