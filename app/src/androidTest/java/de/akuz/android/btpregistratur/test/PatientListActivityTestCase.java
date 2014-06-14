package de.akuz.android.btpregistratur.test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.ActivityInstrumentationTestCase2;

import com.google.android.apps.common.testing.ui.espresso.Espresso;

import de.akuz.android.btpregistratur.app.R;
import de.akuz.android.btpregistratur.app.patientlist.ListPatientDataActivity;
import de.akuz.android.btpregistratur.dao.DaoMaster;
import de.akuz.android.btpregistratur.dao.PatientDao;
import it.gmariotti.cardslib.library.internal.Card;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.Espresso.onView;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.typeText;
import static com.google.android.apps.common.testing.ui.espresso.assertion.ViewAssertions.matches;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.isDisplayed;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withId;
import static com.google.android.apps.common.testing.ui.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.*;
import static org.hamcrest.core.Is.*;
import static org.hamcrest.core.IsAnything.*;
import static org.hamcrest.core.IsInstanceOf.*;

/**
 * Created by till on 05.06.14.
 */
public class PatientListActivityTestCase extends ActivityInstrumentationTestCase2<ListPatientDataActivity> {

    private ListPatientDataActivity activity;

    private SQLiteDatabase db;

    public PatientListActivityTestCase() {
        super(ListPatientDataActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        activity = getActivity();
        db = new DaoMaster.DevOpenHelper(getActivity(), "patient-db", null).getReadableDatabase();
    }

    protected void before() throws Exception {
        db.delete(PatientDao.TABLENAME, null, null);
    }

    public void testEnterPatientData() throws Exception {
        before();
        if (!isTwoPane()) {
            // Only in two pane mode we have the details fragment.
            // for single pane mode we need another testcase
            return;
        }

        enterAndSavePatientData("Perry", "Rhodan", "Stardusttower 1", "Kommentar", "01.01.1980");

        onView(withId(R.id.firstName)).check(matches(withText("")));
        onView(withId(R.id.lastName)).check(matches(withText("")));
        onView(withId(R.id.editStreetAddress)).check(matches(withText("")));
        onView(withId(R.id.editComment)).check(matches(withText("")));
        onView(withId(R.id.birthday)).check(matches(withText("")));

        onView(withText(R.string.patient_data_saved)).check(matches(isDisplayed()));

        onData(anything()).atPosition(0).check(matches(isDisplayed()));

        onData(allOf(is(instanceOf(Card.class)))).atPosition(0).check(matches(isDisplayed()));

        assertRowCount(PatientDao.TABLENAME, 1);
    }

    public void testShowPatientData() throws Exception {
        if (!isTwoPane()) {
            // Only in two pane mode we have the details fragment.
            // for single pane mode we need another testcase
            return;
        }
        testEnterPatientData();

        onData(allOf(is(instanceOf(Card.class)))).atPosition(0).perform(click());

        onView(withId(R.id.cardDetailStreetAddress)).check(matches(withText("Stardusttower 1")));
        onView(withId(R.id.cardDetailComment)).check(matches(withText("Kommentar")));
        onView(withId(R.id.cardDetailBirthday)).check(matches(withText("01.01.1980")));
    }

    public void testBackstackHandling() throws Exception {
        if (!isTwoPane()) {
            // Only in two pane mode we have the details fragment.
            // for single pane mode we need another testcase
            return;
        }
        before();
        for (int i = 0; i < 5; i++) {
            enterAndSavePatientData("Perry" + i, "Rhodan" + i, "Stardusttower " + i, "Kommentar Nummer " + i, "01.01.198" + i);
        }
        onData(allOf(is(instanceOf(Card.class)))).atPosition(4).check(matches(isDisplayed()));
        onData(allOf(is(instanceOf(Card.class)))).atPosition(0).perform(click());
        assertPatientDataIsShown("Perry0","Rhodan0","Stardusttower 0","Kommentar Nummer 0","01.01.1980");

        Espresso.pressBack();
        onView(withId(R.id.firstName)).check(matches(isDisplayed()));

        onData(allOf(is(instanceOf(Card.class)))).atPosition(2).perform(click());
        assertPatientDataIsShown("Perry2","Rhodan2","Stardusttower 2","Kommentar Nummer 2","01.01.1982");
    }

    public void testClearFormData() throws Exception {
        if (!isTwoPane()) {
            // Only in two pane mode we have the details fragment.
            // for single pane mode we need another testcase
            return;
        }
        before();
        enterPatientData("Perry", "Rhodan", "Stardusttower 1", "Kommentar", "01.01.1980");
        onView(withId(R.id.firstName)).check(matches(withText("Perry")));
        onView(withId(R.id.lastName)).check(matches(withText("Rhodan")));
        onView(withId(R.id.editStreetAddress)).check(matches(withText("Stardusttower 1")));
        onView(withId(R.id.editComment)).check(matches(withText("Kommentar")));
        onView(withId(R.id.birthday)).check(matches(withText("01.01.1980")));

        onView(withId(R.id.clearButton)).perform(click());

        onView(withId(R.id.firstName)).check(matches(withText("")));
        onView(withId(R.id.lastName)).check(matches(withText("")));
        onView(withId(R.id.editStreetAddress)).check(matches(withText("")));
        onView(withId(R.id.editComment)).check(matches(withText("")));
        onView(withId(R.id.birthday)).check(matches(withText("")));
    }

    private void assertPatientDataIsShown(String firstName, String lastName, String street, String comment, String birthday){
        onView(withId(R.id.cardDetailStreetAddress)).check(matches(withText(street)));
        onView(withId(R.id.cardDetailComment)).check(matches(withText(comment)));
        onView(withId(R.id.cardDetailBirthday)).check(matches(withText(birthday)));
        onView(withId(R.id.cardDetailFirstNameLastName)).check(matches(withText(firstName+" "+lastName)));
    }

    private void enterAndSavePatientData(String firstName, String lastName, String street, String comment, String birthday) {
        enterPatientData(firstName,lastName,street,comment,birthday);
        onView(withId(R.id.saveButton)).perform(click());
    }

    private void enterPatientData(String firstName, String lastName, String street, String comment, String birthday) {
        onView(withId(R.id.firstName)).perform(typeText(firstName));
        onView(withId(R.id.lastName)).perform(typeText(lastName));
        onView(withId(R.id.editStreetAddress)).perform(typeText(street));
        onView(withId(R.id.editComment)).perform(typeText(comment));
        onView(withId(R.id.birthday)).perform(typeText(birthday));

    }

    private void assertRowCount(String tableName, int expectedCount) {
        Cursor cursor = null;
        try {
            cursor = db.query(tableName, null, null, null, null, null, null);
            assertEquals(expectedCount, cursor.getCount());
        } finally {
            if (cursor != null) {
                cursor.close();

            }
        }
    }

    @Override
    protected void tearDown() throws Exception {
        db.close();
        super.tearDown();
    }

    private boolean isTwoPane() {
        return getActivity().findViewById(R.id.detailContainer) != null;
    }
}
