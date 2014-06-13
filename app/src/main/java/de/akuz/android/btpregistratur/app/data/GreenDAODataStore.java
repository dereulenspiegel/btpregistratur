package de.akuz.android.btpregistratur.app.data;

import java.util.List;

import javax.inject.Inject;

import de.akuz.android.btpregistratur.dao.Patient;
import de.akuz.android.btpregistratur.dao.PatientDao;

/**
 * Created by till on 13.06.14.
 */
public class GreenDAODataStore implements DataStore {

    private PatientDao patientDao;

    @Inject
    public GreenDAODataStore(PatientDao patientDao){
        this.patientDao = patientDao;
    }

    @Override
    public void savePatient(Patient patient) {
        if(patient.getId() == null || patient.getId()<0){
            patientDao.insert(patient);
        } else {
            patientDao.update(patient);
        }
    }

    @Override
    public Patient findPatientById(long id) {
        return patientDao.queryBuilder().where(PatientDao.Properties.Id.eq(id)).unique();
    }

    @Override
    public List<Patient> queryAllPatients() {
        return patientDao.queryBuilder().list();
    }
}
