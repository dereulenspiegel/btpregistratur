package de.akuz.android.btpregistratur.generator;

import java.io.File;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class EntityGenerator {

    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "patients";

    private final static String PACKAGE_NAME = "de.akuz.android.btpregistratur.dao";

    private final static String OUT_PATH = "./app/src/gen/java";

    private static Schema schema;

    public static void main(String[] args) throws Exception {
        System.out.println(new File("./").getAbsoluteFile());
        schema = new Schema(DB_VERSION, PACKAGE_NAME);
        createEntities();

        new DaoGenerator().generateAll(schema, OUT_PATH);
    }

    private static void createEntities() {

        Entity operation = schema.addEntity("Operation");
        operation.addIdProperty().index().autoincrement();
        operation.addDateProperty("startDate");
        operation.addDateProperty("endDate");
        operation.addStringProperty("operationNumber");
        operation.addStringProperty("btpAddress");

        Entity patient = schema.addEntity("Patient");
        patient.addIdProperty().autoincrement().index();
        patient.addStringProperty("firstName").notNull().index();
        patient.addStringProperty("lastName").notNull().index();
        patient.addStringProperty("gender");
        patient.addDateProperty("birthday");
        patient.addDateProperty("arrival").notNull();
        patient.addDateProperty("leave");
        patient.addStringProperty("uniqueId");
        patient.addStringProperty("comment");
        patient.addStringProperty("transportCallSign");
        patient.addStringProperty("streetAddress").notNull();
        patient.addToOne(operation, patient.addLongProperty("operationId").getProperty());

    }
}
