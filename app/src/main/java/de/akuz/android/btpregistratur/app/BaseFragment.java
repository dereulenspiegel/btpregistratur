package de.akuz.android.btpregistratur.app;


import android.app.Fragment;
import android.os.Bundle;

import dagger.ObjectGraph;

/**
 * Created by till on 03.06.14.
 */
public class BaseFragment extends Fragment {

    private ObjectGraph fragmentObjectGraph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        BaseActivity parentActivity = (BaseActivity) getActivity();
        fragmentObjectGraph = parentActivity.getObjectGraph().plus(new FragmentModule(this));
        fragmentObjectGraph.inject(this);
    }

    protected ObjectGraph getObjectGraph() {
        return fragmentObjectGraph;
    }
}
