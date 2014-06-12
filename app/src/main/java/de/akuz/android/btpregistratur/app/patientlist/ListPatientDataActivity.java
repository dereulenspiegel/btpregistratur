package de.akuz.android.btpregistratur.app.patientlist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;
import de.akuz.android.btpregistratur.app.BaseActivity;
import de.akuz.android.btpregistratur.app.EditPatientDetailFragment;
import de.akuz.android.btpregistratur.app.PatientDetailFragment;
import de.akuz.android.btpregistratur.app.R;
import de.akuz.android.btpregistratur.dao.Patient;
import de.akuz.android.btpregistratur.dao.PatientDao;

/**
 * Created by till on 04.06.14.
 */
public class ListPatientDataActivity extends BaseActivity
        implements PatientDataListFragment.DataChangedNotifier, PatientDetailFragment.ShowPatientDetail {

    private final static String DETAIL_FRAGMENT_TAG = "detailFragment";

    private boolean mTwoPane;

    PatientDataListFragment listFragment;

    PatientDetailFragment detailFragment;

    @Inject
    FragmentManager fragmentManager;

    @Inject
    PatientDao patientDao;

    @InjectView(R.id.detailContainer)
    @Optional
    LinearLayout detailContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_patients);
        ButterKnife.inject(this);

        listFragment = (PatientDataListFragment) fragmentManager.findFragmentById(R.id.listFragment);
        detailFragment = new PatientDetailFragment();
        if (detailContainer != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(R.id.detailContainer, new EditPatientDetailFragment(), DETAIL_FRAGMENT_TAG);
            ft.commit();
        }
    }

    @Override
    public void notifyDataChanged() {
        listFragment.reloadData();
    }

    @Override
    public void showPatientDetail(long patientId) {
        if (detailContainer != null) {
            Patient patient =  patientDao.queryBuilder().where(PatientDao.Properties.Id.eq(patientId)).unique();
            detailFragment = showFragment(detailFragment);
            detailFragment.setPatient(patient);
        } else {
            // TODO launch actvity
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.add_patient:
                collectPatientData();
                return true;

            default:
                return false;
        }
    }

    private void collectPatientData() {
        if (detailContainer != null) {
            //TODO check if this fragment is already shown.
            showFragment(new EditPatientDetailFragment());

        } else {
            // TODO show activity
        }

    }

    private <F extends Fragment> F showFragment(F fragment) {
        Fragment currentFragment = fragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG);
        if(currentFragment != null && currentFragment.getClass().equals(fragment.getClass())){
            return (F)currentFragment;
        }
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.detailContainer, fragment, DETAIL_FRAGMENT_TAG);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(DETAIL_FRAGMENT_TAG);
        ft.commit();
        return fragment;
    }
}
