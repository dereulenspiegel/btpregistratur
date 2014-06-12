package de.akuz.android.btpregistratur.app;

import android.app.LoaderManager;

import dagger.Module;
import dagger.Provides;
import de.akuz.android.btpregistratur.app.patientlist.PatientDataListFragment;

/**
 * Created by till on 05.06.14.
 */
@Module(injects = {PatientDataListFragment.class, PatientDetailFragment.class,
        PatientDetailCard.class}, complete = false, library = true)

public class FragmentModule {

    private BaseFragment fragment;

    public FragmentModule(BaseFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    public LoaderManager provideLoaderManager() {
        return fragment.getLoaderManager();
    }
}
