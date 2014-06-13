package de.akuz.android.btpregistratur.app;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import de.akuz.android.btpregistratur.app.data.DataStore;
import de.akuz.android.btpregistratur.app.patientlist.PatientDataListFragment;
import de.akuz.android.btpregistratur.dao.Patient;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by till on 04.06.14.
 */
public class EditPatientDetailFragment extends BaseFragment {

    private final static String TAG = EditPatientDetailFragment.class.getSimpleName();

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    @Inject
    DataStore dataStore;

    @Inject
    @DateFormat(DateFormats.DATE_ONLY)
    SimpleDateFormat dateFormat;

    @InjectView(R.id.firstName)
    EditText editFirstName;

    @InjectView(R.id.lastName)
    EditText editLastName;

    @InjectView(R.id.editStreetAddress)
    EditText editStreetAddress;

    @InjectView(R.id.birthday)
    EditText editBirthday;

    @InjectView((R.id.editComment))
    EditText editComment;

    @InjectViews({R.id.firstName, R.id.lastName, R.id.editStreetAddress, R.id.birthday, R.id.editComment})
    List<EditText> editableFormViews;

    private Patient currentPatient;

    public EditPatientDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentPatient = new Patient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_patient_detail, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @OnClick(R.id.saveButton)
    public void saveAndNext() {
        try {
            collectValues();
            saveToDatabase();
            clearFormAndModel();
            notifyDataChanged();
            Crouton.makeText(getActivity(), getString(R.string.patient_data_saved), Style.CONFIRM).show();
        } catch (Exception e) {
            Log.e(TAG, "Something went wrong while saving and moving on", e);
            Crouton.makeText(getActivity(), getString(R.string.error_saving_patient_data), Style.ALERT).show();
        }
    }

    public boolean isEmpty() {
        for (EditText view : editableFormViews) {
            if (!"".equals(view.getText().toString())) {
                return false;
            }
        }
        return true;
    }

    private void notifyDataChanged() {
        if (getActivity() instanceof PatientDataListFragment.DataChangedNotifier) {
            ((PatientDataListFragment.DataChangedNotifier) getActivity()).notifyDataChanged();
        } else {
            Log.e(TAG, "Can't notify activity of changed data");
        }
    }

    @OnClick(R.id.clearButton)
    public void clearFormAndModel() {
        for (EditText view : editableFormViews) {
            view.setText("");
        }
        currentPatient = new Patient();
    }

    private void saveToDatabase() {
        // FIXME This should probably happen asynchronous
        dataStore.savePatient(currentPatient);
        Log.d(TAG, "New patient id is " + currentPatient.getId());
    }

    private void collectValues() throws ParseException {
        currentPatient.setFirstName(editFirstName.getText().toString());
        currentPatient.setLastName(editLastName.getText().toString());
        currentPatient.setArrival(new Date());
        currentPatient.setStreetAddress(editStreetAddress.getText().toString());
        currentPatient.setBirthday(dateFormat.parse(editBirthday.getText().toString()));
        currentPatient.setComment(editComment.getText().toString());
    }
}
