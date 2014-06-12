package de.akuz.android.btpregistratur.app;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    DateFormats value() default DateFormats.DATE_ONLY;
}