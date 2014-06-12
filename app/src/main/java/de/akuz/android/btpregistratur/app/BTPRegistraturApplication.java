package de.akuz.android.btpregistratur.app;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by till on 03.06.14.
 */
public class BTPRegistraturApplication extends Application {

    private static ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        objectGraph = ObjectGraph.create(new MainModule(this));
        objectGraph.inject(this);
        super.onCreate();
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
