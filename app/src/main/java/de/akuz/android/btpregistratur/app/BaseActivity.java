package de.akuz.android.btpregistratur.app;

import android.app.Activity;
import android.os.Bundle;

import dagger.ObjectGraph;
import de.keyboardsurfer.android.widget.crouton.Crouton;

/**
 * Created by till on 03.06.14.
 */
public class BaseActivity extends Activity {

    private ObjectGraph activityObjectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BTPRegistraturApplication app = (BTPRegistraturApplication) getApplication();
        activityObjectGraph = app.getObjectGraph().plus(new FrontendModule(this));
        activityObjectGraph.inject(this);
    }

    public ObjectGraph getObjectGraph() {
        return activityObjectGraph;
    }

    protected void inject(Object o) {
        activityObjectGraph.inject(o);
    }

    @Override
    protected void onDestroy() {
        Crouton.cancelAllCroutons();
        super.onDestroy();
    }
}
