package de.akuz.android.btpregistratur.app;

import android.app.FragmentManager;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.akuz.android.btpregistratur.app.patientlist.ListPatientDataActivity;
import de.akuz.android.btpregistratur.app.patientlist.PatientCardListLoader;

/**
 * Created by till on 04.06.14.
 */
@Module(injects = {ListPatientDataActivity.class, EditPatientDetailFragment.class,
        PatientCardListLoader.class},
        complete = false, library = true)
public class FrontendModule {

    private BaseActivity activity;

    public FrontendModule(BaseActivity activity) {
        this.activity = activity;
    }

    @Provides
    public Context provideActivityContext() {
        return activity;
    }

    @Provides
    public FragmentManager provideFragmentManager() {
        return activity.getFragmentManager();
    }
}
