package de.akuz.android.btpregistratur.app.data;

import java.util.List;

import de.akuz.android.btpregistratur.dao.Patient;

/**
 * Created by till on 13.06.14.
 */
public interface DataStore {

    public void savePatient(Patient patient);
    public Patient findPatientById(long id);
    public List<Patient> queryAllPatients();

}
